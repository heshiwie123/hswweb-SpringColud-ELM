package com.he.elm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.he.elm.common.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据号码查找用户
     *
     * @param
     */
    User getUserByPhoneNum(@Param("phoneNum") String phoneNum);

    /**
     * 根据用户名查找用户
     */
    User selectUserByUsername(@Param("name") String name);

    boolean userDefaultIdentity (@Param("userPhone")String userPhone);
    boolean userAddIdentity (@Param("userId")Integer userId,@Param("identityId") Integer identityId);
}
