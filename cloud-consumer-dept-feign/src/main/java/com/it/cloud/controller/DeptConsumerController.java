package com.it.cloud.controller;

import com.it.cloud.Service.DeptClientService;
import com.it.cloud.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptConsumerController {


    //private static final String URL="http://localhost:8001/dept/";
    //使用服务名访问
    /*private static final String URL="http://cloud-dept/dept/";


    @Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private DeptClientService service;

    @RequestMapping(value = "consumer/add")
    public boolean add(@RequestBody Dept dept){
        boolean b = service.add(dept);
        return b;
    }

    @RequestMapping(value = "consumer/findById/{dept}")
    public Dept findAll(@PathVariable("dept")Long dept){
        Dept b = service.findById(dept);
        //Dept b = restTemplate.getForObject(URL+"findById/"+dept,Dept.class);
        return b;
    }

    @RequestMapping(value = "consumer/findAll")
    public List<Dept> findById(){
        List<Dept> b = service.findAll();
        //List b = restTemplate.getForObject(URL+"findAll",List.class);
        return b;
    }




}


