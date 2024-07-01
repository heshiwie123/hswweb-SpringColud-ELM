package com.he.elm.controller;


import com.he.elm.common.dtos.OrdersListDto;
import com.he.elm.common.dtos.OrdersResponseDto;
import com.he.elm.common.dtos.ResponseResult;
import com.he.elm.common.pojo.Orders;
import com.he.elm.service.OrdersService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Resource
    private OrdersService ordersService;

    @PutMapping("/createOrders")
    public ResponseResult createOrders(@RequestBody Orders orders) throws Exception {
        System.out.println("createOrders==========================>" + orders);
        int res = ordersService.createOrders(orders);
        Map<String, Integer> map = new HashMap<>();
        map.put("res", res);
        return new ResponseResult(200, map);
    }

    @GetMapping("/getOrdersById")
    public ResponseResult getOrdersById(Integer orderId) throws Exception {
        OrdersListDto ordersListDto = ordersService.getOrdersById(orderId);
        Map<String, Object> map = new HashMap<>();
        map.put("ordersResponseDto", ordersListDto);
        return new ResponseResult(200, map);
    }

    @GetMapping("/listOrdersByUserId")
    public ResponseResult listOrdersByUserId(@Param("userId") Integer userId) throws Exception {
        List<OrdersListDto> ordersListDtos = ordersService.listOrdersByUserId(userId);
        Map<String, List<OrdersListDto>> map = new HashMap<>();
        map.put("ordersListDtos", ordersListDtos);
        return new ResponseResult(200, map);
    }
}
