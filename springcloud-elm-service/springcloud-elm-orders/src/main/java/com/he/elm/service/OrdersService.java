package com.he.elm.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.he.elm.common.dtos.OrdersListDto;
import com.he.elm.common.dtos.OrdersResponseDto;
import com.he.elm.common.pojo.Orders;

import java.util.List;

public interface OrdersService extends IService<Orders> {
    public int createOrders(Orders orders);

    public OrdersListDto getOrdersById(Integer orderId);

    public List<OrdersListDto> listOrdersByUserId(Integer userId);
}
