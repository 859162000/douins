
package com.douins.idea.domain.model;

import java.util.Date;
import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

/** 
* @ClassName: IdeaFeedBack 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月4日 上午11:37:23 
*  
*/
public class IdeaFeedBack extends AbstractModel {

       private static final long serialVersionUID = 3949665806993747074L;
    
       private Long id;
       
       private String ideaFeedbackId;
    
       private String userId;
    
       private String contact;
    
       private Date feedbackTime;
    
       private String idea;
    
       private String isvalid;
    
       public String getIdeaFeedbackId() {
           return ideaFeedbackId;
       }
    
       public void setIdeaFeedbackId(String ideaFeedbackId) {
           this.ideaFeedbackId = ideaFeedbackId;
       }
    
    
       public String getUserId() {
           return userId;
       }
    
       public void setUserId(String userId) {
           this.userId = userId;
       }
    
       public String getContact() {
           return contact;
       }
    
       public void setContact(String contact) {
           this.contact = contact;
       }
    
       public Date getFeedbackTime() {
           return feedbackTime;
       }
    
       public void setFeedbackTime(Date feedbackTime) {
           this.feedbackTime = feedbackTime;
       }
    
       public String getIdea() {
           return idea;
       }
    
       public void setIdea(String idea) {
           this.idea = idea;
       }
    
       public String getIsvalid() {
           return isvalid;
       }
    
       public void setIsvalid(String isvalid) {
           this.isvalid = isvalid;
       }
    
       @Override
       public void initPrimaryKey() {
           this.setIdeaFeedbackId(KeyGenerator.randomSeqNum());
       }
    
       public Long getId() {
           return id;
       }
    
       public void setId(Long id) {
           this.id = id;
       }
}
