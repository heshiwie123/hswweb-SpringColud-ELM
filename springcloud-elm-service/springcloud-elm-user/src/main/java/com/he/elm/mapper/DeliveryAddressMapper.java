package com.he.elm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.DeliveryAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryAddressMapper extends BaseMapper<DeliveryAddress> {

    public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);


    public DeliveryAddress getDeliveryAddressById(Integer daId);


    public int saveDeliveryAddress(DeliveryAddress deliveryAddress);

    public int updateDeliveryAddress(DeliveryAddress deliveryAddress);

    public int removeDeliveryAddress(Integer daId);
}
