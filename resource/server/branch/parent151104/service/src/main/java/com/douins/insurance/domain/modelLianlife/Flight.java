package com.douins.insurance.domain.modelLianlife;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Flight {
	
	public Flight() {
	}

	public Flight(String fltNO) {
		this.fltNO = fltNO;
	}

	@XStreamAlias("FltNO")
	private String fltNO;

	public String getFltNO() {
		return fltNO;
	}

	public void setFltNO(String fltNO) {
		this.fltNO = fltNO;
	}
	
	

}
