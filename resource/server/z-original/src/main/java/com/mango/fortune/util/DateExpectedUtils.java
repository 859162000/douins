package com.mango.fortune.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mango.core.common.config.DaysDtl;
import com.mango.core.common.service.LabelManager;
import com.mango.core.common.util.DateUtils;
import com.mango.fortune.product.enums.PeriodType;

/**
 * 
 * 
 * 做为计算台账预计到期时间计算的工具类
 * 
 * @author raowei
 * @version 1.0 Jun 27, 2014
 * 
 */
public class DateExpectedUtils {

	

	/**
	 * 获取预计到期投资时间
	 * 
	 * @param lendDate
	 *            出借日期
	 * @param unit
	 *            产品收益的单位
	 * @param productCycle
	 *            产品计息时长
	 * @return
	 */
	public static Date expectedDate(Date lendDate, String unit, BigDecimal productCycle) {
		if(lendDate == null)
			return null;
		Date expectedDate = null;
		//到期日期 = 出借日期 + 3个月 - 1天 如：出借日期为 2015年2月9日，则到期日期为 2015年5月8日；实际天数为89天
		Date payMoneyMode = getPayMomeyMode(lendDate, productCycle, unit);
		if(payMoneyMode.getDate() < lendDate.getDate()){
			expectedDate = payMoneyMode;
		}else{
			expectedDate = DateUtils.addDay(getPayMomeyMode(lendDate, productCycle, unit), -1);
		}
		
		return expectedDate;
	}

	/**
	 * 获取出借日期
	 * 
	 * @param payTime
	 *            订单支付时间
	 * @return
	 */
	public static Date getLendDate(Date payTime) {
		Date lendDate = null;
		if (payTime == null) {
			return lendDate;
		}
		lendDate = modeOfT1(DateUtils.addDay(payTime, 1));		
		return lendDate;
	}

	// 当日开始计算投资到期时间 
	private static Date getPayMomeyMode(Date lenderDate, BigDecimal cycle, String unit) {
		if (unit.equals(PeriodType.DAY.getValue())) {
			return DateUtils.addDay(lenderDate, cycle.intValueExact());// 投资天数从出借当天开始算起 需要减去一天
		} else if (unit.equals(PeriodType.MONTH.getValue())) { // 投资天数从出借当天开始算起，需要减去一天
			return DateUtils.addMonths(lenderDate, cycle.intValueExact());
		}else if (unit.equals(PeriodType.YEAR.getValue())) { // 投资天数从出借当天开始算起，需要减去一天
			return DateUtils.addYear(lenderDate, cycle.intValueExact());
		}
		return null;
	}

	/* T+1 算法 */
	private static Date modeOfT1(Date receivedDate) {
		int days = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(receivedDate);
		Date temp = receivedDate;
		while (!isWorkDay(temp)) {
			days++;
			temp = DateUtils.addDay(temp, 1);
		}
		return DateUtils.addDay(receivedDate, days);
	}

	public static boolean isWorkDay(Date date) {
		List<DaysDtl> list = LabelManager.getHolidayList();
		for (DaysDtl daysDtl : list) {
			Date day = daysDtl.getDayValue();
			if (DateUtils.isEqual(day, date)) {
				if (daysDtl.isWork()) {
					return Boolean.TRUE;
				} else {
					return Boolean.FALSE;
				}
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	/**
	 * 计算某个日期之后的days的工作日
	 * @param receivedDate
	 * @param days
	 * @return
	 */
	public static Date afterDaysWorkDay(Date receivedDate, int days) {
		int j = 0;
		for(int i = 0; i < days; i++){
			Date temp = DateUtils.addDay(receivedDate, i + j + 1);
			while (!isWorkDay(temp)) {
				j++;
				temp = DateUtils.addDay(temp, 1);
			}
		}
		return DateUtils.addDay(receivedDate, days + j);
	}
	
	public static Date validateDate(Date date) {
		Calendar calendar = DateUtils.calendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	public static Date expireDate(Date date) {
		Calendar calendar = DateUtils.calendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
}
