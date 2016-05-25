/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.enums;

import com.douins.agency.service.channel.qunar.domain.enums.QunarIdType;

/** 
* @ClassName: CCICIdType 
* @Description: CCIC 证件类型
* @author G. F. 
* @date 2016年1月12日 下午8:07:47 
*  
*/
public enum CCICIdType {
    ID_CARD("01", "0", "身份证"),
    HOUSE_CARD("02", "9",  "户口薄"),
    PASSPORT("03", "1" ,"护照"),
    OFFICER_CERTI("04", "2" ,"军官证"),
    DRIVER_LICENCE("05", "10", "驾驶执照"),
    RETURN_CARD("06", "3", "返乡证"),
    ORGNIZATION_CERTI("07", "5", "组织机构代码证"),
    SOLIDER_CARD("08", "12", "士兵证"),
    ID_CARD_TEMP("09", "13", "临时身份证"),
    POLICE_CERTI("10", "14", "警官证"),
    STUDENT_ID_CARD("11", "15", "学生证"),
    OFFICER_RETIR_CARD("12", "16", "军官离退休证"),
    HK_MACAU_PASSER("13", "6", "港澳通行证"),
    TAIWAN_PASSER("14", "8", "台湾通行证"),
    TRAVEL_CERTI("15", "17", "旅行证"),
    RESIDENCE_ID("16", "18", "居留证"),
    CHINESS_PASSER("17", "7", "中国护照"),
    MTP("18", "4", "台胞证"),
    OTHER_ID("19", "99",  "无法收集证件资料"),
    UNUSUAL_ID("20", "19", "异常身份证"),
    TAX_CERTI("21", "20", "税务登记证"),
    BUSINESS_LICENCE("22", "21",  "营业执照"),
    CHANESS_ORGNIZATION_CERTI("23", "22", "中华人民共和国组织机构代码证"),
    SOCIAL_SECURITY_CERTI("24", "11", "社保保险登记证"),

    ;
    
    private String value;
    private String code;
    private String name;
    
    private CCICIdType(String value, String code, String name){
        this.value = value;
        this.code = code;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
    
    /**
     * 根据机构证件代码获取Douins 的证件代码
     * @param value
     * @return
     */
    public static String getCodeByValue(String value){
        String cd = QunarIdType.OTHER.getCode();
        for(CCICIdType type: CCICIdType.values()){
            if(type.getValue().equals(value))
                cd = type.getCode();
        }
        return cd;
    }
    
    /**
     * 根据Douins 的证件代码获取机构的证件代码
     * @param code2
     * @return
     */
    public static String getValueByCode(String code2){
        String val = QunarIdType.OTHER.getValue();
        for(CCICIdType type: CCICIdType.values()){
            if(type.getCode().equals(code2))
                val = type.getValue();
        }
        return val;
    }
}
