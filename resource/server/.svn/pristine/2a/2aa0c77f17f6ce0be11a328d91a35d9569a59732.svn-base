/**
 * 
 */
package com.douins.apply.domain.enums;

/** 
* @ClassName: LoanType 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:14:55 
*  
*/
public enum LoanType {
    LOANNORMAL("1","正常借款") ;
    
    private String value;
    private String name;
    
    private LoanType(String value,String name){
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
    
    public static String getNameByValue(String value){
        for(LoanType type: LoanType.values()){
            if(type.getValue().equals(value))
                return type.getName();
        }
        return "";
    }

    public static String getValueByName(String name){
        for(LoanType type: LoanType.values()){
            if(type.getName().equals(name))
                return type.getValue();
        }
        return "";
    }
}
