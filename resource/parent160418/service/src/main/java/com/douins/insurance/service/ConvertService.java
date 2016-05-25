package com.douins.insurance.service;

import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyResult;

public interface ConvertService {
	
	  String toConvertRequest(PolicyMaster policyMaster,String transFlag,String transType);
	  PolicyResult toConvertResponse(String responseXml);

}
