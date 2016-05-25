/**
 * 
 */
package com.douins.apply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.apply.domain.model.ApplyInfo;
import com.douins.common.persistence.annotation.MyBatisMapper;


/** 
* @ClassName: ApplyInfoDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月19日 下午3:38:13 
*  
*/
@MyBatisMapper
public interface ApplyInfoDao{

    /**
     * 添加一条记录
     * @param info
     */
	public void add(ApplyInfo info);
	
	/**
	 * 根据 ID 查找记录
	 * @param id
	 * @return
	 */
	public ApplyInfo find(@Param("id")long id);
	
	/**
	 * 根据申请号查询记录
	 * @param infoId
	 * @return
	 */
	public ApplyInfo findByInfoId(@Param("applyInfoId")String applyInfoId);
	
	/**
	 * 删除一条数据（逻辑删除）
	 * @param applyInfoId
	 */
	public void delete(@Param("applyInfoId")String applyInfoId);
	
	/**
	 * 更新记录
	 * @param info
	 */
	public void update(ApplyInfo info);
	
	/**
	 * 
	 * @param info
	 */
	public void updateAfter(ApplyInfo info);
	
	/**
	 * 查询所有满足条件的记录
	 * @param info
	 * @return
	 */
	public List<ApplyInfo> getList(ApplyInfo info);
	
	/**
	 * 查询满足条件的记录总数
	 * @param info
	 * @return
	 */
	public int getList_Count(ApplyInfo info);
}
