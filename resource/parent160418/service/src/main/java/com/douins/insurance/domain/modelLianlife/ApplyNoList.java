package com.douins.insurance.domain.modelLianlife;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
@XStreamAlias("ApplyNoList")
public class ApplyNoList {
	@XStreamImplicit(itemFieldName="ApplyNoItem")
	private List<ApplyNoItem> applynolist;

	public List<ApplyNoItem> getApplynolist() {
		return applynolist;
	}

	public void setApplynolist(List<ApplyNoItem> applynolist) {
		this.applynolist = applynolist;
	}
	
	

}
