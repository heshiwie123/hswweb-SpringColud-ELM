package com.he.elm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class foodApplication {
    public static void main(String[] args) {
        SpringApplication.run(foodApplication.class,args);
    }

}