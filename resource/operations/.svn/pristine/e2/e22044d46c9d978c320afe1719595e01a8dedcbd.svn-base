/**
 * 
 */
package com.douins.agency.service.douins.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.douins.agency.service.AbstractTest;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.douins.dao.PolicyFactDao;
import com.douins.agency.service.douins.domain.model.PolicyFact;
import com.google.common.collect.Lists;

/** 
* @ClassName: InsureServiceIFCTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月30日 上午9:19:34 
*  
*/
public class InsureServiceIFCTest extends AbstractTest{

    @Resource(name = Constants.CCIC_SERVICE)
    private InsureServiceIFC ccicService;
    @Resource
    private PolicyFactDao policyDao;
    
    @Test
    public void test() {
//        ccicService.doUW(null);
        List<String> policyNos = Lists.newArrayList();
        policyNos.add("PEID201611010005000011");
        policyNos.add("PEID201611010005000012");
        List< PolicyFact> policys = policyDao.findPolicysByPolicyNo("01", "01", policyNos);
        System.out.println(policys.size());
    }

}
