package com.he.elm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.Identity;
import com.he.elm.common.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface IdentityMapper extends BaseMapper<Identity> {
    Set<Menu> selectMenuByIdentityIdS(@Param("Ids") Set<Integer> setId);
}
