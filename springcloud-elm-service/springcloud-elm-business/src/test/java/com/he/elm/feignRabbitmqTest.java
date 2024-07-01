package com.he.elm;

import com.he.elm.apis.Business.IBusinessClient;
import com.he.elm.apis.Food.IFoodClient;
import com.he.elm.apis.Orders.IOrdersClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class feignRabbitmqTest {

    @Resource
    private IOrdersClient ordersClient;

    @Resource
    IBusinessClient businessClient;
    @Resource
    IFoodClient foodClient;

    @Test
    public void test2() {
        ordersClient.updateOrdersStatusById(37);
    }
    @Test
    public void test3() {
        businessClient.getBusinessById(10001);
    }
    @Test
    public void test4() {
        foodClient.getFoodById(1);
    }

}