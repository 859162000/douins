/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.douins.apply.domain.model.ApplyInfo;
import com.douins.common.dao.AbstractDao;
import com.google.common.collect.Maps;


/** 
* @ClassName: ApplyInfoDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月19日 下午3:38:13 
*  
*/
@Repository
public class ApplyInfoDao extends AbstractDao{

    /**
     * 添加一条记录
     * @param info
     */
	public void add(ApplyInfo info){
		writeSqlSession.insert(sql(), info);
	}
	
	/**
	 * 根据 ID 查找记录
	 * @param id
	 * @return
	 */
	public ApplyInfo find(long id){
	   Map<String, Long> params = Maps.newHashMap();
	   params.put("id", id);
	   ApplyInfo info = writeSqlSession.selectOne(sql(), params);
	   return info;
	}
	
	/**
	 * 根据申请号查询记录
	 * @param infoId
	 * @return
	 */
	public ApplyInfo findByInfoId(String applyInfoId){
	    return writeSqlSession.selectOne(sql(), applyInfoId);
	}
	
	/**
	 * 删除一条数据（逻辑删除）
	 * @param applyInfoId
	 */
	public void delete(String applyInfoId){
	    writeSqlSession.update(sql(), applyInfoId);
	}
	
	/**
	 * 更新记录
	 * @param info
	 */
	public void update(ApplyInfo info){
	    writeSqlSession.update(sql(), info);
	}
	
	/**
	 * 
	 * @param info
	 */
	public void updateAfter(ApplyInfo info){
	    writeSqlSession.update(sql(), info);
	}
	
	/**
	 * 查询所有满足条件的记录
	 * @param info
	 * @return
	 */
	public List<ApplyInfo> getList(ApplyInfo info){
	    return list(writeSqlSession, sql(), info);
	}
	
	/**
	 * 查询满足条件的记录总数
	 * @param info
	 * @return
	 */
	public int getList_Count(ApplyInfo info){
	    return writeSqlSession.selectOne(sql(), info);
	}
}
