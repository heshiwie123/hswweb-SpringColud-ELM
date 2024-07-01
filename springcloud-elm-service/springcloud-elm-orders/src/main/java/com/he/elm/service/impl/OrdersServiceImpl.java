package com.he.elm.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.he.elm.apis.Business.IBusinessClient;
import com.he.elm.common.constant.Constant;
import com.he.elm.common.dtos.OrdersListDto;
import com.he.elm.common.dtos.OrdersResponseDto;
import com.he.elm.common.pojo.Orders;
import com.he.elm.mapper.OrdersMapper;
import com.he.elm.service.OrderDetailetService;
import com.he.elm.service.OrdersService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private IBusinessClient businessClient; //需调用商家
    @Resource
    private OrderDetailetService orderDetailetService;
    @Resource
    private  RabbitTemplate rabbitTemplate;
    @Override
    @CachePut(value = "orders")
    public int createOrders(Orders orders) {
        save(orders);
        // 给商家发送消息
        rabbitTemplate.convertAndSend(Constant.TOPIC_EXCHANGE,Constant.ORDERS, orders);

        return orders.getOrderId();
    }

    @Override
    @CachePut(value = "orders")
    public OrdersListDto getOrdersById(Integer orderId) {
        OrdersListDto ordersResponseDto = new OrdersListDto();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<Orders>();
        queryWrapper.eq(Orders::getOrderId, orderId);
        Orders orders = ordersMapper.selectOne(queryWrapper);
        ordersResponseDto.setOrderTotal(orders.getOrderTotal());
        ordersResponseDto.setOrderState(orders.getOrderState());

        ordersResponseDto.setBusiness(businessClient.getBusinessById(orders.getBusinessId()));
        ordersResponseDto.setList(orderDetailetService.listOrderDetailetByOrderId(orderId));
        return ordersResponseDto;
    }

    @Override
    @Cacheable(value = "orders", key = "#userId")
    public List<OrdersListDto> listOrdersByUserId(Integer userId) {
        List<OrdersListDto> ordersListDtos = new ArrayList<>();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<Orders>();
        queryWrapper.eq(Orders::getUserId, userId);


        List<Orders> orders =  ordersMapper.selectList(queryWrapper);
        for(Orders order:orders){
            OrdersListDto ordersListDto=new OrdersListDto();
            ordersListDto.setOrderState(order.getOrderState());
            ordersListDto.setOrderTotal(order.getOrderTotal());
            ordersListDto.setBusiness(businessClient.getBusinessById(order.getBusinessId()));
            ordersListDto.setList(orderDetailetService.listOrderDetailetByOrderId(order.getOrderId()));
            ordersListDtos.add(ordersListDto);
        }

        return ordersListDtos;
    }
}
