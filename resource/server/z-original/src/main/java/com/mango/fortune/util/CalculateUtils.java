package com.mango.fortune.util;

import java.math.BigDecimal;
import java.util.Date;

import com.mango.core.common.util.Arith;
import com.mango.core.common.util.DateUtils;
import com.mango.fortune.product.enums.PeriodType;
import com.mango.fortune.product.enums.RateConvertStrategy;

/**
 * 
 * 类的描述 台账中各类金额计算的工具类
 * 
 * @author terryrao
 * @version 1.0 Jun 10, 2014
 * 
 */
public class CalculateUtils {

	/**
	 * 将年利率转成日利率或者月利率 月利率
	 * 
	 * @param rate
	 *            年利率
	 * @param strategy
	 *            将一年分割成strategy份
	 * @return
	 */
	public static BigDecimal convertRate(BigDecimal rate, PeriodType unit) {
		// 转换成相应日利率或月利率
		BigDecimal temp = BigDecimal.ZERO;
		if (rate == null) {
			return BigDecimal.ZERO;
		}
		// 如果单位或时间为空，则默认为计算年管理费
		if (unit == null || unit.getValue().equals("")) {
			temp = rate;
		}
		// 如果是unit == "月" 利率=managementFee/360*30=managementFee/12
		if (PeriodType.MONTH.equals(unit)) {
			temp = rate.divide(RateConvertStrategy.MONTH.getValue(),
					Arith.PRECISION_SCALE + 6, BigDecimal.ROUND_HALF_UP); // 月利率
		}
		// 如果 unit=="日" 则换算成日利率 2014-09-12 变为360天利率
		/*
		 * if (CycleUnits.DAY.equals(unit)) { temp =
		 * rate.divide(RateConvertStrategy.DAY360.getValue(),
		 * Arith.PRECISION_SCALE + 6, BigDecimal.ROUND_HALF_UP); }
		 */

		return temp;
	}

	/**
	 * 以每月360计算日利率
	 * 
	 * @param rate
	 * @return
	 */
	public static BigDecimal getDayRatBy360(BigDecimal rate) {
		return rate.divide(RateConvertStrategy.DAY360.getValue(),
				Arith.PRECISION_SCALE, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 一个数字*百分比
	 * 
	 * @param number
	 * @param rate
	 * @return
	 */
	public static BigDecimal calculateBigDecimalMupltiplyRate(
			BigDecimal number, BigDecimal rate) {
		return number.multiply(rate).divide(new BigDecimal(100),
				Arith.PRECISION_SCALE, BigDecimal.ROUND_HALF_DOWN);
	}

	private CalculateUtils() {
	}

	/**
	 * 7.5%×89/365
	 * 
	 * @param rate
	 * @param unit
	 * @return
	 */
	public static BigDecimal convertDayRateBy365(BigDecimal rate) {
		// 转换成相应日利率或月利率
		BigDecimal temp = BigDecimal.ZERO;
		if (rate == null) {
			return BigDecimal.ZERO;
		}

		// 换算成日利率 变为365天利率
		temp = rate.divide(RateConvertStrategy.DAY365.getValue(),
				Arith.PRECISION_SCALE + 6, BigDecimal.ROUND_HALF_UP);

		return temp;
	}


	/**
	 * i —— 不计时间成本时每付息周期利率 = 预期年化收益率*付息周期/12月
	 */
	public static BigDecimal monthRate(BigDecimal rate, BigDecimal interestCycle) {
		return rate.multiply(interestCycle).divide(new BigDecimal(12),
				Arith.PRECISION_SCALE + 6, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 年金终值系数 = [(1+i)^n-1]/i
	 */
	public static BigDecimal calculateRatio(BigDecimal monthRate,
			BigDecimal interestFreq) {
		return new BigDecimal(Math.pow(
				(new BigDecimal(1).add(monthRate)).doubleValue(),
				interestFreq.doubleValue())).subtract(new BigDecimal(1))
				.divide(monthRate, Arith.PRECISION_SCALE + 6,
						BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 每付息周期付息金额 = 产品预期收益累计/ 年金终值系数
	 * 
	 * @param exceptTotalRevenue
	 * @param ratio
	 * @return
	 */
	public static BigDecimal calculateOneTimeRevenue(
			BigDecimal exceptTotalRevenue, BigDecimal ratio) {
		return Arith.getReturnRoundUp(exceptTotalRevenue.divide(ratio,
				Arith.PRECISION_SCALE + 6, BigDecimal.ROUND_HALF_UP));
	}

	// 按月计算收益
	public BigDecimal calculateTotalRevenueByMonth(BigDecimal investmentAmount,
			BigDecimal productIncomeRate, PeriodType unit,
			BigDecimal productCycle) {
		if (investmentAmount == null) {
			return BigDecimal.ZERO;
		}
		if (productIncomeRate == null) {
			return investmentAmount;
		}
		BigDecimal rate = BigDecimal.ZERO;

		rate = productIncomeRate.multiply(productCycle);
		BigDecimal investmentRat = CalculateUtils.convertRate(rate, unit);

		return calculateTotalRevenue(investmentAmount, investmentRat);
	}

	private static BigDecimal calculateTotalRevenue(BigDecimal investmentAmount,
			BigDecimal productIncomeRate) {
		return CalculateUtils.calculateBigDecimalMupltiplyRate(
				investmentAmount, productIncomeRate);
	}

	// 按日计算收益
	public static BigDecimal calculateTotalRevenueByDay(BigDecimal investmentAmount,
			BigDecimal productIncomeRate, Date beginDate, Date endDate) {
		if (investmentAmount == null) {
			return BigDecimal.ZERO;
		}
		if (productIncomeRate == null) {
			return investmentAmount;
		}

		BigDecimal rate = BigDecimal.ZERO;
		rate = CalculateUtils.convertDayRateBy365(productIncomeRate);
		long day = getCalculateDays(beginDate, endDate);
		// 到期的收益利率
		BigDecimal investmentRat = rate.multiply(new BigDecimal(day));
		return calculateTotalRevenue(investmentAmount, investmentRat);
	}

	private static long getCalculateDays(Date beginDate, Date endDate) {
		return DateUtils.getDateSubDays(beginDate, endDate) + 1;
	}
}
