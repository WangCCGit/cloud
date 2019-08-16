package com.it.cloud.ztlogin;

import lombok.Data;

@Data
public class LoginByPhone {
    /**
     * 设备的唯一标识
     */
    private String deviceToken;
    /**
     * 手机表示 0-安卓 1-ios
     */
    private String phoneIdentify;


    private String userMobile;

    private String password;


    /**
     * 手机验证码
     *
     */
    private String msgCode;


    private String aliasName ;


    private String recommendCode;

    /**
     * 腾讯云 防水 票据
     */
    private String ticket ;
    /**
     * 腾讯云 防水 随机串
     */
    private  String randStr ;


}
