/**
 * 
 */
package com.douins.agency.service.douins.dao;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureConfirmHeader;

/** 
* @ClassName: InsureConformHeaderDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月30日 上午9:59:58 
*  
*/
@Repository
public class InsureConfirmHeaderDao extends AbstractDao {
	
	public void insert(InsureConfirmHeader insertConfirmHeader){
		writeSqlSession.insert(sql(), insertConfirmHeader);
	}
	
	public void updateByOrder(InsureConfirmHeader insertConfirmHeader){
		writeSqlSession.update(sql(),insertConfirmHeader);
	}

}
