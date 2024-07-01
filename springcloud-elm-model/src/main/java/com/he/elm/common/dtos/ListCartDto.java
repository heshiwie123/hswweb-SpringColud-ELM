package com.he.elm.common.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListCartDto implements Serializable {
    private Integer userId;
    private Integer businessId;
}
