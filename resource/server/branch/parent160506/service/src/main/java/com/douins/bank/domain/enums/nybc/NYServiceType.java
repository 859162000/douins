/**
 * 
 */
package com.douins.bank.domain.enums.nybc;

/** 
* @ClassName: NYServiceType 
* @Description: nybc 的服务代码
* @author G. F. 
* @date 2015年12月6日 上午9:51:36 
*  
*/
public enum NYServiceType {
    Error("0000", "00", "未知项"),
    VerifyIdentity("PTP1001", "01", "身份核查"),
    RegistAccount("PTP1001",  "02", "注册电子账户"),
    QueryAccount("PTP1001",  "03", "账户查询"),
    BalanceQuery("PTP1003", "01", "对账单查询"),
    RepayQuery("PTP1005", "01", "还款查询"),
    Repay("PTP1006", "01", "自助还款"),
    SMSMSG("PTP1006", "02", "短信验证"),
	ProjecteRegist("PTP1007","10","支付项目信息登记"),
	QueryAccountRequest("PTP1003","02","业务状态流水查询接口");
    
    private String code;
    private String scene;
    private String name;
    
    private NYServiceType(String code, String scene, String name){
        this.code = code;
        this.scene = scene;
        this.name = name;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCodeAndScene(String code, String scene){
        for(NYServiceType type : NYServiceType.values()){
            if(type.getCode().equals(code) && type.getScene().equals(scene)){
                return type.getName();
            }
        }
        
        return "";
    }
    
    public NYServiceType getTypeByName(String name){
        for(NYServiceType type : NYServiceType.values()){
            if(type.getName().equals(name)){
                return type;
            }
        }
        
        return NYServiceType.Error;
    }
}
