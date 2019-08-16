package com.it.cloud.ztlogin;

import com.alibaba.fastjson.JSONObject;
import com.it.cloud.ztfilter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.it.cloud.ztfilter.TbUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录中心接口
 *
 * @Auther MrGao
 */
@RestController
@RequestMapping("login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ITbUserSV userSV;

    @Autowired
    private RedisUtil redisUtil;

   // @Autowired
   // private ITbUserAccountSV userAccountSV;

    //@Autowired
   // private IPushSV pushSV;


    /**
     * 登录接口
     *
     * @param loginByPhone
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public MessageResult loginIn(@RequestBody LoginByPhone loginByPhone, HttpServletRequest request) throws GeneralException {
        MessageResult messageResult = new MessageResult();
        String mobilePhone = loginByPhone.getUserMobile();
        String password = loginByPhone.getPassword();
        LOGGER.info(">>>用户登录接口登录参数mobilePhone={},password={}", mobilePhone, password);
        try {
            //校验用户手机号
           /* if (!ValidateUtil.isMobilePhone(mobilePhone)) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("手机号非法");
                return messageResult;
            }*/
            //还原密码
            password = EncryptUtils.Decrypt(password, Constants.KEY);

            TbUser user = userSV.queryUserByMobilePhone(mobilePhone);
            if (user == null) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("账号或密码错误，请核实");
                return messageResult;
            }
            //判断用户是否禁用
            if ("1".equals(user.getUserStatus())) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("账户已禁用，请联系客服");
                return messageResult;
            }
            if (Md5.md5Digest(password + user.getPwdSalt()).toLowerCase().equals(user.getPwdCheckvalue())) {
                //查询余额账户
                //TbUserAccount account = userAccountSV.getByCateAndUserId(user.getId(), "0");
                //user.setTbUserAccount(account);
                //获取Token
                TokenInfo tokenInfo = TokenInfo.getTokenInfo(user);
                //更新登录时间
                //存储Token信息
                redisUtil.set(Constants.ACCESS_TOKEN_USER + tokenInfo.getAccessToken(), JSONObject.toJSON(tokenInfo),
                        Constants.ACCESS_TOKEN_EXPIRE_TIME_OUT);
               // userSV.updateByPrimaryKeySelective(user);
                messageResult.setObject(tokenInfo);
                messageResult.setReturnCode(MessageResult.OK);
                messageResult.setReturnMessage("登录成功");
                //
            } else {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("账号或密码错误");
            }
        } catch (Exception e) {
            LOGGER.info("登录异常", e);
            throw new GeneralException("LOGIN_ERROR", e.getMessage());
        }

        return messageResult;
    }

    @RequestMapping(value = "login_out", method = RequestMethod.POST)
    public MessageResult loginOut(HttpServletRequest request) throws Exception {
        MessageResult messageResult = new MessageResult();
        try {
            String accessToken = request.getHeader("access-token");
            //清除登入缓存
            redisUtil.del(Constants.ACCESS_TOKEN_USER + accessToken);
            //退出登录
            messageResult.setReturnCode(MessageResult.OK);
            messageResult.setReturnMessage("success");
        } catch (Exception e) {
            LOGGER.info(">>>>用户登出接口异常<<<<", e);
            throw new Exception(e.getMessage());
        }
        return messageResult;
    }

}
