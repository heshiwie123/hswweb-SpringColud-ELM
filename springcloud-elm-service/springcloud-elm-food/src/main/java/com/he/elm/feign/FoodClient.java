package com.he.elm.feign;

import com.he.elm.apis.Food.IFoodClient;
import com.he.elm.common.pojo.Food;
import com.he.elm.service.FoodService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodClient implements IFoodClient {
    @Resource
    private FoodService foodService;

    @GetMapping("/api/food/getFoodById")
    public Food getFoodById(@RequestParam("foodId") Integer foodId) {
        return foodService.getById(foodId);
    }
}
