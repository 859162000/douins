/**
 * 
 */
package com.douins.idea.service.iml;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douins.idea.dao.IdeaFeedBackDao;
import com.douins.idea.domain.model.IdeaFeedBack;
import com.douins.idea.service.IdeaFeedBackService;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.page.Page;

/** 
* @ClassName: IdeaFeedBackServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月4日 下午1:05:24 
*  
*/
@Service("ideaFeedBackService")
public class IdeaFeedBackServiceImpl implements IdeaFeedBackService {
   private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());

//   @Autowired
//   private BaseDao<IdeaFeedBack> baseDao;
//
//   private String mapper = IdeaFeedBack.class.getName();

   @Inject
   private IdeaFeedBackDao ideaDao;
   
   @Override
   public boolean delete(String arg0, IdeaFeedBack arg1)
           throws DataBaseAccessException {
       // TODO Auto-generated method stub
       ideaDao.softDeleteByPrimaryKey(arg1.getIdeaFeedbackId());
       return true;
   }

   @Override
   public IdeaFeedBack findByKey(String s) {
       // TODO Auto-generated method stub
       return ideaDao.selectByPrimaryKey(s);
   }

   @Override
   public Page<IdeaFeedBack> getPage(IdeaFeedBack arg0, Page<IdeaFeedBack> arg1) {
       // TODO Auto-generated method stub
       return null;
   }

   @Override
   public boolean save(String userName, IdeaFeedBack ideaFeedBack)
           throws DataBaseAccessException {
//       return baseDao.save(mapper  "Mapper.insert", ideaFeedBack) > 0;
       ideaDao.insert(ideaFeedBack);
       return true;
   }

   @Override
   public boolean update(String arg0, IdeaFeedBack arg1)
           throws DataBaseAccessException {
       // TODO Auto-generated method stub
       ideaDao.updateByPrimaryKey(arg1);
       return true;
   }
}
