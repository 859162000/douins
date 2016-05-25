package com.douins.bank.domain.enums;

import java.util.ArrayList;

import com.douins.bank.domain.vo.BankTypeResponseVo;

public enum BankType {
	ICBC("01", "ICBC", "中国工商银行", "10000", "50000", ""),
	ABC("02", "ABC", "中国农业银行","10000", "50000", ""), 
	BC("03", "BC", "中国银行", "10000", "50000", ""), 
	CCB("04", "CCB", "中国建设银行", "10000", "50000", ""),
	CMBC("05", "CMBC", "招商银行", "10000", "50000", ""),
    NYBC("06", "NYBC", "南粤银行", "10000", "50000", ""),
    GFBC("07", "GFBC","广发银行", "10000", "50000", ""),
    ;

    private String code;    // 银行代码
    private String value;   // 银行缩写
	private String name;   // 银行名称
	private String everyLimit; // 每笔限额
	private String dailyLimit; // 每日限额
	private String note;

	private BankType(String code, String value, String name, String everyLimit,
			String dailyLimit, String note) {
	    this.code = code;
		this.value = value;
		this.name = name;
		this.everyLimit = everyLimit;
		this.dailyLimit = dailyLimit;
		this.note = note;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEveryLimit() {
		return everyLimit;
	}

	public void setEveryLimit(String everyLimit) {
		this.everyLimit = everyLimit;
	}

	public String getDailyLimit() {
		return dailyLimit;
	}

	public void setDailyLimit(String dailyLimit) {
		this.dailyLimit = dailyLimit;
	}

	public static String getValueByName(String name) {
		for (BankType type : BankType.values()) {
			if (type.getName().equals(name))
				return type.getValue();
		}
		return "";
	}

	public static String getNameByValue(String value) {
		for (BankType type : BankType.values()) {
			if (type.getValue().equals(value))
				return type.getName();
		}
		return "";
	}

	public static BankType getBankByName(String name) {
		for (BankType type : BankType.values()) {
			if (type.getName().equals(name))
				return type;
		}
		return null;
	}

	public static BankType getBankByCode(String value) {
		for (BankType type : BankType.values()) {
			if (type.getValue().equals(value))
				return type;
		}
		return null;
	}

	public static ArrayList<BankTypeResponseVo> getBankTypeList() {
		ArrayList<BankTypeResponseVo> bankTypeList = new ArrayList<BankTypeResponseVo>();
		for (BankType type : BankType.values()) {
			BankTypeResponseVo btVo = new BankTypeResponseVo(type.getValue(),
					type.getName(), type.getEveryLimit(), type.getDailyLimit(),
					type.getNote());
			bankTypeList.add(btVo);

		}
		return bankTypeList;

	}

}
