package com.he.elm.feign;

import com.he.elm.apis.Business.IBusinessClient;
import com.he.elm.common.pojo.Business;
import com.he.elm.service.BusinessService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessClient implements IBusinessClient {
    @Resource
    private BusinessService businessService;

    @GetMapping("/api/business/getBusinessById")
    public Business getBusinessById(@RequestParam("businessId") Integer businessId) {
        return businessService.getById(businessId);
    }
}
