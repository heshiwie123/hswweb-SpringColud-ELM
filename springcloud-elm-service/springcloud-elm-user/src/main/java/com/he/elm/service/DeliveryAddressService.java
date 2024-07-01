package com.he.elm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.he.elm.common.pojo.DeliveryAddress;


import java.util.List;

public interface DeliveryAddressService extends IService<DeliveryAddress> {
    public List<DeliveryAddress> listDeliveryAddressByUserId(Integer userId);

    public DeliveryAddress getDeliveryAddressById(Integer daId);
    public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
    public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
    public int removeDeliveryAddress(Integer daId);
}
