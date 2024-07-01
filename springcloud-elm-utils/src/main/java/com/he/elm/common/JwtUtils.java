package com.he.elm.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    //密钥
    private static final String secret="dasadfjksfgsegfgsjdaf";
    /**
     * 生成token
     */
    public static String creatToken(Map<String,Object> map){
        return Jwts.builder()
                //输入信息
                .setClaims(map)
                //颁发时间
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600*24*7))
                //加密算法
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    /**
     * token解析用户信息
     */
    public static Claims parseToken(String token){
        //使用相同密钥
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
