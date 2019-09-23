package com.it.cloud;

import com.it.myRule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableEurekaClient //eureka客户端注册到注册中心
//启动该服务是加载自定义的ribbon配置类，是配置生效 name为这个微服务使用 configuration为自定义的配置类
//@RibbonClient(name = "CLOUD-DEPT",configuration = MyselfRule.class)
@EnableDiscoveryClient
public class DeptProvider80_App_consumerZuul {

    public static void main(String[] args) {
        SpringApplication.run(DeptProvider80_App_consumerZuul.class,args);
    }

}
