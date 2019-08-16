package com.it.sdk;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.it.sdk.payConfig.WxPayConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("pay")
@Slf4j
public class MyWxPayController {

    @Autowired
    private WxPayService wxService;

    @Autowired
    private WxPayConfiguration wxPayConfiguration;

    /**
     *
     * @param request
     * @param orderNo  订单号
     * @param subject
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "wxpay")
    public String pay(HttpServletRequest request, String orderNo, String subject) {
        try {
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();

            //根据订单号 支付获取订单
            //商品描述
            orderRequest.setBody("主题");
            //订单id
            orderRequest.setOutTradeNo("订单号");
            //支付的金额
            //orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(order.getTotalFee()));//元转成分
            orderRequest.setOpenid("openId");
            //终端IP.
            orderRequest.setSpbillCreateIp("userIp");
            orderRequest.setTimeStart("yyyyMMddHHmmss");
            orderRequest.setTimeExpire("yyyyMMddHHmmss");
            orderRequest.setNotifyUrl("");
            orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
            Object order = wxPayConfiguration.wxService().createOrder(orderRequest);
            // return Result.ok(wxPayService.createOrder(orderRequest));
            return "";
        } catch (Exception e) {
            log.error("微信支付失败！订单号：{},原因:{}", orderNo, e.getMessage());
            e.printStackTrace();
            //return Result.fail("支付失败，请稍后重试！");
            return "";
        }
    }


    @ResponseBody
    @RequestMapping("/wx")
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxService.parseOrderNotifyResult(xmlResult);

            // 加入自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            String orderId = result.getOutTradeNo();
            String tradeNo = result.getTransactionId();
            String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

}
