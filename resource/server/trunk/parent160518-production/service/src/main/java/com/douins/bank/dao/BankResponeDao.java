/**
 * 
 */
package com.douins.bank.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.bank.domain.model.BankResponseModel;
import com.douins.bank.domain.model.nybc.BankDict;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: BankResponeDao 
* @Description: 与银行交易相关的记录 dao
* @author G. F. 
* @date 2015年12月9日 下午9:50:05 
*  
*/
@MyBatisMapper
public interface BankResponeDao{
    
    public void add(BankResponseModel model);
    
    public void update(BankResponseModel model);
    
	public int insertNYCallbackInfo(CallBackRequest callbackInfo);

	public CallBackRequest findPayInfo(@Param("chanlFlowNo")String chanlFlowNo);
	
	public BankDict getBankDict(BankDict bankDict);

	public List<String> getBankName(@Param("frontCardNumber")String payAcct); 
	
	public String  getBankNameByFrontNumber(@Param("frontCardNumber")String frontCardNumber);
	
	
}
