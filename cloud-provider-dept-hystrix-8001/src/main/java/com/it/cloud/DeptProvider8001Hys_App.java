package com.it.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient             //注册到eureka服务中
@EnableDiscoveryClient   //服务发现
@EnableCircuitBreaker   //支持熔断器
public class DeptProvider8001Hys_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001Hys_App.class,args);
    }

}
