package com.douins.trans.service;

import java.util.List;

import com.douins.trans.domain.model.BusinessTrans;

public interface TrasBusinessService {
	
	void saveTrasBusiness(BusinessTrans businessTrans);
	void updateTrasBusiness(BusinessTrans businessTrans);
	BusinessTrans findTrasBusiness(String transId);
	BusinessTrans findTrasBusinessByOrderNumber(String orderNumber);
	List<BusinessTrans> getList( BusinessTrans businessTrans);

}
