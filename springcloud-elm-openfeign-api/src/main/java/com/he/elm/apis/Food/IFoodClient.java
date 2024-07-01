package com.he.elm.apis.Food;


import com.he.elm.common.pojo.Food;
import com.he.elm.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "foodApplication", configuration = FeignConfig.class)
public interface IFoodClient {
    @GetMapping("/api/food/getFoodById")
    public Food getFoodById(@RequestParam("foodId") Integer foodId);
}
