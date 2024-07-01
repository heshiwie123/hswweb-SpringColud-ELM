package com.he.elm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.he.elm.apis.Food.IFoodClient;
import com.he.elm.common.dtos.OrderdetailetDto;
import com.he.elm.common.pojo.OrderDetailet;
import com.he.elm.mapper.OrderDetailetMapper;
import com.he.elm.service.OrderDetailetService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderDetailetServiceImpl extends ServiceImpl<OrderDetailetMapper, OrderDetailet> implements OrderDetailetService {
   @Resource
   private OrderDetailetMapper orderDetailetMapper;

   @Resource
   private IFoodClient foodClient;

    @Override
    public Boolean saveOrderDetailetBatch(List<OrderDetailet> list) {
        return saveBatch(list);
    }

    @Override
    public List<OrderdetailetDto> listOrderDetailetByOrderId(Integer orderId) {
        LambdaQueryWrapper<OrderDetailet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderDetailet::getOrderId,orderId);
        List<OrderdetailetDto> orderdetailetDtos = new ArrayList<>();
        List<OrderDetailet> orderDetailets = orderDetailetMapper.selectList(lambdaQueryWrapper);

        orderDetailets.forEach(orderDetailet -> {
            OrderdetailetDto orderdetailetDto = new OrderdetailetDto();
            orderdetailetDto.setFood(foodClient.getFoodById(orderDetailet.getFoodId()));
            orderdetailetDto.setQuantity(orderDetailet.getQuantity());
            orderdetailetDto.setOdId(orderDetailet.getOdId());
            orderdetailetDto.setOrderId(orderDetailet.getOrderId());

            orderdetailetDtos.add(orderdetailetDto);
        });
        return orderdetailetDtos;
    }
}
