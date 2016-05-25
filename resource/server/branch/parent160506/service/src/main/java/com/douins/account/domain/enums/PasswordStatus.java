/**
 * 
 */
package com.douins.account.domain.enums;

import org.apache.commons.lang.StringUtils;

/** 
* @ClassName: PasswordStatus 
* @Description: 银行账户密码的状态
* @author G. F. 
* @date 2015年12月3日 下午4:25:04 
*  
*/
public enum PasswordStatus {
    UNSETTELED("0", "未设置"),
    SETTLED("1", "已设置");
    
    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private PasswordStatus(String value, String desc) {
        this.value = value;
        this.desc = desc;

    }
    
    public static  String getValueOf(String desc) {
        if (StringUtils.isNotBlank(desc)) {
            for(PasswordStatus type : PasswordStatus.values()) {
                if (type.getDesc().equals(desc)) {
                    return type.getValue();
                }
            }
        }
        return "";
    }
    
    public static String getNameByValue(String value) {
        if (StringUtils.isNotBlank(value)) {
            for(PasswordStatus type : PasswordStatus.values()) {
                if (type.getValue().equals(value)) {
                    return type.getDesc();
                }
            }
        }
        return "";
    }
    
    public static PasswordStatus getEnumByValue(String value) {
        if (StringUtils.isNotBlank(value)) {
            for(PasswordStatus type : PasswordStatus.values()) {
                if (type.getValue().equals(value)) {
                    return type;
                }
            }
        }
        return null;
    }
}
