package com.he.elm.config;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Resource
    private MinIoProperties minIOProperties;
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minIOProperties.getUrl()) //传入url地址
                .credentials(minIOProperties.getAccessKey(),minIOProperties.getSecretKey()) //传入用户名和密码
                .build(); //完成MinioClient的初始化
    }
}
