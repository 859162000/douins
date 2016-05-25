package com.douins.common.params;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class WebUseConstant {
	public static Properties props = new Properties();
	
	//加载properties文件
	static {
		try {
			InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("constants.properties");

			props.load(ins);
			ins.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	//初始化常量
		

		NY_UNBIND_CARD_URL = props.getProperty("NY_UNBIND_CARD_URL");
		NY_BIND_CARD_URL = props.getProperty("NY_BIND_CARD_URL");
		CHANL_NO = props.getProperty("CHANL_NO", "PTP0000009");
		RECIVE_BANK_ACCOUNT =props.getProperty("RECIVE_BANK_ACCOUNT", "999001270040012");
		RECIVE_BANK_CODE =props.getProperty("RECIVE_BANK_CODE", "资金清算应付款-待划转款项");
		NY_BIND_CARD_CALLBACK_URL = props.getProperty("NY_BIND_CARD_CALLBACK_URL");
		NY_UNBIND_CARD_CALLBACK_URL = props.getProperty("NY_UNBIND_CARD_CALLBACK_URL");
		
		

	}

	
	//根据环境变的 ，写在contants.properties中
	public final static String CHANL_NO; //渠道编号
	public final static String NY_BIND_CARD_URL; //南粤绑卡URL
	public final static String NY_UNBIND_CARD_URL;// 南粤解绑URL
	public final static String NY_BIND_CARD_CALLBACK_URL; //南粤绑卡回调URL
	public final static String NY_UNBIND_CARD_CALLBACK_URL;// 南粤解绑回调URL
	
	public final static String  RECIVE_BANK_ACCOUNT;//收款账户
	public final static String  RECIVE_BANK_CODE;//收款账户代码
	
	
	//不变的常量，直接赋值
	public final static String RECIVE_BANK_USERNAME="利安人寿股份有限公司";
	public final static String RECIVE_USER_ID="LianLife-YL-Recive";

	



}
