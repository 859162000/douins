package com.douins.bank.service;

import java.util.List;

import com.douins.bank.domain.vo.BankTypeResponseVo;
import com.douins.bank.domain.vo.BankTypeRequest;


public interface BankTypeService{
	public List<BankTypeResponseVo> getBankAPI(BankTypeRequest bankTypeRequest) throws Exception;

}