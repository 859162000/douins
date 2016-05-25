/**
 * 
 */
package com.douins.agency.service.douins.domain.enums;

/** 
* @ClassName: InsuranceEnum 
* @Description: 保险公司枚举
* @author G. F. 
* @date 2016年1月1日 下午4:34:38 
*  
*/
public enum InsuranceEnum {
    CCIC("01", "大地"),
    ;
    
    private String code;
    private String name;
    
    private InsuranceEnum(String code, String name){
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
        for(InsuranceEnum type: InsuranceEnum.values()){
            if(type.getName().equals(name))
                return type.getCode();
        }
        return "";
    }
    
    public static String getNameByCode(String code){
        for(InsuranceEnum type: InsuranceEnum.values()){
            if(type.getCode().equals(code))
                return type.getName();
        }
        return "";
    }
}
