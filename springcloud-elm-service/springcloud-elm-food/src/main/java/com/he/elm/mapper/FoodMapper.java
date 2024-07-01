package com.he.elm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.Food;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {

}
