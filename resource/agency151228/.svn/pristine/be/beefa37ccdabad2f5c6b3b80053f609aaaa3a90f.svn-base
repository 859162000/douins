package com.douins.agency.rest.job;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.agency.service.douins.service.LianConvertServiceIFC;

@Controller
@RequestMapping("/policyProfit")
public class PolicyProfitJob {
	@Resource(name = "lianConvertServiceIFC")
    private LianConvertServiceIFC lianConvertServiceIFC;
	
	/**
	 * 这个是手动执行调度，更新保险收益
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/job1")
	public String Job1(HttpServletRequest request){
		lianConvertServiceIFC.ValidationJob();
		return "success";
	}
	
	/**
	 * 这个也是手动执行，把退保的数据发给去哪儿
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/job2")
	public String Job2(HttpServletRequest request){
		lianConvertServiceIFC.PolicyJob();
		return "success";
	}
}
