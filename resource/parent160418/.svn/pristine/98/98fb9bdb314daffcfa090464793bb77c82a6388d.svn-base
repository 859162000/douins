/**
 * 
 */
package com.douins.trans.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.trans.domain.model.RequestTrans;

/** 
* @ClassName: RequestTransDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午4:04:55 
*  
*/
@MyBatisMapper
public interface RequestTransDao{

    public RequestTrans selectByPrimaryKey(@Param("requestTransId")String requestTransId);
    
    public void insert(RequestTrans trans);
    
    public void updateResponseInfo(RequestTrans trans);
}
