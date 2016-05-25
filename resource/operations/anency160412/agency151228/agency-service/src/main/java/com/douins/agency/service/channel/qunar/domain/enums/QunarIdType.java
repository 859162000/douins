/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.enums;

/** 
* @ClassName: QunarIdType 
* @Description: Qunar 证件类型
* @author G. F. 
* @date 2016年1月12日 下午8:07:16 
*  
*/
public enum QunarIdType {
    ID_CARD("1", "0","身份证"),
    PASSPORT("2", "1", "护照"),
    MILITARY_ID("3", "2", "军官证"),
//    HVPS("4", "3", "回乡证"),
    MTPS("5", "4", "台胞证"),
//    INT_SEAMAN("6",  "5", "国际海员证"),
    OTHER("9", "99", "其他证件"),
//    HK_M_TW_PERMIT("8", "6","港澳通行证"),
//    TW_ENTRY("9", "7", "赴台证"),
//    TW_PERMIT("13", "8", "台湾通行证"),
    ;
    
    private String value;
    private String name;
    private String code;        // 豆芽的ID号
    /**
     * 
     */
    private QunarIdType(String value,String code, String name) {
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取豆芽的 ID 类型号
     * @param val
     * @return
     */
    public static String getCodeByValue(String val){
        String cd = QunarIdType.ID_CARD.getCode();
        for(QunarIdType type: QunarIdType.values()){
            if(type.getValue().equals(val))
                cd = type.getCode();
        }
        return cd;
    }
}
