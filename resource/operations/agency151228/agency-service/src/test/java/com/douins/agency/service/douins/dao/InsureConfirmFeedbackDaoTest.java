/**
 * 
 */
package com.douins.agency.service.douins.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.douins.agency.service.AbstractTest;
import com.douins.agency.service.douins.domain.model.InsureConfirmFeedback;
import com.google.common.collect.Lists;

/** 
* @ClassName: InsureConfirmFeedbackDaoTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月14日 上午10:58:32 
*  
*/
public class InsureConfirmFeedbackDaoTest extends AbstractTest{
    @Resource
    private InsureConfirmFeedbackDao fbDao;
    
    @Test
    public void test() {
        List<InsureConfirmFeedback> feedbacks = Lists.newArrayList();
        feedbacks.add(new InsureConfirmFeedback());
        feedbacks.add(new InsureConfirmFeedback());
        fbDao.insert(feedbacks);
    }

}
