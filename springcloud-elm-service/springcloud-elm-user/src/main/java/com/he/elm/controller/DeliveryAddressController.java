package com.he.elm.controller;



import com.he.elm.common.dtos.ResponseResult;
import com.he.elm.common.enums.AppHttpCodeEnum;
import com.he.elm.common.pojo.DeliveryAddress;
import com.he.elm.service.DeliveryAddressService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/deliveryAddress")
public class DeliveryAddressController {
    @Resource
    private DeliveryAddressService deliveryAddressService;

    @GetMapping("/listDeliveryAddressByUserId")
    public ResponseResult listDeliveryAddressByUserId(@Param("userId") Integer userId) throws Exception{
        List<DeliveryAddress>deliveryAddresses= deliveryAddressService.listDeliveryAddressByUserId(userId);
        Map<String,Object> map=new HashMap<>();
        map.put("deliveryAddresses",deliveryAddresses);
        return new ResponseResult(200,"获取成功",map);
    }

    @GetMapping("/getDeliveryAddressById")
    public ResponseResult getDeliveryAddressById(@Param("daId") Integer daId) throws Exception{
        DeliveryAddress deliveryAddress= deliveryAddressService.getDeliveryAddressById(daId);
        Map<String, Object> map=new HashMap<>();
        map.put("deliveryAddress",deliveryAddress);
        return new ResponseResult(200,"获取成功",map);
    }

    @PutMapping("/saveDeliveryAddress")
    public ResponseResult saveDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
        Integer res= deliveryAddressService.saveDeliveryAddress(deliveryAddress);
        if(res!=0){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.FAILED);
    }

    @PostMapping("/updateDeliveryAddress")
    public ResponseResult updateDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress) throws Exception{
        int res= deliveryAddressService.updateDeliveryAddress(deliveryAddress);
        if(res!=0){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.FAILED);
    }

    @DeleteMapping("/removeDeliveryAddress")
    public ResponseResult removeDeliveryAddress(Integer daId) throws Exception{
        int res= deliveryAddressService.removeDeliveryAddress(daId);
        if(res!=0){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
            return ResponseResult.errorResult(AppHttpCodeEnum.FAILED);
    }
}
