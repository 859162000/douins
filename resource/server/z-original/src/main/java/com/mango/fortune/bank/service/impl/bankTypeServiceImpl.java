package com.mango.fortune.bank.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mango.api.bankTypeAPI.vo.BankTypeRequest;
import com.mango.api.bankTypeAPI.vo.BankTypeResponseVo;
import com.mango.fortune.bank.enums.BankType;
import com.mango.fortune.bank.service.BankTypeService;

@Service
public class bankTypeServiceImpl implements BankTypeService {

	@Override
	public List<BankTypeResponseVo> getBankAPI(BankTypeRequest bankTypeRequest)
			throws Exception {
		List<BankTypeResponseVo> bankList=BankType.getBankTypeList();
		return bankList;
	}
}
