package com.he.elm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.he.elm.common.dtos.OrderdetailetDto;
import com.he.elm.common.pojo.OrderDetailet;

import java.util.List;

public interface OrderDetailetService extends IService<OrderDetailet> {
    public Boolean saveOrderDetailetBatch(List<OrderDetailet> list);

    public List<OrderdetailetDto> listOrderDetailetByOrderId(Integer orderId);
}
