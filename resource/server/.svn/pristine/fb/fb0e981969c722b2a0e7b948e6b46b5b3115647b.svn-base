package com.mango.fortune.product.enums;



import org.apache.commons.lang.StringUtils;
/**
 * 描述：计息方式<br>
 * 作者：wintrchen <br>
 * 修改日期：Jun 4, 20152:42:58 PM <br>
 * E-mail: winterchen@sinorfc.com <br>
 */
public enum CalType {
	DAY("1", "天"), MONTH("2", "月"),GROUP("3","组合");

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

	private CalType(String value, String desc) {
		this.value = value;
		this.desc = desc;

	}
	
	public static  String getValueOf(String desc) {
		if (StringUtils.isNotBlank(desc)) {
			for(CalType type : CalType.values()) {
				if (type.getDesc().equals(desc)) {
					return type.getValue();
				}
			}
		}
		return "";
	}
	
	public static String getNameByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(CalType type : CalType.values()) {
				if (type.getValue().equals(value)) {
					return type.getDesc();
				}
			}
		}
		return "";
	}
	
	public static CalType getEnumByValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			for(CalType type : CalType.values()) {
				if (type.getValue().equals(value)) {
					return type;
				}
			}
		}
		return null;
	}

}
