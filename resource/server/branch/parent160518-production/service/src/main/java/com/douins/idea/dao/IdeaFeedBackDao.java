/**
 * 
 */
package com.douins.idea.dao;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.idea.domain.model.IdeaFeedBack;

/** 
* @ClassName: IdeaFeedBackDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月4日 上午11:50:17 
*  
*/
@MyBatisMapper
public interface IdeaFeedBackDao{
    
    /**
     * 插入一条数据
     * @param idea
     */
    public void insert(IdeaFeedBack idea);
    
    /**
     * 更新记录
     * @param idea
     */
    public void updateByPrimaryKey(IdeaFeedBack idea);
    
     /**
      * 查询记录
      * @param ideaFeedbackId
      * @return
      */
    public IdeaFeedBack selectByPrimaryKey(@Param("ideaFeedbackId")String ideaFeedbackId);
    
    /**
     * 软删除
     * @param ideaFeedbackId
     */
    public void softDeleteByPrimaryKey(@Param("ideaFeedbackId")String ideaFeedbackId);
}
