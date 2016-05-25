package com.douins.agency.service.douins.service.database;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.douins.agency.service.channel.qunarfinance.domain.model.Response;
import com.douins.agency.service.common.util.DateTimeUtils;
import com.douins.agency.service.douins.dao.PolicyDataDao;
import com.douins.agency.service.douins.dao.PolicyReservationRequestDao;
import com.douins.agency.service.douins.dao.PolicyReservationResponseDao;
import com.douins.agency.service.douins.domain.model.PolicyData;
import com.douins.agency.service.douins.domain.model.PolicyReservationRequest;
import com.douins.agency.service.douins.domain.model.PolicyReservationResponse;
import com.google.common.collect.Maps;
@Service("lianDataService")
public class LianDataService {
	@Resource
    private PolicyReservationRequestDao policyReservationRequestDao;
	@Resource
	private PolicyReservationResponseDao reservationResponseDao;
	@Resource
	private PolicyDataDao policyDataDao;
	
	public int savePolicyReservation(PolicyReservationRequest record){
		return policyReservationRequestDao.insert(record);
	}

	public int savePolicyReservationResponse(PolicyReservationResponse record) {
		return reservationResponseDao.insert(record);
	}
	
	public List<PolicyData> queryPolicy(List<String> policyNos){
		Map<String, Object> params = Maps.newHashMap();
		params.put("list", policyNos);
	   	params.put("createTime",DateTimeUtils.formateDateTimeByHHmmss(" 00:01:00"));//取大于当天0点01分入库的数据
		return policyDataDao.queryPolicys(params);
	}
	
	public PolicyReservationResponse getResponesByPolicy(String policyNo){
		PolicyReservationResponse resp = new PolicyReservationResponse();
		Map<String, Object> params = Maps.newHashMap();
		params.put("tradeCode", "20002");
		params.put("policyNo", policyNo);
		params.put("responseCode", "0");
		params.put("isSuccess","1");
		params.put("createTime", DateTimeUtils.formateDateTimeByHHmmss(" 00:00:01"));//取大于当天0点01分01秒入库的数据)
		if( policyDataDao.queryResp(params).size() > 0){
		String Premium =  policyDataDao.queryResp(params).get(0);
		double P = Double.valueOf(Premium) / 100;
		resp.setTotalPremium(String.valueOf(P));
			return resp;
		}else{
			return null;
		}
	}
}
