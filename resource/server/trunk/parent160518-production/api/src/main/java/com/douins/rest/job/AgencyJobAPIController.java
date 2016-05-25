package com.douins.rest.job;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douins.common.util.StringUtils;
import com.douins.policy.domain.model.PolicyData;
import com.douins.policy.service.PolicyMasterService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


@Controller
@RequestMapping("/agencyJob")
public class AgencyJobAPIController {
	@Autowired
	private PolicyMasterService policyMasterService;

	@RequestMapping(value = "/updateData", method = RequestMethod.POST)
	public String toDo(HttpServletRequest request) {
		int response = 0;
		Gson gson = new Gson();
		String data = request.getParameter("data");
		if (StringUtils.isEmpty(data))
			return "request data error!";
		List<PolicyData> policyDatas = gson.fromJson(data, new TypeToken<List<PolicyData>>() {
			private static final long serialVersionUID = 1L;
		}.getType());
		response = policyMasterService.updatePolicyWithAgency(policyDatas);
		// System.out.println("response========"+response);
		return String.valueOf(response);
	}
}
