
/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.enums;



/** 
* @ClassName: TransType 
* @Description: quanr 交易类型的枚举
* @author G. F. 
* @date 2015年12月31日 上午9:14:35 
*  
*/
public enum QunarTransType {
	MOTORDEALS("01","承保交易","policyIssuing"),
	PRINT("02","打印交易","policyPrinting"),
	WITHDRAW("03","契撤交易","policyWithdraw"),
	CONSTRUCTIONRISKUPLOAD("04","建工险上载交易","policyConstruct"),
	CONSTRUCTIONRISKWITHDRAW("05","建工险契撤","policyConstructWithdraw"),
	POLICYCHECK("06","对账","policyCheck"),
	QUERY("07","查询","policyQuery"),
	BEIJINGINSURANCESTATISTIC("08","北京乘意险统计","policyStatistic"),
	BEIJINGINSURANCEQUERYMAXBILLNO("09","北京乘意险最大单证号查询","policyQueryMaxBillNo"),
	CARDAPPLY("10","自助卡申请","policyCardApply"),
	CARDCANCEL("11","自助卡作废","policyCardCancel"),
	TRIAL("12","试算交易","policyTrial"),
	GROUPISSUING("14","团单承保交易","policyGroupIssuing"),
	GROUPWITHDRAW("15","团单契撤交易","policyGroupWithdraw"),
	UNDERWRITING("16","核保交易","policyTrial"),
	CONFIRMMOTORDEALS("17","确认承保交易","policyConfirmIssuing"),
	CERTNODOWNLOADER("18","凭证号批量下载接口","certNoDownloader"),
	CONFIRMQUERYPOLICYISSUING("19","确认承保查询","queryPolicyIssuing"),
	GROUPISSUINGPERSONALINSURANCE("20","团单个人承保交易","policyGroupIssuingPersonalInsurance"),
	QQPOLICYISSUING("23","QQ确认承保查询新接口","policyIssuing"),
	ONLINEWITHDRAWREVOKE("24","在线契撤申请接口","policyRevoke"),
	NETSELLREVOKEAPPLY("26","网销在线契撤申请接口","NetSellRevokeApply"),
	NETSELLREVOKECONFIRM("27","网销在线契撤确认接口","NetSellRevokeConfirm"),
	QUERYNETSELLREVOKECONFIRM("28","网销在线契撤确认重试查询接口","queryNetSellRevokeConfirm");
	
 
    private String value;
    private String name;
    private String function;
    
    private QunarTransType(String val, String name, String func){
        this.value = val;
        this.name = name;
        this.function = func;
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

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
    
	public static String getValueByFunction(String function){
		for(QunarTransType type: QunarTransType.values()){
			if(type.getFunction().equals(function))
				return type.getValue();
		}
		return "";
	}
	
	public static String getFunctionByValue(Integer value){
		for(QunarTransType type: QunarTransType.values()){
			if(type.getValue().equals(value))
				return type.getFunction();
		}
		return "";
	}
    
}
