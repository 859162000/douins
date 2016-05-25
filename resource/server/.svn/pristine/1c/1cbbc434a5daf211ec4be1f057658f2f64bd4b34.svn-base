/**
 * 
 */
package com.douins.account.service.iml;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.douins.AbstractTest;
import com.douins.account.domain.model.ValidateCode;
import com.douins.policy.dao.PolicyMasterDao;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequestVo;

/** 
* @ClassName: ValidateCodeServiceImplTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月1日 下午12:39:08 
*  
*/
public class ValidateCodeServiceImplTest extends AbstractTest {

    @Inject
    private ValidateCodeServiceImpl service;
    
    @Inject
    private PolicyMasterDao policyDao;
    
    @Test
    public void test() {
        ValidateCode code = service.findByKey("63a12a946a7b41378c7538a99201cb15");
        try {
            //service.sendValidateCode(code);
            //service.sendMessage("101", "15901967109", 6);
            PolicyRequestVo vo = new PolicyRequestVo();
            vo.setPolicyId("39d7373d867e46c7ba3f31acaed04ced");
            policyDao.getVoList(vo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }

}
