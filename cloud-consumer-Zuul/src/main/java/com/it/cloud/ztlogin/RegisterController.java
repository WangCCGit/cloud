package com.it.cloud.ztlogin;

import com.it.cloud.ztfilter.Constants;
import org.apache.shiro.util.ByteSource;
import com.it.cloud.ztfilter.ITbUserSV;
import com.it.cloud.ztfilter.MessageResult;
import com.it.cloud.ztfilter.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户注册接口
 *
 * @Auther MrGao
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IdWorkByTwitter idWorkByTwitter;

    @Autowired
    private ITbUserSV userSV;

    @Autowired
    private RedisUtil redisUtil;

    /*@Autowired
    private IPointsRecordSV pointsRecordSV;

    @Autowired
    private ITbUserAccountSV userAccountSV;*/

    @RequestMapping(value = "by_phone", method = RequestMethod.POST)
    public MessageResult registerByPhone(@RequestBody LoginByPhone loginByPhone) throws GeneralException {
        LOGGER.info(">>>通过手机注册>>");
        MessageResult messageResult = new MessageResult();
        try {
            String mobilePhone = loginByPhone.getUserMobile();
            //校验手机号
           /* if (!ValidateUtil.isMobilePhone(mobilePhone)) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("手机号非法");
                return messageResult;
            }*/
            //校验手机号是否存在
            //TbUser userByPhone = userSV.queryUserByMobilePhone(mobilePhone);
            TbUser userByPhone = null;
            if (userByPhone != null) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("该手机号已存在，请核实");
                return messageResult;
            }
            Object msgCode = redisUtil.get(Constants.REGISTER_PHONE_CODE + mobilePhone);
            if (msgCode == null) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("请重新发送验证码");
                return messageResult;
            }
            if (!msgCode.equals(loginByPhone.getMsgCode())) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("验证码错误");
                return messageResult;
            }
            //删除验证码缓存
            redisUtil.del(Constants.REGISTER_PHONE_CODE+mobilePhone);
            //解密密码
            String password = EncryptUtils.Decrypt(loginByPhone.getPassword(), Constants.KEY);
            if (StringUtils.isEmpty(password) || password.length() < 6) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("密码长度过短或格式有误，请检查");
                return messageResult;
            }
            //支付密码
           // String payPassword = EncryptUtils.Decrypt(loginByPhone.getPayPassword(), Constants.KEY);
            String payPassword = null;
            if (StringUtils.isEmpty(payPassword) || payPassword.length() < 6) {
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("密码长度过短或格式有误，请检查");
                return messageResult;
            }
            //检查推荐人是否存在
            String recommodId;
            TbUser userCode;
            if (StringUtils.isEmpty(loginByPhone.getRecommendCode())) {
//                //推荐码为空设定默认推荐人
//                recommodId = "99999";
//                userCode = userSV.getByPrimaryKey(Long.valueOf(recommodId));
                messageResult.setReturnCode(MessageResult.ERROR);
                messageResult.setReturnMessage("推荐人不能为空，请检查");
                return messageResult;
            } else {
                //userCode = userSV.queryUserByRecommendCode(loginByPhone.getRecommendCode());
                userCode = null;

                if (userCode == null) {
                    messageResult.setReturnCode(MessageResult.ERROR);
                    messageResult.setReturnMessage("推荐码错误");
                    return messageResult;
                }
                recommodId = userCode.getId() + "";
            }

            //获取不可重复字符串
            String loginNo = String.valueOf(idWorkByTwitter.nextId());
            //盐
            String credentialsSalt = ByteSource.Util.bytes(loginNo).toHex();
            password = Md5.md5Digest(password + credentialsSalt).toLowerCase();
            //生成盐值 加密 将盐存入库
            TbUser user = new TbUser();

            //设置推荐人
            user.setRecommendId(recommodId);
            //生成推荐码
            user.setRecommendCode(mobilePhone);
            user.setAliasName(loginByPhone.getAliasName());
            user.setMobile(loginByPhone.getUserMobile());
            user.setPwdSalt(credentialsSalt);
            user.setPwdCheckvalue(password);
           // user.setPayPasswod(Md5.md5Digest(payPassword + credentialsSalt).toLowerCase());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            //默认体验会员
            user.setUserLevel("0");
            user.setVerifiedStatus("0");
            user.setLastLoginTime(new Date());
            user.setUserIdentity("0");
            user.setUserStatus("0");
            //默认二次激活已认证
            user.setRecommStatus("1");
            //设置推荐关系
            String userRealtion = userCode.getUserRelation();
            if (userRealtion == null) {
                user.setUserRelation(userCode.getId() + "");
            } else {
                user.setUserRelation(userRealtion + "," + recommodId);
            }
            //将信息入库 返回
            //userSV.save(user);
            Long userId = user.getId();
            //生成余额账户和积分账户
            //userAccountSV.createAccount(userId);
            //TODO 修改为从字典表读取
            //注册立即送100积分
           // String registerCount = DictionaryConfig.getVal(SystemConstants.REGISTER_BONUS_POINTS);
            //if (!"0".equals(registerCount)){
                //BigDecimal registerBonusPoints = new BigDecimal(registerCount);
               // pointsRecordSV.addPoints(user, "1", "0", registerBonusPoints, "注册赠送", null, null, null);
           // }
            //更新推荐人总数
            int recommSum = userCode.getRecommendSum();
            userCode.setRecommendSum(recommSum + 1);
            //userSV.updateByPrimaryKey(userCode);
            //userByPhone = userSV.queryUserByMobilePhone(mobilePhone);
          //  messageResult.setObject(AuthUser.toAuthUser(userByPhone));
            messageResult.setReturnCode(MessageResult.OK);
            messageResult.setReturnMessage("注册成功");
        } catch (Exception e) {
            LOGGER.info(">>>>注册失败>>>>e={}",e);
            throw new GeneralException("REGISTER_ERROR", e.getMessage());
        }
        return messageResult;
    }
}
