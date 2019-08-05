package com.it.myRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义ribbon规则
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
       // return new RandomRule();    //随机
        return new RandomRule_MS(); //五次轮询
    }

}
