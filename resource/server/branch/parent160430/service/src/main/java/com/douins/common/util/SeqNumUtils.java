package com.douins.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.mango.core.common.util.DateUtils;


public class SeqNumUtils {
	// 豆芽－00；保险-- 1x; 银行-- 3x
	public static final String ORDER_PREFIX = "00"; 
	public static final String LOAN_PREFIX = "10";         // 贷款
	public static final String REPAY_PREFIX = "11";        // 还款
	public static final String SURRENDER_PREFIX = "12";    // 退保
	public static final String BNAK_VERIFY_ID = "30";          // 身份核查认证
	public static final String BANK_REGIST_ACC = "31";     // 资金账户开户
	public static final String BANK_QUERY_ACC = "32";      // 查询账户信息
	public static final String PAY_PREFIX = "33";//保单购买, 银行支付
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
	
	/**
     * 生成订单号
     * @param chType    渠道类型代码：0-- 渠道；1-- 保险公司；2--银行
     * @param channel      交易方的通道代码
     * @param transType     交易类型
     * @return
     */
    public static String geneTransNo(String chType, String channel, String transType){
        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());
        tmp = tmp % (99 - 10 + 1) + 10;
        try {
            Thread.sleep(tmp >> 1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        StringBuilder strB = new StringBuilder();
        strB.append(chType);
        strB.append(channel);
        strB.append(transType);
//        strB.append(DateUtils.formatDatetime(new Date(), "yyMMddHHmmss"));
//        strB.append(tmp);
        strB.append(new Date().getTime());
        strB.append(tmp);
        
        return strB.toString();
    }
	
	public static void main(String[] args){
		System.out.println(geneTxSN("00"));
	}
}
