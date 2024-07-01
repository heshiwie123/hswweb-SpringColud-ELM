package com.he.elm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
    Orders getOrdersById(Integer orderId);

    List<Orders> listOrdersByUserId(String userId);
}
