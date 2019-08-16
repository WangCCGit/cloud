package com.it.controller;


import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

//@RestController //restcontroller   返回json字符串  不会重定向
@Controller
@RequestMapping("wx")
public class WxController {

    @Autowired
    private WxMpService wxMpService;

    /**
     *
     * @param returnUrl 跳转的地址  可以是公众号的等等地址返回带openid
     * @return
     * 访问拼写为    路径？returnUrl=XXX   公众号或者其他的网址
     */
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        String url = "/wx/userInfo";
        //返回带code的 路径这个路径为自己写的方法
        String reidsrectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        return "redirect:"+reidsrectUrl;
    }

    @GetMapping("userInfo")
    public String userInfo(@RequestParam("code")String code,
                         @RequestParam("state")String returnUrl) throws WxErrorException {
        //获得access token
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);

        String openId = wxMpOAuth2AccessToken.getOpenId();

        //获得用户基本信息
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);

        return "redirect:"+returnUrl+"?openid="+openId;
    }





}
