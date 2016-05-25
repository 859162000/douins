package com.douins.policy.service.iml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.common.util.CalculateUtils;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SystemConstant;
import com.douins.policy.dao.PolicyMasterDao;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponseVo;
import com.douins.policy.service.Policyservice;
import com.mango.core.common.util.DateUtils;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.page.Page;
@Service("policyservice")
public class PolicyServiceImpl extends Policyservice<PolicyMaster> {
	
	@Inject
	private PolicyMasterDao masterDao;
	
	/***
	 * DbOperateService interface
	 * 
	 */
	//TODO
	@Override
	public Page<PolicyMaster> getPage(PolicyMaster paramT, Page<PolicyMaster> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public PolicyMaster findByKey(String paramString) {
	    return masterDao.selectByPrimaryKey(paramString);
	}

	@Override
	public boolean save(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
	    SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
	    masterDao.insert(paramT);
	    return true;
	}

	@Transactional
	@Override
	public boolean update(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
	    masterDao.updateByPrimaryKey(paramT);
	    return true;
	}

	@Override
	public boolean delete(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
	    masterDao.softDeleteByPrimaryKey(paramT.getPolicyId());
	    return true;
	}
	
	@Override
	public List<PolicyResponseVo> findVoByCondition2(PolicyRequestVo paramT){
	    List<PolicyResponseVo> list = masterDao.getVoListByUid(paramT);
        calPolicyValues(list);
        return list;
	}
	
	/**
	 * 计算保单价值等属性
	 * @param list
	 */
	private void calPolicyValues(List<PolicyResponseVo> list){
	    if(list!=null&&list.size()>0){
            for(PolicyResponseVo policyResp:list){
                if (PolicyStatus.INSURESUCCESS.getValue().equals(policyResp.getStatus())
                        || PolicyStatus.LOANING.getValue().equals(policyResp.getStatus())
                        || PolicyStatus.INVALIDATE.getValue().equals(policyResp.getStatus())) {
                    Date endTime = DateUtils.today();
                    if (endTime.before(policyResp.getValidateDate())) {
                        policyResp.setNowRevenue(new BigDecimal("0"));
                    } else if (endTime.after(policyResp.getExpireDate())) {
                        policyResp.setNowRevenue(CalculateUtils.calculateTotalRevenueByDay(
                                        policyResp.getTotalPrem(),
                                        policyResp.getExpectRate(),
                                        policyResp.getValidateDate(),
                                        policyResp.getExpireDate()));
                    } else {
                        policyResp.setNowRevenue(CalculateUtils.calculateTotalRevenueByDay(
                                        policyResp.getTotalPrem(),
                                        policyResp.getExpectRate(),
                                        policyResp.getValidateDate(), endTime));
                    }

                    if (endTime.after(DateUtils.getCalcDay(policyResp.getValidateDate(), 1))
                            && endTime.before(DateUtils.getCalcDay(policyResp.getExpireDate(), 1))) {
                        policyResp.setYesDayEarn(CalculateUtils.calculateTotalRevenueByDay(
                                        policyResp.getTotalPrem(),
                                        policyResp.getExpectRate(), endTime,
                                        endTime));
                    } else {
                        policyResp.setYesDayEarn(new BigDecimal("0"));
                    }
                }
                // 可贷金额
                BigDecimal loanable = getLoanableAmount(policyResp.getTotalPrem(), policyResp.getRateLoanable());
                policyResp.setLoanableAmount(loanable);
                // modify by sty  APP 锁定期改成 1年 ，不需要转换成天
//                int days = calPeriodDays(policyResp.getValidateDate(), policyResp.getExpireDate());
//                policyResp.setPeriodDays(days);
            }
        }else{
            list=new ArrayList<PolicyResponseVo>();
        }
	}
	
	/**
	 * 计算可贷金额
	 * @param totalPrem        总保费
	 * @param rate                  可贷比例
	 * @return
	 */
	private BigDecimal getLoanableAmount(BigDecimal totalPrem, double rate){
	    BigDecimal val = totalPrem.multiply(new BigDecimal(rate));
	    int temp = 100;
	    val = val.divideToIntegralValue(new BigDecimal(temp));
	    val = val.multiply(new BigDecimal(temp));
	    
	    return val;
	}
	
	/**
	 * 计算时间差，天数
	 * @param start
	 * @param end
	 * @return
	 */
	private int calPeriodDays(Date start, Date end){
	    long gap = end.getTime() - start.getTime();
	    gap /= (24 * 60 * 60 * 1000);
	    
	    return (int)gap;
	}

	/***
	 * policyService abstract
	 * 
	 */
	
	@Override
	public List<PolicyMaster> findByCondition(PolicyMaster paramT) {
	    return masterDao.getList(paramT);
	}
	
	@Override
	public List<PolicyResponseVo> findVoByCondition(PolicyRequestVo paramT) {
	    List<PolicyResponseVo> list = masterDao.getVoList(paramT);
	    calPolicyValues(list);
		return list;
	}
	
    /* (non-Javadoc)
     * @see com.douins.policy.service.PolicyMasterService#findSuccessVoByCondition(com.douins.policy.domain.vo.PolicyRequestVo)
     */
    @Override
    public List<PolicyResponseVo> findSuccessVoByCondition(PolicyRequestVo policyVo) {
        List<PolicyResponseVo> list = masterDao.getSuccessPolicyVoList(policyVo);
        if(list == null){
            list = Collections.emptyList();
        }
        calPolicyValues(list);
        
        
        return list;
    }

    /**
     * 根据保单号查询保单。
     * 
     */
	@Override
	public PolicyMaster selectByPolicyId(String policyId) {
		 PolicyMaster policyMaster = masterDao.selectByPrimaryKey(policyId);
	    return policyMaster==null ? new PolicyMaster():policyMaster;
	}

	@Override
	public PolicyMaster findByChanlFlowNo(String chanlFlowNo) {
		PolicyMaster policymaster = masterDao.findByChanlFlowNo(chanlFlowNo);
		return policymaster ==null?new PolicyMaster():policymaster;
	}

	@Override
	public void updateStatus(String policyMasterId,String status) {
		masterDao.updateStatus(policyMasterId,status);
		
	}

	
	
	
  
	
}
