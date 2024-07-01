package com.he.elm.apis.Business;


import com.he.elm.common.pojo.Business;
import com.he.elm.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "businessApplication", configuration = FeignConfig.class)
public interface IBusinessClient {
    @GetMapping("/api/business/getBusinessById")
    public Business getBusinessById(@RequestParam("businessId") Integer businessId);
}
