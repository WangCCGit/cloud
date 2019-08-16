package com.it.Dsdk.service.impl;

import com.it.Dsdk.service.PayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayService bestPayService;

    //参数应该有订单详情的
    public PayResponse create() {

        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid("");
        payRequest.setOrderAmount(0.01);
        payRequest.setOrderId("");
        payRequest.setOrderName("");
        //支付类型
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        return bestPayService.pay(payRequest);
    }
}
