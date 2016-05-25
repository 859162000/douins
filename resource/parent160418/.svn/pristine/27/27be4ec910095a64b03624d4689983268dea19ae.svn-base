/**
 * 
 */
package com.douins.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import com.douins.account.domain.model.ValidateCode;
import com.douins.common.dao.AbstractDao;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: ValidateCodeDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:14:08 
*  
*/
@MyBatisMapper
public interface ValidateCodeDao{

    public void add(ValidateCode code);
    
    public ValidateCode findByCodeId(@Param("validateCodeId") String validateCodeId);
    
    public void delete(@Param("validateCodeId")String validateCodeId);
    
    public void update(ValidateCode code);
    
    public ValidateCode findLatestValidateCode(ValidateCode code);
}
