package com.he.elm.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "business")
public class Business implements Serializable {
    @TableId(value = "businessId",type = IdType.AUTO)
    private Integer businessId;

    private String businessName;

    private String businessAddress;

    private String businessExplain;

    private String businessImg;

    private Integer orderTypeId;

    private double starPrice; //起送费

    private double deliveryPrice; //配送费

    private String remarks;
}
