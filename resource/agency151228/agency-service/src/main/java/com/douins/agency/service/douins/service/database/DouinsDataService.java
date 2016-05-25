/**
 * 
 */
package com.douins.agency.service.douins.service.database;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.douins.agency.service.douins.dao.PolicyDataDao;
import com.douins.agency.service.douins.dao.PolicyFactDao;
import com.douins.agency.service.douins.domain.model.PolicyData;
import com.douins.agency.service.douins.domain.model.PolicyFact;
import com.douins.agency.service.douins.domain.vo.QunarFNQueryWDVo;

/** 
* @ClassName: DouinsDataService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月10日 下午1:13:24 
*  
*/
@Service
public class DouinsDataService {

    @Resource
    private PolicyFactDao policyFactDao;
    
    /**
     * 保存保单信息，批量处理
     * @param policys
     */
    public void savePolicyFact(List<PolicyFact> policys){
        policyFactDao.insert(policys);
    }
    
    public List<PolicyFact> findPolicyByPolicyNo(String channelNo, String insureNo, List<String> policyNos){
        return policyFactDao.findPolicysByPolicyNo(channelNo, insureNo, policyNos);
    }
    
    /**
     * 更新保单状态
     * @param policys
     */
    public void updatePolicyFact(List<PolicyFact> policys, String status){
        if(policys != null && policys.size() > 0)
            policyFactDao.updateStatus(policys, status);
    }
    
    /**
     * 更新指定保单的状态
     * @param policys
     */
    public void updatePolicyFactByPolicyNo(List<String> policys, String status){
        if(policys != null && policys.size() > 0)
            policyFactDao.updateStatus2(policys, status);
    }
    
    /**
     * 查看保单退保金额和手续费
     * @param policyno
     */
//    public  getWDInfoByPolicyNo(String policyNo){
//    		PolicyDataDao policyData = new PolicyDataDao();
//    		return policyData.queryWDInfo(policyNo);
//    }
}
