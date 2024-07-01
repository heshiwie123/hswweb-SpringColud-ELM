package com.he.elm.config;


import com.he.elm.web.Filter.JwtAuthenticationFilter;
import com.he.elm.web.Manager.SttAuthorizationManager;
import com.he.elm.web.MyUserDetailsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@Slf4j
/**
 *
 * 开启SpringSecurity 后默认注册大量过滤器
 * 过滤器链，责任链模式，可以使用@EnableWebSecurity来配置
 */
public class WebSecurityConfig {
    //jwt过滤器
    @Resource
    JwtAuthenticationFilter jwtAuthenticationFilter;
    //自定义从数据库获取用户信息
    @Resource
    MyUserDetailsService myUserDetailsService;
    //配置自定义的权限比对
    @Resource
    SttAuthorizationManager sttAuthorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //关闭csrf
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(
                authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/**").permitAll()
                        //使用自定义认证逻辑
                        .anyRequest().access(sttAuthorizationManager)
        );
        //将jwt过滤器添加到过滤器链中
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    /**
     * AuthenticationManager负责认证
     * DaoAuthenticationProvider负责将myUserDetailsService、passwordEncoder融合进去送到AuthenticationManager中
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        try {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            //关联UserDetailsService
            provider.setUserDetailsService(myUserDetailsService);
            //关联使用的密码编码器,
            // 这里可以用provider.setPasswordEncoder(passwordEncoder（）)
            // 因为类中给 public PasswordEncoder passwordEncoder()设为了bean
//            provider.setPasswordEncoder(new BCryptPasswordEncoder());
            provider.setPasswordEncoder(passwordEncoder());
            //provider,通过构造函数传进AuthenticationManager(包含)，因为没有set方法

            ProviderManager providerManager = new ProviderManager(provider);
            //ProviderManager implements AuthenticationManager
            return providerManager;
        } catch (Exception e) {
            log.error("Exception during authentication", e);
        }
        return null;
    }

    /**
     * 密码编码设置BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
