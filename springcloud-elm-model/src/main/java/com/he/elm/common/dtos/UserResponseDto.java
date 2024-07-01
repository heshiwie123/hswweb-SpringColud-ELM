package com.he.elm.common.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponseDto implements Serializable {
    private Integer userId;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;
    private String phoneNum;
    //@TableLogic可以设置逻辑删除
    private Integer state;
}
