package com.douins.trans.service.iml;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.trans.dao.BusinessTransDao;
import com.douins.trans.domain.enums.TransType;
import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.service.TrasBusinessService;
import com.google.common.collect.Lists;
@Service
@Transactional
public class TrasBusinessServiceImpl implements TrasBusinessService {
	@Inject
	private BusinessTransDao transDao;
	@Override
	public void saveTrasBusiness(BusinessTrans businessTrans) {
		transDao.insert(businessTrans);
	}
	@Override
	public void updateTrasBusiness(BusinessTrans businessTrans) {
		transDao.updateByPrimaryKey(businessTrans);
	}
	@Override
	public BusinessTrans findTrasBusiness(String transNo) {
		return transDao.findByTransNo(transNo);
	}
	@Override
	public BusinessTrans findTrasBusinessByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BusinessTrans> getList(BusinessTrans businessTrans) {
		List<BusinessTrans> list = transDao.getList(businessTrans);
		List<BusinessTrans> businessTransList = Lists.newArrayList();
		if(businessTransList!=null){
			for (BusinessTrans businessTrans2 : list) {
				String transType = businessTrans2.getTransType();
				if((TransType.ADDMONEY.getValue().equals(transType)
						||TransType.DELETEMONEY.getValue().equals(transType)
						||TransType.BUY.getValue().equals(transType)
						||TransType.CHEBAO.getValue().equals(transType))){
					businessTransList.add(businessTrans2);
				}
			}
		}
		return businessTransList;
	}

}
