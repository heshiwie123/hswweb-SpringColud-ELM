package com.he.elm.common.dtos;


import com.he.elm.common.pojo.Food;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderdetailetDto implements Serializable {
    private Integer odId;
    private Integer orderId;
    private Integer quantity;

    private Food food;
}
