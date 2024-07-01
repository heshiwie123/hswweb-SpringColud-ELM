package com.he.elm.web.Filter;




import com.he.elm.common.JwtUtils;
import com.he.elm.common.pojo.Identity;
import com.he.elm.common.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
/**
 * 制作jwt过滤器，从token中获取用户信息
 * 加入到SpringSecurity的过滤器链中
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * 1.获取到用户信息后，需要传递给SpringSecurity,他会去判断接口方法是否有权限
     * 2.告知SpringSecurity就是使用Authentication告知框架，然后存到SecurityContext中，=====》SecurityContextHolder中
     */
    @Override
    /**
     * 该方法会被doFilter调用
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取token
        String token = request.getHeader("Authorization");
        //没有token 直接返回错误信息
        System.out.println("doFilterInternal:token:"+token);
        if (token == null ||token.equals("null")) {
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            System.out.println("没有检查到token，请检查是否登录！！！！！");
            return;
        }
        //有token,Jwt解析数据
        log.info("token=============>{}", token);
        Claims claims = null;
        try {
            claims = JwtUtils.parseToken(token);
        } catch (SignatureException e) {
            response.setContentType("application/json; charset=utf-8");
            //验签出错会导致乱码，设置格式
            response.setCharacterEncoding("UTF-8");
            System.out.println("验签失败，请重新登录！！！！");
            return;
        }catch (ExpiredJwtException expiredJwtException){
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("token过期！！！！");
            return;
        }

        //获取到信息
        Integer id = claims.get("用户Id", Integer.class);
        String name = claims.get("用户Name", String.class);
        ArrayList<Identity> identitys = claims.get("用户身份", ArrayList.class);
        ArrayList<String> menus = claims.get("用户权限", ArrayList.class);
        log.info("权限：=================》{}", menus);
        //放到user中
        User user = new User();
        user.setUserId(id);
        user.setUserName(name);
        /**
         * jwt解析完数据会将数据转换为ArrayList类型，不匹配我们的Set
         * 设置新的Set集合，将刚刚jwt解释的ArrayList类型数据添加进去，在添加到用户
         */
        Set<Identity> identitySet = new HashSet<>();
        Set<String> menuSet = new HashSet<>();
        identitySet.addAll(identitys);
        menuSet.addAll(menus);

        user.setIdentitySet(identitySet);
        user.setMenus(menuSet);
        //用户信息放置到SecurityContext中
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //放行
        doFilter(request, response, filterChain);
    }
}
