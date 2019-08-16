package com.it.cloud.ztfilter;

public class Constants {
   
	//加密相关数据
    public static final String KEY = "29roeyufuqphribl";
    public static final String ALGORITHMSTR_CBC = "AES/CBC/PKCS5Padding";
    public static final String ALGORITHMSTR_ECB = "AES/ECB/PKCS5Padding";
    public static final String VI = "0102030405060708";
   
    //session
    public static final String SESSION_USER = "AUTH_SESSION_USER";
    
    //Token过期时间
    public static final int ACCESS_TOKEN_EXPIRE_TIME_OUT=86400 ;
    
    //tokenKey前缀
    public static final String ACCESS_TOKEN_USER = "ACCESS_TOKEN_USER:";
    
    //重置密码验证码前缀
    public static final String USERINFO_RESET_VERIFYCODE = "'USERINFO_RESET_VERIFYCODE:'" ;
    public static final int USERINFO_RESET_VERIFYCODE_TIMEOUT = 600;

    //验证码过期时间10分钟
    public static final String PHONE_CODE="PHONE_CODE_";

    public static final String REGISTER_PHONE_CODE="REGISTER_PHONE_CODE_";

    public static final String WITHDRAW_PHONE_CODE="WITHDRAW_PHONE_CODE_";

    public static final String AUTH_AGAIN_PHONE_CODE="AUTH_AGAIN_PHONE_CODE_";
    //验证码下发1分钟间隔
    public static final String PHONE_CODE_TIME="PHONE_CODE_TIME_";

    //币种配置缓存
    public static final String PLATFORM_COIN_CONFIG_CONFIGURATION = "PLATFORM_COIN_CONFIG_CONFIGURATION_";
    public static final Long PLATFORM_COIN_CONFIG_CONFIGURATION_TIME_OUT = 86400L;


    /**
     * 充值类型缓存
     */
    public static final String RECHARGE_TYPE="RECHARGE_TYPE_";

    public static final Long RECHARGE_TYPE_TIME_OUT=86400L;
    
    //推荐有效人数
    public static final String USER_RECOMMEND="USER_RECOMMEND_";

    /**动态收益比例
     *
     */
    public static final String DYNAMIC_INCOME="DYNAMIC_INCOME_";

}
