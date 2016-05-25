/**
 * 
 */
package com.douins.agency.service.douins.domain.enums;

import java.util.Map;

import com.google.common.collect.Maps;

/** 
* @ClassName: DouinsException 
* @Description: Douins 系统的异常枚举
* @author G. F. 
* @date 2015年12月29日 下午4:30:33 
*  
*/
public enum DouinsException {
    IP_ERROR("9999", "IP 地址错误或解析失败"),
    
    TRADE_TYPE_ERROR("2001", "交易类型不正确"),
    BACK_NULL("2002", "核心处理返回数据为空"),
    SEND_DATA_ERROR("4444", "Douins 数据发送/接收失败"),
    UW_DATA_ERROR("1000", "核保请求数据解析失败"),
    UW_DATA_DUPLICATE("1001", "该订单已经请求处理，请勿重复发送"),
    INSURE_DATA_ERROR("1005", "承保请求数据解析失败"),
    INSURE_DATA_EXIST("1006", "该订单号已经承保"),
    
    WITHDRAW_DATA_ERROR("1008", "退保／契撤请求数据失败"),
    ;
    
    private String value;
    private String name;
    
    private DouinsException(String value, String name){
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
    
    public static Map<String, String>  getErrorMap(DouinsException ex){
        Map<String, String> error = Maps.newHashMap();
        error.put("errorCode", ex.getValue());
        error.put("errorMsg", ex.getName());
        
        return error;
    }
}
