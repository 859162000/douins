package com.douins.trans.domain.enums;


public enum TransType {
	USERPAYPASSWORDINIT("118","交易密码初始化"),
	USERGESTUREPASSWORDINIT("119","手势密码初始化"),
	USERGESTUREPASSWORDCHANGE("120","手势密码修改"),
	
	USERREGISTER("101","注册"),
	USERLOGIN("102","登录"),
	USERLOGINPASSWORDRESET("103","重置登陆密码"),
	USERLOGINPASSWORDCHANGE("104","登陆密码修改"),
	USERPAYPASSWORDRESET("105","重置交易密码"),
	USERPAYPASSWORDCHANGE("106","交易密码修改"),
	USERMOBILECHANGE("108","修改手机号"),
	USEREMAILCHANGE("109","修改邮箱"),
	USERNICKNAMECHANGE("110","修改昵称"),
	USERFIVEELECHANGE("111","修改五要素信息"),
	USERBANKOPEN("112","绑卡开户"),
	USERBANKCHANGE("113","变更银行卡"),
	USERBANKFIND("114","查询银行卡"),
	USERACCOUNTFIND("115","查询客户虚拟账户"),
	USERACCOUNTADDMINUS("116","充值/提现"),
	USERACCOUNTHISTORY("117","账户历史纪录"),
	
	CUSTOMERADD("201","新增客户"),
	CUSTOMERDETETE("202","删除客户"),
	CUSTOMERBASISCHANGE("203","修改客户五项基本信息"),
	CUSTOMERDETAILCHANGE("204","修改客户详细信息"),
	CUSTOMERFIND("205","查询客户列表"),
	
	POLICYUW("206","核保"),
	POLICYFIND("207","查询保单"),
	POLICYINSURE("208","承保并支付"),
	
	PRODUCTLIST("001","保险列表"),
	PRODUCTDETAIL("002","保险详情"),
	
	LOANREQUEST("301","申请贷款"),
	LOANCONFIRM("302","确认贷款"),
	MYLOANDETAIL("303","我的保单贷款"),
	REPAYREQUEST("304","申请还款"),
	REPAYCONFIRM("305","确认还款"),
	SURRENDERREQUEST("306","申请退保"),
	SURRENDERCONFIRM("307","确认退保")
	;
	
	private String value;
	private String name;
	
	private TransType(String value,String name){
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
		for(TransType type: TransType.values()){
			if(type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}
	
	public static String getNameByValue(Integer value){
		for(TransType type: TransType.values()){
			if(type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}
	
}
