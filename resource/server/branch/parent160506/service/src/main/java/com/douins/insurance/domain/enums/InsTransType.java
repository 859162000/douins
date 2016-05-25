/**
 * 
 */
package com.douins.insurance.domain.enums;

/** 
* @ClassName: InsTransType 
* @Description: 保险公司交易类型
* @author G. F. 
* @date 2015年12月11日 下午3:20:26 
*  
*/
public enum InsTransType {
    UNDERWRITE("01", "核保"),
    UNDERWRITE_2("02", "二次核保"),
    APPROVE("03", "承保交易"),
    SURRENDER_ACC("05", "退保费用核算"),
    SURRENDER_REQ("06", "退保申请"),
    ;
    
    private String value;
    private String name;
    
    private InsTransType(String value, String name){
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
    
    
}
