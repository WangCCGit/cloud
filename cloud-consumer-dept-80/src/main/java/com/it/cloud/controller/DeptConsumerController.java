package com.it.cloud.controller;

import com.it.cloud.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptConsumerController {


    //private static final String URL="http://localhost:8001/dept/";
    //使用服务名访问
    private static final String URL="http://CLOUD-DEPT/dept/";

    @Autowired
    //@LoadBalanced
    private RestTemplate restTemplate;

    @RequestMapping(value = "consumer/add")
    public boolean add(@RequestBody Dept dept){
        boolean b = restTemplate.postForObject(URL+"add",dept,Boolean.class);
        return b;
    }

    @RequestMapping(value = "consumer/findById/{dept}")
    public Dept findAll(@PathVariable("dept")Long dept){
        Dept b = restTemplate.getForObject(URL+"findById/"+dept,Dept.class);
        return b;
    }

    @RequestMapping(value = "consumer/findAll")
    public List<Dept> findById(){
        List b = restTemplate.getForObject(URL+"findAll",List.class);
        return b;
    }




}


