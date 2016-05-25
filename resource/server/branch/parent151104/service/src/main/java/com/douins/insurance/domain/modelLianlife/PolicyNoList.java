package com.douins.insurance.domain.modelLianlife;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class PolicyNoList {
	
	@XStreamImplicit(itemFieldName="PolicyNoItem")
	private List<PolicyNoItem> policynolist;

	public List<PolicyNoItem> getPolicynolist() {
		return policynolist;
	}

	public void setPolicynolist(List<PolicyNoItem> policynolist) {
		this.policynolist = policynolist;
	}
	
	

}
