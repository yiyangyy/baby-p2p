package com.baby.common.utils;

/**
 * '系统限制值' 常量类
 * @author Kival
 *
 */
public final class SystemLimitConst {
    
    /** 系统货币单位：元(1元 = 100分) **/
    public static final long MONEY_UNIT = 100;
    
    /** 货币显示精度(元) **/
    public static final int MONEY_SHOW_SCALE = 2; 
    
    /** 利率运算精度(万分制) **/
    public static final int INTEREST_CALC_SCALE = 4;
    
    /** 最小投资金额(单位：分) **/
    public static final long MINIMUM_BID_AMOUNT = 50 * MONEY_UNIT;
    
    /** 最小借款金额(单位：分) **/
    public static final long MINIMUM_BORROW_AMOUNT = 500 * MONEY_UNIT;
    
    /** 最小提现金额(单位：分) **/
    public static final long MINIMUM_WITHDRAW_AMOUNT = 500 * MONEY_UNIT;
    
    /** 最小借款利息年化率(百分制) **/
    public static final long MINIMUM_YEAR_RATE = 5;
    
    /** 最大借款利息年化率(百分制) **/
    public static final long MAXIMUM_YEAR_RATE = 20;
    
}
