package com.it.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//网页授权用的
@Component
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig wechat;

    @Bean
    public WxMpService wxMpService(){

        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(wechat.getMpAppId());
        config.setSecret(wechat.getMpAppSecret());

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }




}
