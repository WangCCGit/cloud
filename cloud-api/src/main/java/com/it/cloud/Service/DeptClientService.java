package com.it.cloud.Service;

import com.it.cloud.entity.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@FeignClient(value = "CLOUD-DEPT")
@FeignClient(value = "CLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService {


    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(Dept dept);

    @RequestMapping(value = "/dept/findById/{dept}",method = RequestMethod.GET)
    public Dept findById(@PathVariable("dept")Long dept);

    @RequestMapping(value = "/dept/findAll",method = RequestMethod.GET)
    public List<Dept> findAll();

}
