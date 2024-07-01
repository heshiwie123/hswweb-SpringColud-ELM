package com.he.elm.common.dtos;


import com.he.elm.common.pojo.Business;
import com.he.elm.common.pojo.Food;
import lombok.Data;

import java.io.Serializable;

@Data
public class CartResponseDto implements Serializable {
    private Integer cartId;
    private Integer foodId;
    private Integer businessId;
    private String userId;
    private Integer quantity;
    private Food food;
    private Business business;
}
