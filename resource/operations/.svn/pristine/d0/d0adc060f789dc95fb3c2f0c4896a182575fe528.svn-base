package com.douins.bank.service.iml;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douins.bank.domain.enums.BankType;
import com.douins.bank.domain.vo.BankTypeRequest;
import com.douins.bank.domain.vo.BankTypeResponseVo;
import com.douins.bank.service.BankTypeService;


@Service
public class bankTypeServiceImpl implements BankTypeService {

	@Override
	public List<BankTypeResponseVo> getBankAPI(BankTypeRequest bankTypeRequest)
			throws Exception {
		List<BankTypeResponseVo> bankList=BankType.getBankTypeList();
		return bankList;
	}
}
