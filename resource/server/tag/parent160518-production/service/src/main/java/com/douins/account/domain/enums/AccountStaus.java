/**
 * 
 */
package com.douins.account.domain.enums;

import org.apache.commons.lang.StringUtils;

/** 
* @ClassName: AccountStaus 
* @Description: 银行账户状态 
* @author G. F. 
* @date 2015年12月3日 下午4:17:54 
*  
*/
public enum AccountStaus {
    NEW_USER("0", "新用户"),
    BIND_CARD_NO("1", "未绑卡"),
    BIND_CARD_YES("2", "已绑卡"),
    NOTACTIVATED("3", "未激活"),
    ACTIVATED("4", "已激活"),
    NORMAL("5", "正常"),
    FROZEN("6", "已冻结"),
    DELETED("7", "已注销");

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

    private AccountStaus(String value, String desc) {
        this.value = value;
        this.desc = desc;

    }
    
    public static  String getValueOf(String desc) {
        if (StringUtils.isNotBlank(desc)) {
            for(AccountStaus type : AccountStaus.values()) {
                if (type.getDesc().equals(desc)) {
                    return type.getValue();
                }
            }
        }
        return "";
    }
    
    public static String getNameByValue(String value) {
        if (StringUtils.isNotBlank(value)) {
            for(AccountStaus type : AccountStaus.values()) {
                if (type.getValue().equals(value)) {
                    return type.getDesc();
                }
            }
        }
        return "";
    }
    
    public static AccountStaus getEnumByValue(String value) {
        if (StringUtils.isNotBlank(value)) {
            for(AccountStaus type : AccountStaus.values()) {
                if (type.getValue().equals(value)) {
                    return type;
                }
            }
        }
        return null;
    }
}
