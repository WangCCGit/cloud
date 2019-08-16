package com.it.cloud.ztfilter;

import com.it.cloud.ztlogin.TbUserAccount;
import lombok.Data;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TbUser implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID 自增
     * This field corresponds to the database column tb_user.ID
     *
     * @date 2018-11-08 10:59:32
     */

    private Long id;

    /**
     * 昵称
     * This field corresponds to the database column tb_user.ALIAS_NAME
     *
     * @date 2018-11-08 10:59:32
     */
    private String aliasName;

    /**
     * 手机号
     * This field corresponds to the database column tb_user.MOBILE
     *
     * @date 2018-11-08 10:59:32
     */

    private String mobile;

    /**
     * 邮箱
     * This field corresponds to the database column tb_user.EMAIL
     *
     * @date 2018-11-08 10:59:32
     */
    private String email;

    /**
     * 登录密码校验值
     * This field corresponds to the database column tb_user.PWD_CHECKVALUE
     *
     * @date 2018-11-08 10:59:32
     */
    private String pwdCheckvalue;

    /**
     * 密码salt值
     * This field corresponds to the database column tb_user.PWD_SALT
     *
     * @date 2018-11-08 10:59:32
     */
    private String pwdSalt;

    /**
     * 用户等级(0-体验用户，1-普通会员，2-VIP会员)
     * This field corresponds to the database column tb_user.USER_LEVEL
     *
     * @date 2018-11-08 10:59:32
     */

    private String userLevel;

    /**
     * 实名认证状态（0-未认证，1-已认证，2-待认证，3-认证未通过）
     * This field corresponds to the database column tb_user.VERIFIED_STATUS
     *
     * @date 2018-11-08 10:59:32
     */

    private String verifiedStatus;

    /**
     * 真实姓名
     * This field corresponds to the database column tb_user.REAL_NAME
     *
     * @date 2018-11-08 10:59:32
     */
    private String realName;

    /**
     * 性别
     * This field corresponds to the database column tb_user.GENDER
     *
     * @date 2018-11-08 10:59:32
     */
    private String gender;

    /**
     * 国别
     * This field corresponds to the database column tb_user.COUNTRY
     *
     * @date 2018-11-08 10:59:32
     */
    private String country;

    /**
     * 证件类型
     * This field corresponds to the database column tb_user.IDCARD_TYPE
     *
     * @date 2018-11-08 10:59:32
     */
    private String idcardType;

    /**
     * 证件号码
     * This field corresponds to the database column tb_user.IDCARD_NO
     *
     * @date 2018-11-08 10:59:32
     */

    private String idcardNo;

    /**
     * 证件图片路径
     * This field corresponds to the database column tb_user.IDCARD_PIC_PATH
     *
     * @date 2018-11-08 10:59:32
     */
    private String idcardPicPath;

    /**
     * 常用登录设备码1
     * This field corresponds to the database column tb_user.USED_LOGIN_DEVICE1
     *
     * @date 2018-11-08 10:59:32
     */
    private String usedLoginDevice1;

    /**
     * 常用登录设备码2
     * This field corresponds to the database column tb_user.USED_LOGIN_DEVICE2
     *
     * @date 2018-11-08 10:59:32
     */
    private String usedLoginDevice2;

    /**
     * 上次登录时间
     * This field corresponds to the database column tb_user.LAST_LOGIN_TIME
     *
     * @date 2018-11-08 10:59:32
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     * This field corresponds to the database column tb_user.CREATE_TIME
     *
     * @date 2018-11-08 10:59:32
     */
      private Date createTime;

    /**
     * 修改时间
     * This field corresponds to the database column tb_user.UPDATE_TIME
     *
     * @date 2018-11-08 10:59:32
     */
    private Date updateTime;

    /**
     * 头像图片路径
     * This field corresponds to the database column tb_user.AVATAR_PIC_PATH
     *
     * @date 2018-11-08 10:59:32
     */
    private String avatarPicPath;

    /**
     * 可用时长
     * This field corresponds to the database column tb_user.AVAILABLE_TIME
     *
     * @date 2018-11-08 10:59:32
     */
    private Date availableTime;

    private Integer pageNum;

    private Integer pageSize;

    /**
     * 个人推荐码
     */
    private String recommendCode;

    /**
     * 一级推荐人ID
     */
    private String recommendId;

    /**
     * 推荐人总数
     */
    private Integer recommendSum = 0;

    /**
     * 二次认证标志位(0-未认证，1-已认证)
     */
    private String recommStatus = "1";

    /**
     * 用户身份标示(0-一般用户，1-团长)
     */
    private String userIdentity;

    /**
     * 推荐关系（2，5，7，1，11，19）
     */
    private String userRelation;


//	private String payPasswod;

    /**
     * 用户状态 （0-可用，1-已禁用）
     */

    private String userStatus = "0";

    /**
     * 积分总额
     */
    private BigDecimal pointsTotal;

    /**
     * 余额总额
     */
    private BigDecimal accountTotal;

    /**
     * 积分冻结
     */
    private BigDecimal frozenPoints;


    /**
     * 代理商星级（0，1，2，3，4，5）
     */

    private String groupLevel = "0";

    /**
     * 直推累计
     */
    private BigDecimal grandTotal = BigDecimal.ZERO;

    /**
     * 运营中心（0-不是，1-是）
     */

    private String userCenter = "0";

    /**
     * 注册时间戳
     */
    private Long timeMill;

    /**
     * 支付密码
     */
    private String payPwd;

    /**
     * 个人消费
     */
    private BigDecimal totalConsume = BigDecimal.ZERO;

    /**
     * 部门消费
     */
    private BigDecimal deptConsume = BigDecimal.ZERO;

    /**
     * 最高消费金额
     */
    private BigDecimal userHigh=BigDecimal.ZERO;

    /**
     * 推荐人昵称
     */

    private String recomAlias;

    /**
     * 推荐人手机号
     */

    private String recomMbile;

    /**
     *起始时间
     */
    private String startTime;

    /**
     *截止之间
     */
    private String endTime;

    /**
     * hpy代理商0是1不是
     */
    private String hpyAgency;

    public void setTbUserAccount(TbUserAccount account) {

    }

    public TbUserAccount getTbUserAccount() {
        return null;
    }
}