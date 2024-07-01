package com.he.elm;

import com.he.elm.apis.Business.IBusinessClient;
import com.he.elm.apis.Food.IFoodClient;
import com.he.elm.apis.Orders.IOrdersClient;
import com.he.elm.common.pojo.Business;
import com.he.elm.common.pojo.Food;
import com.he.elm.common.pojo.Orders;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeignTest {

    @Resource
    private IFoodClient foodClient;
    @Resource
    private IBusinessClient businessClient;
    @Resource
    private IOrdersClient ordersClient;

    @Test
    public void testGetFoodById() {
        Food food = foodClient.getFoodById(1);
        System.out.println(food);
    }
    @Test
    public void testGeBusinessById() {
        Business business = businessClient.getBusinessById(10001);
        System.out.println(business);
    }
    @Test
    public void test2() {
        Orders orders = ordersClient.updateOrdersStatusById(37);
        System.out.println(orders);
    }
}

