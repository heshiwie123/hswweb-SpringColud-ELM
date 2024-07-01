package com.he.elm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.he.elm.common.dtos.UserRequestDto;
import com.he.elm.common.dtos.UserResponseDto;
import com.he.elm.common.pojo.User;
import com.he.elm.common.JwtUtils;
import com.he.elm.mapper.UserMapper;
import com.he.elm.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    PasswordEncoder encoder;

    @Resource
    UserMapper userMapper;

    @Override
    public int getUserExistByPhone(String phoneNum) {
        log.info("getUserById:phoneNum==================>{}", phoneNum);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getPhoneNum, phoneNum);
        queryWrapper.select(User::getUserId);
        int res = userMapper.selectList(queryWrapper).size();
        log.info("getUserById:res==================>{}", res);
        //queryWrapper晴空，否则会queryWrapper留存在内存中，出现 WHERE (userId = ? AND userId = ? AND userId = ?)
        return res;
    }

    @Override
    public int saveUser(User user) {
        log.info("getUserById:user==================>{}", user);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        //加密为密文
        String password=encoder.encode(user.getPassword());
        user.setPassword(password);
        int res = userMapper.insert(user);
        log.info("getUserById:res==================>{}", res);
        //这里根据用户号码，给用户添加默认的普通用户身份
        userMapper.userDefaultIdentity(user.getPhoneNum());
        return res;
    }
}
