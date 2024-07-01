package com.he.elm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    //根据用户id选择权限
    List<Menu> getMenusByUserId(@Param("userId") Integer userId);

    Menu selectMenuByURI(@Param("uri") String uri);
}
