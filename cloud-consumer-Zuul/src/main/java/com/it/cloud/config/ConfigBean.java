package com.it.cloud.config;

import com.it.cloud.entity.Dept;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced  //这个是实现负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /*@Bean //这个有就用这个没有就用上一个
    public IRule myRule(){
        return new RandomRule();  //自定义随机访问服务端
        //return new RetryRule();
    }*/

}
