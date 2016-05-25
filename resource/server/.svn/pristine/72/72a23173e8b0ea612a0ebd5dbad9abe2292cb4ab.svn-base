package com.douins.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.mango.core.common.util.DateUtils;


public class SeqNumUtils {
	
	public static final String ORDER_PREFIX = "10";
	public static final String LOAN_PREFIX = "11";
	public static final String REPAY_PREFIX = "12";
	public static final String SURRENDER_PREFIX = "13";
	public static final String PAY_PREFIX = "14";//保单购买
	public static final String DISCHARGE_PREFIX = "15";//充值
	public static final String WITHDRAW_PREFIX = "16";//提现
	
	
	private static SeqNumUtils obj = new SeqNumUtils();
	private static final SimpleDateFormat cf = new SimpleDateFormat("yyMMddHHmmss");
	
	private SeqNumUtils(){
		
	}
	public SeqNumUtils getInstance(){
		return obj;
	}
	/**
	 * 方法名称: getTransNo<br>
	 * 描述：获取交易流水号
	 * 作者: chendogjun
	 * 修改日期：2014年7月1日下午3:32:02
	 * @param tranSchnl 渠道
	 * @return
	 */
	public static String getTransNo(String tranSchnl){
		SimpleDateFormat tranDate = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat tranTime = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		String transNo = tranSchnl+tranDate.format(date)+tranTime.format(date);
		return transNo;
	}
	
	/**
	 * 
	 * @param channel	渠道：
	 * @return
	 */
	public static String getPayNumber(String channel){
		String datestr = cf.format(new Date());
		String head = channel +RandomStringUtils.randomNumeric(2);
		String orderNumber=head +datestr;
		return orderNumber;
	}
	
	/**
	 * 
	 * @param channel	渠道：ios
	 * @return
	 */
	public static String getOrderNumber(String channel){
		String datestr = cf.format(new Date());
		String orderNumber=channel+datestr;
		return orderNumber;
	}
	
	public static String geneTxSN(String prefix) {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		tmp = tmp % (999999 - 100000 + 1) + 100000;
		String txnssn = prefix + DateUtils.formatDatetime(new Date(), "yyMMddHHmmss") + tmp;
		return txnssn;
	}
	
	public static void main(String[] args){
		System.out.println(geneTxSN("10"));
	}
}
