package com.he.elm.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "orderdetailet")
public class OrderDetailet implements Serializable {
    @TableId(value = "odId",type = IdType.AUTO)
    private Integer odId;
    private Integer orderId;
    private Integer foodId;
    private Integer quantity;
}
