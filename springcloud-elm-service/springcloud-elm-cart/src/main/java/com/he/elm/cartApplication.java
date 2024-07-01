package com.he.elm;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//扫描所有主启动类下有@FeignClient，若引入的@FeignClient不在启动类扫描包下，则无法创建ServiceProviderService
//此时需要有两种解决方案：1.@EnableFeignClients(basePackages = "com.he.springcloud.feign")
//                   2.@EnableFeignClients(clients = {ServiceProviderService.class}),可以多指定
@EnableAutoDataSourceProxy
@EnableFeignClients(basePackages = "com.he.elm")
public class cartApplication {
    public static void main(String[] args) {
        SpringApplication.run(cartApplication.class,args);
    }
}