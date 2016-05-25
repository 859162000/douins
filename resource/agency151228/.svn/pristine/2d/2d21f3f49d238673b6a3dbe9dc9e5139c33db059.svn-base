/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.enums;

/** 
* @ClassName: TransType 
* @Description: CCIC 交易类型及代码
* @author G. F. 
* @date 2015年12月30日 上午10:35:34 
*  
*/
public enum CCICTransType {
    UWANDINSURE("0010","试算承保"),
    UW("0011","试算"),
    INSURE("0012","承保"),
    CANCEL("0013","撤销"),
    CORRECT("0014","冲正"),
    GETMODIFYINDFO("0015","收集批改信息"),
    WITHDRAW("0016","退保"),
    MODIFYMEMBERINFO("0017","人员基本信息批改"),
    QUERY("0018","查询（重打）"),
    BALANCE("0019","业务对账"),
    POLICYNOTATION("0020","保单生效通知接口"),
    ORDERIFC("0021","下单接口");
    
    
    private String value;
    private String name;
    
    private CCICTransType(String value,String name){
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
        for(CCICTransType type: CCICTransType.values()){
            if(type.getName().equals(name))
                return type.getValue();
        }
        return "";
    }
    
    public static String getNameByValue(Integer value){
        for(CCICTransType type: CCICTransType.values()){
            if(type.getValue().equals(value))
                return type.getName();
        }
        return "";
    }
}
