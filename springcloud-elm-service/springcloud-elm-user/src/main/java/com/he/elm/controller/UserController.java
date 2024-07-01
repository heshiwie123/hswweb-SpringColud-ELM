package com.he.elm.controller;



import com.he.elm.common.dtos.ResponseResult;
import com.he.elm.common.dtos.UserRequestDto;
import com.he.elm.common.enums.AppHttpCodeEnum;
import com.he.elm.common.pojo.User;
import com.he.elm.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/getUserExistByPhone")
    public ResponseResult getUserExistByPhone(@Param("phoneNum") String phoneNum) throws Exception{
        int res=userService.getUserExistByPhone(phoneNum);
        if(res!=0){
            return new ResponseResult(200,"用户存在");
        }
        return new ResponseResult(202,"用户不存在");
    }

    @PutMapping("/saveUser")
    public ResponseResult saveUser(@RequestBody User user) throws Exception{
        int res= userService.saveUser(user);
        if(res!=0){
            return new ResponseResult(200,"保存成功");
        }
        return new ResponseResult(500,"保存失败");
    }

}
