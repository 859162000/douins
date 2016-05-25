/**
 * 
 */
package com.douins.partner.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.partner.domain.model.Bank;

/** 
* @ClassName: BankDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:26:15 
*  
*/
@Repository
public class BankDao extends AbstractDao {

    public List<Bank> getList(Bank bank){
        return list(writeSqlSession, sql(), bank);
    }
    
    public int getList_Count(Bank bank){
        return size(writeSqlSession, sql(), bank);
    }
    
    public void add(Bank bank){
        writeSqlSession.insert(sql(), bank);
    }
    
    public void delete(String bankId){
        writeSqlSession.update(sql(), bankId);
    }
    
    public void update(Bank bank){
        writeSqlSession.update(sql(), bank);
    }
    
    public Bank findByBankId(String bankId){
        return writeSqlSession.selectOne(sql(), bankId);
    }
}
