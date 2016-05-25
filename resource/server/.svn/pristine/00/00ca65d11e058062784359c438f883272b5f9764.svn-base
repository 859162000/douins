package com.douins.trans.domain.enums;


public enum ResponseCode {
	
	SUCCESS("000000","成功"),
	
	RESPOSE_NULL("100000", "交易返回数据为空"),
	
	FAILED("999999","失败"),
	DATAREAD_ERROR("900001","传入数据错误"),
	INVALID_SESSION("900003","用户未登录或超时"),
	
	USERNOTEXIST("100000", "用户不存在或账号已被注销"),
	USERPASSWORD_1("100001","原登陆密码错误"),
	USERPASSWORD_2("100002","原交易密码错误"),
	USERPASSWORD_3("100003","原手势密码错误"),
	USERPASSWORD_6("100005","原密码和现密码重复！"),
	USERPASSWORD_4("100004","用户名或密码错误"),
	USERREGIST_1("100006","该账号已存在"),
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
	USERNOTOPENERROR("100017","账户未开户"),
	USEROPENINFOERROR("100018","用户开户信息错误"),
	CUSTOMEREXISTSERROR("100019","该客户信息已存在"),
	CUSTOMERNOTEXISTSERROR("100020","该客户信息不存在"),
	USERBALANCEERROR("100021","账户余额不足"),
	SMSNOTSEND("100022","短信发送失败"),
	USERREGIST_2("100023","该账号未注册"),
	USERNAMENULL("100024", "用户姓名为空"),
	USERCARDNULL("100025", "用户身份证号为空"),
	USEROPENINFOSUCCESS("100028","身份证初步审核通过"),
	USERCARDNUMBER("100029", "身份证位数不对"), 
	USERCARDNUMBEREXIT("100030", "身份证已存在"), 
	USERCARDNUMBERNULL("100029", "身份证不能为空"), 
	USERPHONENULL("100026", "用户手机号为空"),
	USERNOTBINDCARD("100027", "用户未绑卡"),
	
	HASBINDCARD("100031","只能绑定一张银行卡"),
	ACCOUNTHASBALANCE("100032","电子账户有余额不能解绑"),
	PHONENOTMATCH("100033","预留手机号不匹配"),
	NOTSUPPORTCARD("100034","暂不支持该卡"),
	
	
	
	
	
	POLICYISNOTEXISTS("200010","保单不存在"),
	POLICYTOTALPREM("200011","保单总金额错误"),
	POLICYNOTLOAN("200012","保单不是有效状态，不能贷款"),
	POLICYNOTSURRENDER("200013","保单不是有效状态，不能退保"),
	POLICYNOTREPAY("200014","保单不是贷款状态，不需要还款"),
	POLICYLOANAMOUNT("200015","申请贷款金额超过上限"),
	POLICYNOTPAY("200016","保单不是待支付状态，不能支付"),
	POLICYFAILED("200017","核保失败！"),
	
	INVALID_TOKEN("300000", "请先登录"),
	EXPIRED_TOKEN("300001", "登录已过期，请重新登录"),
	
	// ********  银行业务相关占用如下码段，请避让 *********** //
	// ***************** 400000 ~ 499999 ******************** //
	BANK_SUCCESS("400000", "交易(处理)成功"),
	BANK_FAILED("400001", "交易(处理)失败"),
	BANK_ACC_EXIST("400002", "电子账户已开通，请勿重复开户"),
	BANK_SYN_ERROR("400003", "账户信息同步失败"),
	BANK_SIGN_ERROR("400004", "验签失败"), 
	BANK_MORE_REGIST("E00085", "注册过于频繁"), 
	
	USERMOBILE_1("PTP00007","该手机号已存在"),
	USERCERTCODEILLEGAL("E0300060003","身份证非法!"),
	TIMEOUTCONVERT("9999","请求超时"),//对方系统抛出异常，我们做一次转换
	TIMEOUT("9001","通信超时"),
	
	CARDISNOTNAME("E0300062005","客户户名不符"),
	CARDEXISTLETTER("M14701","证件号码不可含有小写字母"), 
	
	//TODO 增加所有记录
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

	public String getName() {
		return name;
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
