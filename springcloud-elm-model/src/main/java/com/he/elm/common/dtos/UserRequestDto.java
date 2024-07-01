package com.he.elm.common.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {
    private String phoneNum;
    private String password;
}
