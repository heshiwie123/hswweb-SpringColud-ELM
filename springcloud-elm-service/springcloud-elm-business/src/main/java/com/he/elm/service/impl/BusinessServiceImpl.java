package com.he.elm.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.he.elm.apis.Orders.IOrdersClient;
import com.he.elm.common.pojo.Business;
import com.he.elm.common.pojo.Orders;
import com.he.elm.mapper.BusinessMapper;
import com.he.elm.service.BusinessService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
    @Resource
    BusinessMapper businessMapper;

    @Resource
    private IOrdersClient ordersClient;

    /**
     * 调用orders更改订单状态
     * @param
     */
    @Override
    public void listenOrdersTopicExchange(Integer orderId){
        try {
            Orders orders = ordersClient.updateOrdersStatusById(orderId);
            log.info("更新订单状态结果: {}",orders);
            System.out.println("执行结果: " +orders);
        } catch (Exception e) {
            log.error("调用 Feign 客户端时出错", e);
        }
    }

    /**
     listBusinessByOrderTypeId
     */
    @Override
    public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
        log.info("listBusinessByOrderTypeId:orderTypeId：=============================》{}",orderTypeId);
        LambdaQueryWrapper<Business> queryWrapper=new LambdaQueryWrapper<Business>();
        queryWrapper.eq(Business::getOrderTypeId,orderTypeId);
        List<Business> businesses= businessMapper.selectList(queryWrapper);
        return businesses;
    }

    /**
     getBusinessById
     */
    @Override
    public Business getBusinessById(Integer businessId) {
        log.info("getBusinessById:businessId：=============================》{}",businessId);
        LambdaQueryWrapper<Business> queryWrapper=new LambdaQueryWrapper<Business>();
        queryWrapper.eq(Business::getBusinessId,businessId);
        Business business= businessMapper.selectOne(queryWrapper);
        return business;
    }
}
