package com.he.elm.common.dtos;


import com.he.elm.common.pojo.Business;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersResponseDto implements Serializable {
    private Integer orderId;
    private Integer userId;
    private Integer businessId;
    private String orderDate;
    private Double orderTotal;
    private Integer daId;
    private Integer orderState; //订单状态（0：未支付； 1：已支付）
    private Business business;
}
