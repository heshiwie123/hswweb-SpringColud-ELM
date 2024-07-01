package com.he.elm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.he.elm.common.dtos.UserRequestDto;
import com.he.elm.common.pojo.User;

import java.util.Map;

public interface UserService extends IService<User> {
    public Map<String,Object> getUserByPhoneByPass(UserRequestDto userRequestDto);
}
