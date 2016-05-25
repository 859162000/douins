/**
 * 
 */
package com.douins.apply.domain.enums;

/** 
* @ClassName: LoanStatus 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月20日 下午2:13:05 
*  
*/
public enum LoanStatus {
    LOANING("1","借款申请"),
    LOANFAILED("2","借款审核失败"),
    LOANPAYMENT("3","借款打款中"),
    LOANINGEND("4","借款完成"),
    LOANFINISHED("5","已还款")
    ;
    
    private String value;
    private String name;
    
    private LoanStatus(String value,String name){
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
        for(LoanStatus type: LoanStatus.values()){
            if(type.getValue().equals(value))
                return type.getName();
        }
        return "";
    }

    public static String getValueByName(String name){
        for(LoanStatus type: LoanStatus.values()){
            if(type.getName().equals(name))
                return type.getValue();
        }
        return "";
    }
}
