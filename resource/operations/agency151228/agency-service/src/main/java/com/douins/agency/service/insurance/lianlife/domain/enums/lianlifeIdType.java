package com.douins.agency.service.insurance.lianlife.domain.enums;

public enum lianlifeIdType {
	
    ID_CARD("01", "0", "身份证"),
    HOUSE_CARD("02", "4",  "户口薄"),
    PASSPORT("03", "1" ,"护照"),
    OFFICER_CERTI("04", "2" ,"军官证"),
    DRIVER_LICENCE("05", "3", "驾驶执照"),
    STUDENT_ID_CARD("11", "5", "学生证"),
    WORK_CARD("25","6","工作证"),
    BIRTH_CARD("26","7","出生证"),
    OTHER_CARD("19", "8",  "无法收集证件资料"),
    NULL_CARD("27","9","无证件"),
    SOLDIER_CARD("28","A","士兵证"),
    RETURN_CARD("06", "B", "返乡证"),
    ID_CARD_TEMP("09", "C", "临时身份证");
	
	
    private String value;
    private String code;
    private String name;
    
    private lianlifeIdType(String value, String code, String name){
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
    

}
