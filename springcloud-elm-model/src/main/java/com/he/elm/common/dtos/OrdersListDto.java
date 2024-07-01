package com.he.elm.common.dtos;


import com.he.elm.common.pojo.Business;
import com.he.elm.common.pojo.Food;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class OrdersListDto implements Serializable {


    private Double orderTotal;

    private Integer orderState; //订单状态（0：未支付； 1：已支付）
    private Business business;
    private List<OrderdetailetDto> list;
}
