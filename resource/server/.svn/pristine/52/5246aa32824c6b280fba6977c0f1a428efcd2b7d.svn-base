/**
 * 
 */
package com.douins.bank.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.douins.bank.domain.model.BankResponseModel;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: BankResponeDao 
* @Description: 与银行交易相关的记录 dao
* @author G. F. 
* @date 2015年12月9日 下午9:50:05 
*  
*/
@Transactional
@Repository
public class BankResponeDao extends AbstractDao {
    
    public void add(BankResponseModel model){
        writeSqlSession.insert(sql(), model);
    }
    
    public void update(BankResponseModel model){
        writeSqlSession.update(sql(), model);
    }
    
	public int insertNYCallbackInfo(CallBackRequest callbackInfo) {
		
		return writeSqlSession.insert(sql(), callbackInfo);
	}

	public CallBackRequest findPayInfo(String chanlFlowNo) {
		return  writeSqlSession.selectOne(sql(),chanlFlowNo);
	}
}
