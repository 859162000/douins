/**
 * 
 */
package com.douins.bank.domain.enums;

/** 
* @ClassName: BankTransType 
* @Description: 银行交易类型
* @author G. F. 
* @date 2015年12月10日 下午3:02:20 
*  
*/
public enum BankTransType {
    UNKNOWN("00", "未知交易类型","0"),
    VERIFT_ID("01", "身份核查","0"),
    REGIST_ACC("02", "资金账户开户","0"),
    QUERY_ACC("03", "查询账户信息","0"),
    PWD_SET("06", "设置交易密码","0"),
    PWD_CHANGE("07", "修改交易密码","0"),
    PWD_RESET("08", "重置交易密码","0"),
    BIND_CARD("09", "绑卡","0"),
    UNBIND_CARD("15","解绑","0"),
    RECHARGE("10", "电子账户充值","1"),
    WITHDRAW("11", "电子账户取现","-1"),
    PURCHASE("12", "购买/支付","-1"),
    REPAY("13", "还款","-1"),
    REFUND("14", "退款","1"),
    PROJECTE_REGIST("15","支付项目信息登记","0"),
    
    REGIST_ACC_REQ("21", "电子账户开户结果查询","0"),
    QUERY_SINGLE_RESULTS("22","电子账户单笔消费记录查询","0"),
    QUERY_BANK_NO("23","已签约银行查询","0"),
    QUERY_ACCOUNT_REQUEST("24","支付项目信息登记接口","0"),
    ;
    private String value;
    private String name;
    private String code;// 1:进账；-1:出账；0:其他
    
    private  BankTransType(String value, String name,String code){
        this.value = value;
        this.name = name();
        this.code =code;
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
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BankTransType getTypeByValue(String value){
        for(BankTransType type : BankTransType.values()){
            if(value.equals(type.getValue())){
                return type;
            }
        }
        
        return BankTransType.UNKNOWN;
    }
	public static String getCodeByValue(String value){
        for(BankTransType type : BankTransType.values()){
            if(value.equals(type.getValue())){
                return type.getCode();
            }
        }      
        return "";
    }
}
