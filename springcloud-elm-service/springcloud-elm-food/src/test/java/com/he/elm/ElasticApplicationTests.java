package com.he.elm;

import com.he.elm.common.pojo.Food;
import com.he.elm.mapper.ElasticFoodRepository;
import com.he.elm.mapper.FoodMapper;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ElasticApplicationTests {
    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private ElasticFoodRepository foodRepository;



    @Test
    public void testConnection() throws Exception {
//        foodRepository.findAll().forEach( food -> {
//            System.out.println(food);
//        });\
        foodRepository.findFoodsByFoodNameIsLike("蛋").forEach(food -> {
            System.out.println(food);
        });
    }
//    @Test
//    void initData() {
//        List<Food> list = foodMapper.selectAll();
//
//        for (Food food : list) {
//            foodRepository.save(food);
//        }
//    }

}
