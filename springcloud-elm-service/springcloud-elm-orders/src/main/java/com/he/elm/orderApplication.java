package com.he.elm;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.he.elm")
@EnableCaching
public class orderApplication {
    public static void main(String[] args) {
        SpringApplication.run(orderApplication.class,args);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public MappingJackson2HttpMessageConverter jsonHttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}