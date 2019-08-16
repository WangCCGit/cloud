package com.it.Dsdk.controller;

import com.it.Dsdk.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dsdk")
public class DsdkController {

    @Autowired
    private PayService payService;

    @PostMapping
    public void create(){
        //根据订单id查询订单
        // 应该传参订单
        payService.create();

    }

}
