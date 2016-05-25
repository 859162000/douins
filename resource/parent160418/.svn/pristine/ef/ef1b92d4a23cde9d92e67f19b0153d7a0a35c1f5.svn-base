/**
 * 
 */
package com.douins.apply.domain.enums;

/** 
* @ClassName: ApplyType 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:11:59 
*  
*/
public enum ApplyType {
    LOAN("LO","借款申请"),
    REPAY("RE","还款申请"),
    SURRENDER("SU","退保申请");;
    
    private String value;
    private String name;
    
    private ApplyType(String value,String name){
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
        for(ApplyType type: ApplyType.values()){
            if(type.getName().equals(name))
                return type.getValue();
        }
        return "";
    }
}
