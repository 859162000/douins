/**
 * 
 */
package com.douins.idea.dao;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.idea.domain.model.IdeaFeedBack;

/** 
* @ClassName: IdeaFeedBackDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月4日 上午11:50:17 
*  
*/
@Repository
public class IdeaFeedBackDao extends AbstractDao {
    
    /**
     * 插入一条数据
     * @param idea
     */
    public void insert(IdeaFeedBack idea){
        writeSqlSession.insert(sql(), idea);
    }
    
    /**
     * 更新记录
     * @param idea
     */
    public void updateByPrimaryKey(IdeaFeedBack idea){
        writeSqlSession.update(sql(), idea);
    }
    
     /**
      * 查询记录
      * @param ideaFeedbackId
      * @return
      */
    public IdeaFeedBack selectByPrimaryKey(String ideaFeedbackId){
        return writeSqlSession.selectOne(sql(), ideaFeedbackId);
    }
    
    /**
     * 软删除
     * @param ideaFeedbackId
     */
    public void softDeleteByPrimaryKey(String ideaFeedbackId){
        writeSqlSession.update(sql(), ideaFeedbackId);
    }
}
