package com.it.cloud.weixinpay;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class GetUserOpenAction{
    private static final long serialVersionUID = 1L;

    public String getCode(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");
        System.out.println(code);
        if (code != null) {
            //获取openid和access_token的连接
            String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=APPSECRET&code=CODE&grant_type=authorization_code";
            //获取返回的code
            String requestUrl = getOpenIdUrl.replace("CODE", code);
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //向微信发送请求并获取response
            String response = httpClient.execute(httpGet,responseHandler);
            System.out.println("=========================获取token===================");
            System.out.println(response);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(response);
            String access_token = jsonObject.get("access_token").getAsString();
            String openId = jsonObject.get("openid").getAsString();
            System.out.println("=======================用户access_token==============");
            System.out.println(access_token);
            System.out.println(openId);
            //获取用户基本信息的连接
            String getUserInfo = "https://api.weixin.qq.com/sns/userinfo?  access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
            String userInfoUrl = getUserInfo.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);
            HttpGet httpGetUserInfo = new HttpGet(userInfoUrl);
            String userInfo = httpClient.execute(httpGetUserInfo,responseHandler);
            //微信那边采用的编码方式为ISO8859-1所以需要转化
            String json = new String(userInfo.getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("====================userInfo==============================");
            JsonObject jsonObject1 = (JsonObject) parser.parse(json);
            String nickname = jsonObject1.get("nickname").getAsString();
            String city = jsonObject1.get("city").getAsString();
            String province = jsonObject1.get("province").getAsString();
            String country = jsonObject1.get("country").getAsString();
            String headimgurl = jsonObject1.get("headimgurl").getAsString();
            //性别  1 男  2 女  0 未知
            Integer sex = jsonObject1.get("sex").getAsInt();
            System.out.println("昵称"+nickname);
            System.out.println("城市"+city);
            System.out.println("省"+province);
            System.out.println("国家"+country);
            System.out.println("头像"+headimgurl);
            System.out.println("性别"+sex);
            System.out.println(userInfo);
        }
        return "ok";
    }
}
