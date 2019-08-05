package com.it.cloud.controller;

import com.it.cloud.entity.Dept;
import com.it.cloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService = null;


    @RequestMapping(value = "findById/{dept}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("dept") long dept){
        Dept b = deptService.findById(dept);
        if (b==null){
            throw new RuntimeException("该id"+dept+"不存在");
        }

        return b;
    }

    public Dept processHystrix_Get(@PathVariable("dept")long dept){
        return new Dept().setDname("该ID："+dept+"没有对应信息").setDbSource("没有");
    }

}


