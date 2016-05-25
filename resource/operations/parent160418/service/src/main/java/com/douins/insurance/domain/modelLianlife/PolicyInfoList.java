package com.douins.insurance.domain.modelLianlife;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class PolicyInfoList {
	@XStreamImplicit(itemFieldName="PolicyInfoItem")
	private List<PolicyInfoItem> policyinfolist;

	public List<PolicyInfoItem> getPolicyinfolist() {
		return policyinfolist;
	}

	public void setPolicyinfolist(List<PolicyInfoItem> policyinfolist) {
		this.policyinfolist = policyinfolist;
	}
	
	

}
