package com.he.elm.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "cart")
public class Cart implements Serializable {
    @TableId(value = "cartId",type = IdType.AUTO)
    private Integer cartId;
    private Integer foodId;
    private Integer businessId;
    private Integer userId;
    private Integer quantity;
}
