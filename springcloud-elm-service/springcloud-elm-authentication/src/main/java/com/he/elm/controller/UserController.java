package com.he.elm.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.he.elm.common.dtos.ResponseResult;
import com.he.elm.common.dtos.UserRequestDto;
import com.he.elm.common.enums.AppHttpCodeEnum;
import com.he.elm.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/authenticate")
public class UserController {

    @Resource
    private UserService userService;
    @PostMapping(value = "/login")
    @SentinelResource(value = "authenticate_getUserByPhoneByPass",blockHandler = "getUserByPhoneByPass_blockHandlerMethod" ,fallback = "getUserByPhoneByPass_fallbackMethod")
    public ResponseResult getUserByPhoneByPass(@RequestBody UserRequestDto userRequestDto) {
        Map<String,Object> userInfo = userService.getUserByPhoneByPass(userRequestDto);

        if(userInfo.get("token")!=null){
            return new ResponseResult(200,"登录成功",userInfo);
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
    }

    public String getUserByPhoneByPass_blockHandlerMethod (){

        log.error("authenticate_getUserByPhoneByPass====>{}","发生了流控，请稍后重试!!");
        return "哎呀！达到流量上限啦，请稍后重试！！";
    }
    public String getUserByPhoneByPass_fallbackMethod (){

        log.error("authenticate_getUserByPhoneByPass====>{}","发生了异常，请稍后重试!!");
        return "哎呀！服务出现异常啦，请稍后重试！！";
    }
}
