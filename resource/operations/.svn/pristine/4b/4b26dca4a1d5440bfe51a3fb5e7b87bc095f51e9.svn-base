package com.douins.agency.service.douins.service.database;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	   	params.put("createTime",DateTimeUtils.formateDateTimeByHHmmss(" 02:00:00"));
		return policyDataDao.queryPolicys(params);
	}
	
}
