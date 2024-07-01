package com.he.elm.web.Manager;




import com.he.elm.common.pojo.Menu;
import com.he.elm.mapper.MenuMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 动态获取权限，判断是否有权限访问接口
 * implements AuthorizationManager
 */
@Slf4j
@Component
public class  SttAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    //根据这个表，动态获取权限
    @Resource
    private MenuMapper menuMapper;
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {

        //获取请求路径HttpServletRequest
        HttpServletRequest httpServletRequest=requestAuthorizationContext.getRequest();
        //拿到uri，只包含相对的请求路径url:https://github.com/heshiwie123/SchoolFllowProjectWithSpringSecurity
        //                        uri:heshiwie123/SchoolFllowProjectWithSpringSecurity
        String url=httpServletRequest.getRequestURL().toString();
        String uri=httpServletRequest.getRequestURI();
        log.info("URL=======================>{}",url);
        log.info("URI=======================>{}",uri);

        //根据URI获取路径的访问权限
        Menu menu = menuMapper.selectMenuByURI(uri);
        if(menu==null){
            //直接不通过,因为路径错误
            return new AuthorizationDecision(false);
        }
        //拿到对应权限
        String perms=menu.getPerms();
        log.info("路径需要的权限=======================>{}",perms);
        if(perms==null|| perms.trim().isEmpty()){
            //直接通过,因为此接口路径不需要权限
            return new AuthorizationDecision(true);
        }
        //与用户权限进行判断
        //authentication存储的就是用户信息
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            //authority类型为GrantedAuthority，转化为String
            String userPerms = authority.getAuthority();
            log.info("用户的权限=======================>{}",userPerms);
            if(userPerms.equals(perms)){
                return new AuthorizationDecision(true);
            }
        }
        //遍历完毕，没有权限
        return new AuthorizationDecision(false);
    }
}
