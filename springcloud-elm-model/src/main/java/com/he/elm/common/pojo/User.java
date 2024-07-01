package com.he.elm.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@TableName(value = "user")
public class User implements Serializable, UserDetails {
    @TableId(value = "userId",type = IdType.AUTO)
    private Integer userId;
    private String password;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;
    private String phoneNum;
    //@TableLogic可以设置逻辑删除
    private Integer state;

    //角色信息
    private Set<Identity> identitySet;
    //权限信息
    private Set<String> menus;

    /**
     * SpringSecurity根据getAuthorities获取用户的权限信息
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //权限告知SpringSecurity
        //lambda表达式将Set<String>=》collection<GrantedAuthority>
        if(menus !=null&& !menus.isEmpty()) {
            return menus.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return state==0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return state==0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return state==0;
    }

    @Override
    public boolean isEnabled() {
        return state==0;
    }
}
