package com.douins.insurance.domain.modelLianlife;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class ApplyInfoList {
	
	@XStreamImplicit(itemFieldName="ApplyInfoItem")
	private List<ApplyInfoItem> applyinfolist;

	public List<ApplyInfoItem> getApplyinfolist() {
		return applyinfolist;
	}

	public void setApplyinfolist(List<ApplyInfoItem> applyinfolist) {
		this.applyinfolist = applyinfolist;
	}
	

}
