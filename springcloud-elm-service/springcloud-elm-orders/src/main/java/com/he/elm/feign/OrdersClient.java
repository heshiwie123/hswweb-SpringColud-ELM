package com.he.elm.feign;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.he.elm.apis.Orders.IOrdersClient;
import com.he.elm.common.pojo.Orders;
import com.he.elm.service.OrdersService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersClient implements IOrdersClient {
    @Resource
    private OrdersService ordersService;

    @PutMapping("/api/orders/updateOrdersStatusById")
    public Orders updateOrdersStatusById(@RequestParam("ordersId") Integer ordersId) {
        boolean result = ordersService.update(new LambdaUpdateWrapper<Orders>()
                .eq(Orders::getOrderId, ordersId)
                .set(Orders::getOrderState, 1));
        System.out.println(result);

        return ordersService.getById(ordersId);
    }
}
