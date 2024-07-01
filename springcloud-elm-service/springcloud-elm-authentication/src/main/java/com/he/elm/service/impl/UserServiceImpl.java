package com.he.elm.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.he.elm.common.JwtUtils;
import com.he.elm.common.dtos.UserRequestDto;
import com.he.elm.common.dtos.UserResponseDto;
import com.he.elm.common.pojo.User;
import com.he.elm.mapper.UserMapper;
import com.he.elm.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@SentinelResource("/UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    PasswordEncoder encoder;
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String,Object> getUserByPhoneByPass(UserRequestDto userRequestDto) {
        //传入用户名，密码
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userRequestDto.getPhoneNum(), userRequestDto.getPassword());
        //实现登录逻辑,会去调用在UserDetails里定义的loadUserByUsername
        //authenticate就是UserDetails
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authentication);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.info("用户名或者密码错误!");
            return null;
        }
        //获取返回的用户
        User user =  new User();
        user = (User) authenticate.getPrincipal();
        log.info("登录后的用户=======》{}", user);
        if (user == null) {
            return null;
        }
        //根据用户信息生成token
        Map<String, Object> tokenmap = new HashMap<>();
        tokenmap.put("用户Id", user.getUserId());
        tokenmap.put("用户Name", user.getUsername());
        tokenmap.put("用户身份", user.getIdentitySet());
        tokenmap.put("用户权限", user.getMenus());


        //存储用户token信息的map
        Map<String,Object> userMap=new HashMap<>();
        log.info("用户map==============================={}",tokenmap);
        //返回体，初始化
        UserResponseDto userResponseDto=new UserResponseDto();
        //获取并存储用户个人信息
        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setUserName(user.getUsername());
        userResponseDto.setUserImg(user.getUserImg());
        userResponseDto.setUserSex(user.getUserSex());

        userResponseDto.setPhoneNum(user.getPhoneNum());
        userResponseDto.setState(user.getState());

        userMap.put("userInfo",userResponseDto);
        //存储token

        userMap.put("token", JwtUtils.creatToken(tokenmap));
        log.info("token:======================>{}",JwtUtils.creatToken(tokenmap));
        return userMap;
    }
}
