package com.he.elm.apis.Orders;



import com.he.elm.common.pojo.Orders;
import com.he.elm.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "ordersApplication", configuration = FeignConfig.class)
public interface IOrdersClient {
    @PutMapping("/api/orders/updateOrdersStatusById")
    public Orders updateOrdersStatusById(@RequestParam("ordersId") Integer ordersId);
}
