package com.baby.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;



/**
 * '金融计算' 帮助类
 * 
 * @author Kival
 */
public final class FinanceCalcUtils {
    
    /**
     * '计算每期还款总金额'
     * @param repaymentType 还款类型
     * @param borrowAmount 借款金额
     * @param yearRate 年化率
     * @param period 还款期数(第几期)
     * @param repaymentMonth 还款总期数
     * @return 计算后的每期还款总金额
     */
    public static long calcMonthRepaymentAmount( int repaymentType, long borrowAmount, 
                                                 int yearRate, int period, int repaymentMonth ) {
        
        // 每期还款总金额
        BigDecimal monthRepaymentAmount = BigDecimal.ZERO;
        
        // 借款金额
        BigDecimal bdBorrowAmount = new BigDecimal(borrowAmount);
        
        // 计算月利率
        BigDecimal monthRate = calcMonthRate(yearRate);
        
        // 等额本息
        if( repaymentType == RepaymentTypeConst.FIXED_PAYMENT ) {
            
            // 只借1个月
            if( 1 == repaymentMonth ) {
                monthRepaymentAmount = bdBorrowAmount.add(bdBorrowAmount.multiply(monthRate));
                return monthRepaymentAmount.longValue();
            }
            
            // 借1个月以上
            BigDecimal temp1 = bdBorrowAmount.multiply(monthRate);
            BigDecimal temp2 = (BigDecimal.ONE.add(monthRate)).pow(repaymentMonth);
            BigDecimal temp3 = (BigDecimal.ONE.add(monthRate)).pow(repaymentMonth).subtract(BigDecimal.ONE);
            
            // 算出每月还款
            monthRepaymentAmount = temp1.multiply(temp2).divide(temp3, SystemLimitConst.INTEREST_CALC_SCALE, RoundingMode.DOWN);
            
            return monthRepaymentAmount.longValue();
            
        }// if( repaymentType == RepaymentTypeConst.FIXED_PAYMENT );
        
        // 先息后本
        if( repaymentType == RepaymentTypeConst.BEFORE_INTEREST ) {
            
            // 计算每月利息
            BigDecimal monthInterest = bdBorrowAmount.multiply(monthRate);
            
            // 如果还款期数是最后一期
            if( period == repaymentMonth ) {
                // 就要连息带本一起作为待还总金额
                monthRepaymentAmount = bdBorrowAmount.add(monthInterest);
            } else {
                // 如果还款期数不是最后一期，那么只需要还利息即可
                monthRepaymentAmount = monthInterest;
            }// if( period == repaymentMonth );
            
            return monthRepaymentAmount.longValue();
            
        }// if( repaymentType == RepaymentTypeConst.BEFORE_INTEREST );
        
        return monthRepaymentAmount.longValue();
    }
    
    
    /**
     * '计算每期还款利息'
     * @param repaymentType 还款类型
     * @param borrowAmount 借款金额
     * @param yearRate 年化率
     * @param period 还款期数(第几期)
     * @param repaymentMonth 还款总期数
     * @return 计算后的每期还款利息
     */
    public static long calcMonthInterest( int repaymentType, long borrowAmount, 
                                                 int yearRate, int period, int repaymentMonth ) {
        // 每期还款利息
        BigDecimal monthInterest = BigDecimal.ZERO;
        
        // 借款金额
        BigDecimal bdBorrowAmount = new BigDecimal(borrowAmount);
        
        // 计算月利率
        BigDecimal monthRate = calcMonthRate(yearRate);
    
        // 等额本息
        if( repaymentType == RepaymentTypeConst.FIXED_PAYMENT ) {
            
            // 只借1个月
            if( 1 == repaymentMonth ) {
                monthInterest = bdBorrowAmount.multiply(monthRate);
                return monthInterest.longValue();
            }
            
            // 借1个月以上
            BigDecimal temp1 = bdBorrowAmount.multiply(monthRate);
            BigDecimal temp2 = (BigDecimal.ONE.add(monthRate)).pow(repaymentMonth);
            BigDecimal temp3 = (BigDecimal.ONE.add(monthRate)).pow(repaymentMonth).subtract(BigDecimal.ONE);
            BigDecimal temp4 = (BigDecimal.ONE.add(monthRate)).pow(period - 1);
            
            // 算出每月还款
            BigDecimal monthRepaymentAmount = temp1.multiply(temp2).divide(temp3, SystemLimitConst.INTEREST_CALC_SCALE, RoundingMode.DOWN);
            // 算出总还款
            BigDecimal totalRepaymentAmount = monthRepaymentAmount.multiply(BigDecimal.valueOf(repaymentMonth));
            // 算出总利息
            BigDecimal totalInterest = totalRepaymentAmount.subtract(bdBorrowAmount);
            
            // 如果还款期数不是最后一期
            if( period < repaymentMonth ) {
                monthInterest = (temp1.subtract(monthRepaymentAmount)).multiply(temp4).add(monthRepaymentAmount);
                return monthInterest.longValue();
            }
            
            // 如果还款期数是最后一期
            BigDecimal temp5 = BigDecimal.ZERO;
            
            // 汇总最后一期之前的所有利息之和
            for( int i = 1; i < repaymentMonth; i++ ) {
                
                BigDecimal tempBD = (BigDecimal.ONE.add(monthRate)).pow(i - 1);
                monthInterest = (temp1.subtract(monthRepaymentAmount)).multiply(tempBD).add(monthRepaymentAmount);
                temp5 = temp5.add(monthInterest);
                
            }// for( int i = 1; i < repaymentMonth; i++ );
            
            monthInterest = totalInterest.subtract(temp5);
            
            return monthInterest.longValue();
            
        }// if( repaymentType == RepaymentTypeConst.FIXED_PAYMENT );
        
        // 先息后本
        if( repaymentType == RepaymentTypeConst.BEFORE_INTEREST ) {
            
            monthInterest = bdBorrowAmount.multiply(monthRate);
            return monthInterest.longValue();
        }// if( repaymentType == RepaymentTypeConst.BEFORE_INTEREST );
    
        return monthInterest.longValue();
    }
    
    /**
     * '计算待收本金'
     * @param bidAmount 投标金额
     * @param borrowAmount 借款总金额
     * @param borrowPrincipal 借款本金
     * @return 计算后的待收本金
     */
    public static long calcPrincipalPending( long bidAmount, long borrowAmount, long borrowPrincipal ) {
        
        // 待收本金
        BigDecimal principalPending = BigDecimal.ZERO;
        
        // 投标金额
        BigDecimal bdBidAmount = new BigDecimal(bidAmount);
        
        // 借款总金额
        BigDecimal bdBorrowAmount = new BigDecimal(borrowAmount); 
        
        // 借款总本金
        BigDecimal bdBorrowPrincipal = new BigDecimal(borrowPrincipal);
        
        principalPending = bdBidAmount.divide(bdBorrowAmount, SystemLimitConst.INTEREST_CALC_SCALE, RoundingMode.DOWN).multiply(bdBorrowPrincipal);
        
        return principalPending.longValue();
    }
    
    
    /**
     * '计算待收利息'
     * @param bidAmount 投标金额
     * @param borrowAmount 借款总金额
     * @param borrowTotalInterest 借款总回报利息
     * @return 计算后的待收利息
     */
    public static long calcInterestPending( long bidAmount, long borrowAmount, long borrowTotalInterest ) {
        
        // 待收利息
        BigDecimal interestPending = BigDecimal.ZERO;
        
        // 投标金额
        BigDecimal bdBidAmount = new BigDecimal(bidAmount);
        
        // 借款总金额
        BigDecimal bdBorrowAmount = new BigDecimal(borrowAmount); 
        
        // 借款总回报利息
        BigDecimal bdBorrowTotalInterest = new BigDecimal(borrowTotalInterest);
        
        interestPending = bdBidAmount.divide(bdBorrowAmount, SystemLimitConst.INTEREST_CALC_SCALE, RoundingMode.DOWN).multiply(bdBorrowTotalInterest);
        
        return interestPending.longValue();
    }
    
    /**
     * '计算借款总利息'
     * @param repaymentType 还款类型
     * @param borrowAmount 借款金额
     * @param yearRate 年化利率
     * @param repaymentMonth 还款期数
     * @return 计算后的借款总利息
     */
    public static long calcTotalInterest( int repaymentType, long borrowAmount, 
                                          int yearRate, int repaymentMonth ) {
        
        // 借款总利息
        BigDecimal totalInterest = BigDecimal.ZERO;
        
        // 借款金额
        BigDecimal bdBorrowAmount = new BigDecimal(borrowAmount); 
        
        // 计算月利率
        BigDecimal monthRate = calcMonthRate(yearRate);
        
        // 等额本息
        if( repaymentType == RepaymentTypeConst.FIXED_PAYMENT ) {
            
            // 只借款1个月
            if(repaymentMonth == 1 ) {
                totalInterest = bdBorrowAmount.multiply(monthRate);
                return totalInterest.longValue();
            }
            
            // 借款1个月以上
            BigDecimal temp1 = bdBorrowAmount.multiply(monthRate);
            BigDecimal temp2 = (BigDecimal.ONE.add(monthRate)).pow(repaymentMonth);
            BigDecimal temp3 = (BigDecimal.ONE.add(monthRate)).pow(repaymentMonth).subtract(BigDecimal.ONE);
            
            // 算出每月还款
            BigDecimal monthRepaymentAmount = temp1.multiply(temp2).divide(temp3, SystemLimitConst.INTEREST_CALC_SCALE, RoundingMode.DOWN);
                
            // 算出总还款
            BigDecimal totalRepaymentAmount = monthRepaymentAmount.multiply(BigDecimal.valueOf(repaymentMonth));
            
            // 算出总利息
            totalInterest = totalRepaymentAmount.subtract(bdBorrowAmount);
            
            return totalInterest.longValue();
            
        }// if( repaymentType == RepaymentTypeConst.FIXED_PAYMENT );
        
        // 先息后本
        if( repaymentType == RepaymentTypeConst.BEFORE_INTEREST ) {
            
            // 计算每月利息
            BigDecimal monthInterest = bdBorrowAmount.multiply(monthRate);
            // 算出总利息
            totalInterest = monthInterest.multiply(BigDecimal.valueOf(repaymentMonth));
            
            return totalInterest.longValue();
        }// if( repaymentType == RepaymentTypeConst.BEFORE_INTEREST );
        
        return totalInterest.longValue();
    }
    
    /**
     * '根据年利率计算月利率'
     * @param yearRate 年利率(百分制整数)
     * @return 四舍五入后的年利率(万分制小数)
     */
    public static BigDecimal calcMonthRate( int yearRate ) {
        
        BigDecimal bdYearRate = new BigDecimal(yearRate);
        
        BigDecimal hundred = new BigDecimal(100); // 百分制
        
        BigDecimal monthNum = new BigDecimal(12); // 每年12个月份
        
        return bdYearRate.divide(hundred).divide(monthNum, SystemLimitConst.INTEREST_CALC_SCALE, RoundingMode.DOWN);
    }
    
}
