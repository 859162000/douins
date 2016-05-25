/**
 * 
 */
package com.douins.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import com.douins.account.domain.model.ValidateCode;
import com.douins.common.dao.AbstractDao;

/** 
* @ClassName: ValidateCodeDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:14:08 
*  
*/
@Repository
public class ValidateCodeDao extends AbstractDao {

    public void add(ValidateCode code){
        writeSqlSession.insert(sql(), code);
    }
    
    public ValidateCode findByCodeId(String validateCodeId){
        return writeSqlSession.selectOne(sql(), validateCodeId);
    }
    
    public void delete(String validateCodeId){
        writeSqlSession.update(sql(), validateCodeId);
    }
    
    public void update(ValidateCode code){
        writeSqlSession.update(sql(), code);
    }
    
    public ValidateCode findLatestValidateCode(ValidateCode code){
        return writeSqlSession.selectOne(sql(), code);
    }
}
