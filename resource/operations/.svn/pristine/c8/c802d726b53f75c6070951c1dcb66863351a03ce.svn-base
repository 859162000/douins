package com.douins.agency.service.channel.qunarfinance.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.douins.agency.service.channel.qunarfinance.domain.vo.IssueStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.QueryStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.UWStructVo;
import com.douins.agency.service.channel.qunarfinance.domain.vo.WithdrawStructVo;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.douins.service.AdapterServiceIFC;
import com.douins.agency.service.douins.service.ChannelServiceIFC;
import com.douins.agency.service.douins.service.impl.QunarfnLianlifeAdaper;

/**
 * @ClassName: ProcessService 
 * @author panrui
 *
 */
@Service(Constants.QUNARFN_SERVICE)
public class ProcessService implements ChannelServiceIFC {
	Logger logger = Logger.getLogger(ProcessService.class);
	
	@Resource(name = Constants.QUNARFN_LIANLIFE_ADAPTER)
	private QunarfnLianlifeAdaper adapterServiceQNF;
	
	@Override
	public String doUW(String data) throws Exception {
		// TODO Auto-generated method stub
		
		UWStructVo uwStructVo = ConvertDataService.getUWDatasFromXml(data);
		String result = adapterServiceQNF.doUWLianlifeAgency(uwStructVo, data);
		return result;
	}
	
	@Override
	public String doQuery(String data){
		QueryStructVo queryStructVo = ConvertDataService.getQueryDatasFromXml(data);
		String result = adapterServiceQNF.doQueryAgency(queryStructVo, data);
		return result;
	}

	@Override
	public String doInsure(String data) throws Exception {
		// TODO Auto-generated method stub
		IssueStructVo issureStructVo = ConvertDataService.getIssueDatasFromXml(data);
		String result = adapterServiceQNF.doInsureAgency(issureStructVo,data);
		return result;
	}

	@Override
	public String doWithdraw(String data) throws Exception {
		// TODO Auto-generated method stub
		WithdrawStructVo withdrawReq = ConvertDataService.getWithdrawDatasFromXml(data);
		String result = adapterServiceQNF.doWithdarwAgency(withdrawReq, data);
		return result;
	}

	@Override
	public String doBalance(String data) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
