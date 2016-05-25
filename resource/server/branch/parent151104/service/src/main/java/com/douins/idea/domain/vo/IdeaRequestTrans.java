/**
 * 
 */
package com.douins.idea.domain.vo;

import com.douins.trans.domain.vo.RequestTransVo;

/** 
* @ClassName: IdeaRequestTrans 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月4日 上午11:44:37 
*  
*/
public class IdeaRequestTrans extends RequestTransVo {


   /**
    * 
    */
   private static final long serialVersionUID = -522837387290732050L;
   
   private IdeaRequestVo ideaVo;
   
   public IdeaRequestVo getIdeaVo() {
       return ideaVo;
   }
   public void setIdeaVo(IdeaRequestVo ideaVo) {
       this.ideaVo = ideaVo;
   }
   
   
   
}
