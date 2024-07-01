package com.he.elm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.OrderDetailet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 标注Mapper映射到xml,以及确认让Spring管理
 */
@Mapper
public interface OrderDetailetMapper extends BaseMapper<OrderDetailet> {

}
