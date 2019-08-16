package com.it.cloud.ztlogin;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class TbUserAccount implements Serializable{	

	private static final long serialVersionUID = 1L;

    /**
     * 主键ID 自增
     * This field corresponds to the database column tb_user_account.ID
     *
     * @date 2018-12-05 16:34:58
     */  
	private Long id;
	
    /**
     * 用户ID
     * This field corresponds to the database column tb_user_account.USER_ID
     *
     * @date 2018-12-05 16:34:58
     */  
	private Long userId;

    /**
     * 0-普通账户，1-hpy1，2-hpy
     */
	private String accountCate;
	
    /**
     * 币种（USDT）
     * This field corresponds to the database column tb_user_account.COIN
     *
     * @date 2018-12-05 16:34:58
     */  
	private String coin;
	
    /**
     * 可用余额
     * This field corresponds to the database column tb_user_account.BALANCE
     *
     * @date 2018-12-05 16:34:58
     */  
	private BigDecimal balance;
	
    /**
     * 钱包不是锁定
     * This field corresponds to the database column tb_user_account.is_lock
     *
     * @date 2018-12-05 16:34:58
     */  
	private String isLock;
	
    /**
     * 冻结金额
     * This field corresponds to the database column tb_user_account.FROZEN_BALANCE
     *
     * @date 2018-12-05 16:34:58
     */  
	private BigDecimal frozenBalance;
	
    /**
     * 待释放金额
     * This field corresponds to the database column tb_user_account.RELEASE_BALANCE
     *
     * @date 2018-12-05 16:34:58
     */  
	private BigDecimal releaseBalance;
	
    /**
     * 状态
     * This field corresponds to the database column tb_user_account.STATUS
     *
     * @date 2018-12-05 16:34:58
     */  
	private String status;
	
    /**
     * This field corresponds to the database column tb_user_account.address
     *
     * @date 2018-12-05 16:34:58
     */  
	private String address;
	
    /**
     * This field corresponds to the database column tb_user_account.UPDATE_TIME
     *
     * @date 2018-12-05 16:34:58
     */  
	private Date updateTime;
	
    /**
     *
     * This field corresponds to the database column tb_user_account.RES1
     *
     * @date 2018-12-05 16:34:58
     */  
	private String res1;
	
    /**
     * 预留2
     * This field corresponds to the database column tb_user_account.RES2
     *
     * @date 2018-12-05 16:34:58
     */  
	private String res2;
	
    /**
     * 预留3
     * This field corresponds to the database column tb_user_account.RES3
     *
     * @date 2018-12-05 16:34:58
     */  
	private String res3;

    /**
     * 积分封顶值
     */
	private BigDecimal pointsPinnacle = new BigDecimal(60000);

    /**
     * 释放基数
     */
	private BigDecimal releaseBase = BigDecimal.ZERO;


    /**
     * 现金账户CNY
     */
    private BigDecimal cashBalance = BigDecimal.ZERO;
    /**
     * 锁仓积分
     */
    private BigDecimal forzenPoints = BigDecimal.ZERO;


    /**
     * 可用积分
     */
    private BigDecimal points = BigDecimal.ZERO;


    /**
     * 消费券账户
     */
    private BigDecimal ticketBalance = BigDecimal.ZERO;


    /**
     * HPY1
     */
    private BigDecimal hpyBalance = BigDecimal.ZERO;


    /**
     * HPY1
     */
    private BigDecimal reallyHpy = BigDecimal.ZERO;


    private Long version = 1L;


    /**
     * 手机号
     */
    private String userMobile;

    /**
     * 昵称
     */
    private String userAlias;


}