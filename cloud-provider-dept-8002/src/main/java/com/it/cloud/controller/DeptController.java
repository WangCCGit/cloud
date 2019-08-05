package com.it.cloud.controller;

import com.it.cloud.entity.Dept;
import com.it.cloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        boolean b = deptService.addDept(dept);
        return b;
    }
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public List<Dept> findAll(){
        List<Dept> b = deptService.findAll();
        return b;
    }

    @RequestMapping(value = "findById/{dept}",method = RequestMethod.GET)
    public Dept add(@PathVariable("dept") long dept){
        Dept b = deptService.findById(dept);
        return b;
    }


}


