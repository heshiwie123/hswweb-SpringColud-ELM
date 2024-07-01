package com.he.elm.controller;



import com.he.elm.common.dtos.ResponseResult;
import com.he.elm.common.pojo.Business;
import com.he.elm.service.BusinessService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/business")

public class BusinessController {

    @Resource
    private BusinessService businessService;

    @GetMapping(value = "/listBusinessByOrderTypeId")
    public ResponseResult listBusinessByOrderTypeId(@Param("orderTypeId") Integer orderTypeId) throws Exception{
        List<Business> businesses= businessService.listBusinessByOrderTypeId(orderTypeId);
        Map<String, List<Business>> map=new HashMap<>();
        map.put("businessList",businesses);
        return new ResponseResult(200,map);
    }

    @GetMapping("/getBusinessById")
    public ResponseResult getBusinessById(@Param("businessId") Integer businessId) throws Exception{
        Business business= businessService.getBusinessById(businessId);
        Map<String,Object> map=new HashMap<>();
        map.put("business",business);
        return new ResponseResult(200,"查询成功",map);
    }
}