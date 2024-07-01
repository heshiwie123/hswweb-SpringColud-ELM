package com.he.elm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {


    Menu selectMenuByURI(@Param("uri") String uri);
}
