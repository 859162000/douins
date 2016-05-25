package com.douins.agency.service.douins.service;

import com.douins.agency.service.douins.domain.model.PolicyReservationRequest;
import com.douins.agency.service.douins.domain.model.PolicyReservationResponse;

public interface LianConvertServiceIFC {
	
	public  <T>PolicyReservationRequest convertPolicyReservationRequest(T data,String code);
	
	public <T>PolicyReservationResponse convertPolicyReservationResponse(T data,String code);
	
	public void PolicyJob();
	
	public void ValidationJob();
}
