package com.mango.fortune.trans.enums;


public enum ResponseCode {
	
	SUCCESS("000000","成功"),
	
	FAILED("999999","失败"),
	DATAREAD_ERROR("900001","传入数据错误"),
	INVALID_SESSION("900003","用户未登录或超时"),
	
	USERPASSWORD_1("100001","原登陆密码错误"),
	USERPASSWORD_2("100002","原交易密码错误"),
	USERPASSWORD_3("100003","原手势密码错误"),
	USERPASSWORD_6("100005","原密码和现密码重复！"),
	USERPASSWORD_4("100004","用户名或密码错误"),
	USERREGIST_1("100005","该账号已存在"),
	USERMOBILE_1("100006","该手机号已存在"),
	USEREMAIL_1("100007","该邮箱已存在"),
	VERIFICATIONCODE_MOBILE("100008","手机验证码错误"),
	USERBANK_1("100009","该银行卡已绑定"),
	NORMAL1("100010","交易类型编码错误"),
	PASSWORDAUTH1("100011","证件号码错误"),
	PASSWORDAUTH2("100012","银行卡错误"),
	ORDERPAYPASSWORD("100013","交易密码错误"),
	USERPASSWORD_5("100014","请去登陆"),
	USERBINDING("100015","该账号已与微信号存在绑定关系"),
	USEROPENERROR("100016","账户已开户"),
	USERREGIST_2("100023","该账号未注册"),
	
	USERNOTOPENERROR("100017","账户未开户"),
	USEROPENINFOERROR("100018","用户开户信息错误"),
	CUSTOMEREXISTSERROR("100019","该客户信息已存在"),
	CUSTOMERNOTEXISTSERROR("100020","该客户信息不存在"),
	USERBALANCEERROR("100021","账户余额不足"),
	SMSNOTSEND("100022","短信发送失败"),
	
	POLICYISNOTEXISTS("200010","保单不存在"),
	POLICYTOTALPREM("200011","保单总金额错误"),
	POLICYNOTLOAN("200012","保单不是有效状态，不能贷款"),
	POLICYNOTSURRENDER("200013","保单不是有效状态，不能退保"),
	POLICYNOTREPAY("200014","保单不是贷款状态，不需要还款"),
	POLICYLOANAMOUNT("200015","申请贷款金额超过上限"),
	POLICYNOTPAY("200016","保单不是待支付状态，不能支付"),
	;
	
	
	
	private String value;
	private String name;
	
	ResponseCode(String value,String name){
		this.value = value;
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public static String getValueByName(String name){
		for(ResponseCode type: ResponseCode.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(String value){
		for(ResponseCode type: ResponseCode.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	
	
	public static ResponseCode getEnumByValue(String value){
		for(ResponseCode type: ResponseCode.values()){
			if(type.getValue().equals(value))
				return type;
		}
		return null;
	}
	
}
