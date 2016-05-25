/**
 * 
 */
package com.douins.partner.dao;

import java.util.List;

import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.partner.domain.model.Insurance;

/** 
* @ClassName: InsuranceDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 上午11:34:20 
*  
*/
@Repository
public class InsuranceDao extends AbstractDao {

    public List<Insurance> getList(Insurance insurance){
        return list(writeSqlSession, sql(), insurance);
    }
    
    public int getList_Count(Insurance insurance){
        return size(writeSqlSession, sql(), insurance);
    }
    
    public void delete(String insuranceId){
        writeSqlSession.update(sql(), insuranceId);
    }
    
    public void insert(Insurance insurance){
        writeSqlSession.insert(sql(), insurance);
    }
    
    public void updateByPrimaryKey(Insurance insurance){
        writeSqlSession.update(sql(), insurance);
    }
    
    public Insurance selectByPrimaryKey(String insuranceId){
        return writeSqlSession.selectOne(sql(), insuranceId);
    }
}
