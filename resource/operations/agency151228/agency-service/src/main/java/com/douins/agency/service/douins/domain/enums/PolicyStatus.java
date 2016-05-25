/**
 * 
 */
package com.douins.agency.service.douins.domain.enums;

/** 
* @ClassName: PolicyStatus 
* @Description: 保单状态
* @author G. F. 
* @date 2016年1月10日 下午1:21:09 
*  
*/
public enum PolicyStatus {
    UN_EFFECTIVE("0", "已承保，保单尚未生效"),
    NORMAL("1", "保单已生效，处于有效期内"),
    APPLY_WITHDRAW("2", "已申请退保／契撤，待确认"),
    WITHDRAW("3", "已退保"),
    EXPIRED("4", "保单已过期"),
    ;
    
    private String value;
    private String name;
    
    private PolicyStatus(String value, String name){
        this.value = value;
        this.name = name();
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
}
