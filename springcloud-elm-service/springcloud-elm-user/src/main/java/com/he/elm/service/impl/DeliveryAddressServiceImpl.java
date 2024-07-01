package com.he.elm.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.he.elm.common.pojo.DeliveryAddress;
import com.he.elm.mapper.DeliveryAddressMapper;
import com.he.elm.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeliveryAddressServiceImpl extends ServiceImpl<DeliveryAddressMapper, DeliveryAddress> implements DeliveryAddressService {
    @Resource
    DeliveryAddressMapper deliveryAddressMapper;

    @Override
    public List<DeliveryAddress> listDeliveryAddressByUserId(Integer userId) {
        log.info("listDeliveryAddressByUserId:userId====================>{}",userId);
        LambdaQueryWrapper<DeliveryAddress> queryWrapper = new LambdaQueryWrapper<DeliveryAddress>();
       queryWrapper.eq(DeliveryAddress::getUserId,userId);
        return deliveryAddressMapper.selectList(queryWrapper);
    }

    @Override
    public DeliveryAddress getDeliveryAddressById(Integer daId) {
        log.info("getDeliveryAddressById:daId====================>{}",daId);
        LambdaQueryWrapper<DeliveryAddress> queryWrapper = new LambdaQueryWrapper<DeliveryAddress>();
        queryWrapper.eq(DeliveryAddress::getDaId,daId);
        DeliveryAddress deliveryAddress= deliveryAddressMapper.selectOne(queryWrapper);
        log.info("deliveryAddress:==========================>{}",deliveryAddress);
        return deliveryAddress;
    }

    @Override
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        log.info("saveDeliveryAddress:deliveryAddress====================>{}",deliveryAddress);
        return deliveryAddressMapper.insert(deliveryAddress);
    }

    @Override
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
        log.info("updateDeliveryAddress:deliveryAddress====================>{}",deliveryAddress);
        LambdaUpdateWrapper<DeliveryAddress> updateWrapper = new LambdaUpdateWrapper<DeliveryAddress>();
        updateWrapper.eq(DeliveryAddress::getDaId,deliveryAddress.getDaId());
        return deliveryAddressMapper.update(deliveryAddress,updateWrapper);
    }

    @Override
    public int removeDeliveryAddress(Integer daId) {
        log.info("removeDeliveryAddress:daId====================>{}",daId);
        return deliveryAddressMapper.deleteById(daId);
    }
}
