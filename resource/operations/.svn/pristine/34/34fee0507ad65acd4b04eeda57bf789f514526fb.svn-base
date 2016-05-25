/**
 * 
 */
package com.douins.agency.service.douins.domain.enums;

/** 
* @ClassName: ChannelCodeType 
* @Description: 渠道枚举
* @author G. F. 
* @date 2016年1月1日 下午4:28:35 
*  
*/
public enum ChannelEnum {
    QUNAR("01", "去哪儿"),
    ;
    
    private String code;
    private String name;
    
    private ChannelEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static String getCodeByName(String name){
        for(ChannelEnum type: ChannelEnum.values()){
            if(type.getName().equals(name))
                return type.getCode();
        }
        return "";
    }
    
    public static String getNameByCode(String code){
        for(ChannelEnum type: ChannelEnum.values()){
            if(type.getCode().equals(code))
                return type.getName();
        }
        return "";
    }
}
