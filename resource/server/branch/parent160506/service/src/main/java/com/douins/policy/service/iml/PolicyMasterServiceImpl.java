package com.douins.policy.service.iml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.account.domain.enums.CertiType;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.vo.UserResponseVo;
import com.douins.account.service.UserAccountService;
import com.douins.account.service.UserService;
import com.douins.common.util.CalculateUtils;
import com.douins.common.util.DateExpectedUtils;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SeqNumUtils;
import com.douins.common.util.SystemConstant;
import com.douins.pay.service.PaymentApplyService;
import com.douins.policy.dao.PolicyMasterDao;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.model.PolicyData;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequest;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy.domain.vo.PolicyResponseVo;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.policy.service.PolicyMasterService;
import com.douins.policy.service.PolicyUwService;
import com.douins.product.domain.model.Product;
import com.douins.product.service.ProductService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.page.Page;

@Service
public class PolicyMasterServiceImpl implements PolicyMasterService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<PolicyMaster> baseDao;
//	@Autowired
//	private BaseDao<PolicyResponseVo> baseVoDao;
	@Autowired
	private PaymentApplyService paymentApplyService;
	@Autowired
	@Qualifier("policyUwServiceImpl")
	private PolicyUwService policyUwService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserAccountService userAccountService;

	@Inject
	private PolicyMasterDao masterDao;
	
//	String mapperName =PolicyMaster.class.getName();
//	String mapperVoName =PolicyRequestVo.class.getName();
	
	@Override
	public PolicyResponse add2Uw(PolicyRequest policyRequest){
		PolicyResponse response=new PolicyResponse();
		RequestTrans transVo=policyRequest.getRequestTrans();
		String transId= transVo.getTransId();
		ResponseTrans resTrans=null;
		try {
			boolean isUw=false;
			//更新客户信息
			this.updateUserInfo(policyRequest.getPolicyVo());
			//保存保单
			PolicyMaster policy=new PolicyMaster();
			resTrans= this.checkAndSavePolicy(policyRequest.getPolicyVo(),policy,transId);
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				isUw=true;	
			}
			//核保
			if(isUw){
				PolicyResult uwResult=policyUwService.doThirdUw(policy);
				if(uwResult.isSuccess()){
					policy.setUnderwritindTime(uwResult.getFinishTime());
					policy.setSendCode(uwResult.getSendCode());
					policy.setProposalNo(uwResult.getSendCode());
					this.updatePolicyStatus(policy, PolicyStatus.UWSUCCESS.getValue());
					resTrans=new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getName(),transId);
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.UWFAILED.getValue());
					resTrans=new ResponseTrans(uwResult.getResultCode(),uwResult.getMsg(),transId);
				}
			}
			logger.info("核保结果="+ resTrans.getResponseCode());
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				PolicyRequestVo policyVo=new PolicyRequestVo();
				policyVo.setPolicyId(policy.getPolicyId());
				policyVo.setUserId(policyRequest.getPolicyVo().getUserId());
				List<PolicyResponseVo> resList=this.findVoByCondition(policyVo);
               if(resList!=null&&resList.size()>0){
                   User sUser = userService.findByKey(policyVo.getUserId());
                   UserResponseVo tUser=new UserResponseVo();
                   BeanUtils.copyProperties(sUser, tUser);
                   resList.get(0).setUserInfo(tUser);
               }
               logger.info("记录数="+resList.size());
               response.setPolicyList(resList);
			}
		} catch (Exception e) {
			logger.error("policy pay error",e);
			resTrans=new ResponseTrans(ResponseCode.FAILED.getValue(),ResponseCode.FAILED.getName(),transId);
		}
		response.setResponseTrans(resTrans);
		return response;
	}
	
	/**
	 * 新版本 2015-12-17
	 * @param p
	 * @throws Exception
	 */
	private void updateUserInfo(PolicyRequestVo p) throws Exception{
	    if(StringUtils.isNotBlank(p.getUserId())){
	           User user=new User();
	           user.setUserId(p.getUserId());
	           user.setUserEmail(p.getUserEmail());
	           user.setProvince(p.getProvince());
	           user.setCity(p.getCity());
	           user.setDistrict(p.getDistrict());
	           user.setDetailedAdress(p.getDetailedAdress());
	           SaveEntityUtils.setUpdateForEntity(user, SystemConstant.OP_USER);
	           userService.update(SystemConstant.OP_USER, user);
	    }
	}

	/**
	 * 旧版本 2015-12-17
	 * @param p
	 * @throws Exception
	 */
	private void updateUserInfo2(PolicyRequestVo p) throws Exception {
       if(StringUtils.isNotBlank(p.getUserId())){
           User user=new User();
           user.setUserId(p.getUserId());
           user.setUserName(p.getUserName());
           user.setUserEmail(p.getUserEmail());
           user.setUserMobile(p.getUserMobile());
           user.setCertiCode(p.getCertiCode());
           if(StringUtils.isBlank(p.getCertiType())){
               user.setCertiType(CertiType.ID.getValue());
           }else{
               user.setCertiType(p.getCertiType());
           }
           // 自动解析生日和性别
           if(p.getUserBirthDay()==null){
               if((CertiType.ID.getValue()).equals(p.getCertiType())&&p.getCertiCode().length()==18){
                   String date=p.getCertiCode().substring(6, 14);
                   user.setUserBirthDay(DateUtils.parseDatetime(date, "yyyyMMdd"));
               }
           }else{
               user.setUserBirthDay(p.getUserBirthDay());
           }
           if(StringUtils.isBlank(p.getUserSex())){
               if((CertiType.ID.getValue()).equals(p.getCertiType())&&p.getCertiCode().length()==18){
                   int a=Integer.parseInt(p.getCertiCode().substring(16, 17));
                   if(a%2==0){//女
                       user.setUserSex("2");
                   }else{//男
                       user.setUserSex("1");
                   }
               }
           }else{
               user.setUserSex(p.getUserSex());
           }
           
           user.setCertiIsValid(p.getCertiIsValid());
           user.setCertiValidDate(p.getCertiValidDate());
           user.setProvince(p.getProvince());
           user.setCity(p.getCity());
           user.setDistrict(p.getDistrict());
           user.setDetailedAdress(p.getDetailedAdress());
           SaveEntityUtils.setUpdateForEntity(user, SystemConstant.OP_USER);
           userService.update(SystemConstant.OP_USER, user);
       }
   }
	
	@Override
	@Transactional
	public PolicyResponse insure2Pay(PolicyRequest policyRequest){
		PolicyResponse response=new PolicyResponse();
		RequestTrans transVo=policyRequest.getRequestTrans();
		String transId=transVo.getTransId();
		ResponseTrans resTrans=null;
		try {
			boolean isPay=false;
			boolean isInsure=false;
			//保存保单
			PolicyMaster policy=new PolicyMaster();
			resTrans= this.checkInsurePolicy(policyRequest.getPolicyVo(),policy,transId);
			Product product = productService.findByKey(policy.getProductId());
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				isPay=true;
			}
			//支付
			if(isPay){
				//ApplyResult result=paymentApplyService.payApply(policy, transVo.getTransChannel());
				if(true/*result.isSuccess()*/){
					isInsure=true;
					//policy.setPayTime(result.getFinishTime());
					policy.setPayTime(new Date());
					// 预期总收益
					policy.setTotalRevenue(CalculateUtils.calculateTotalRevenueByDay(policy.getTotalPrem(), 
							product.getExpectRate(), policy.getValidateDate(), policy.getExpireDate()));
					// 赎回金额
					policy.setRepayAmount(policy.getTotalPrem().add(policy.getTotalRevenue()));
					this.updatePolicyStatus(policy, PolicyStatus.PAYSUCCESS.getValue());
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.PAYFAILED.getValue());
					//resTrans=new ResponseTrans(result.getResultCode(),result.getMsg(),transId);
				}
			}
			//承保
			if(isInsure){
				//修改产品可售份额和累计销售份额
				int stock = product.getStock();
				int saleNum = product.getSaleNum();
				stock =  (int) (product.getStock() - policy.getApplyNum());
				saleNum = (int) (product.getSaleNum() + policy.getApplyNum());
				product.setStock(stock);
				product.setSaleNum(saleNum);
				SaveEntityUtils.setUpdateForEntity(product, "");
				productService.updateAfterPaySuccess("", product);
				
				//承保
				PolicyResult insureResult=policyUwService.doThirdInsure(policy);
				if(insureResult.isSuccess()){
					policy.setPolicyCode(insureResult.getPolicyCode());
					policy.setPolicyUrl(insureResult.getPolicyUrl());
					this.updatePolicyStatus(policy, PolicyStatus.INSURESUCCESS.getValue());
					resTrans=new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getName(),transId);
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.INSUREFAILED.getValue());
					resTrans=new ResponseTrans(insureResult.getResultCode(),insureResult.getMsg(),transId);
				}
			}
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				PolicyRequestVo policyVo=new PolicyRequestVo();
				policyVo.setPolicyId(policy.getPolicyId());
				policyVo.setUserId(policyRequest.getPolicyVo().getUserId());
//				response.setPolicyList(this.findVoByCondition(policyVo));
				List<PolicyResponseVo> resList=this.findVoByCondition(policyVo);
				    if(resList!=null&&resList.size()>0){
				    User sUser = userService.findByKey(policyVo.getUserId());
				    UserResponseVo tUser=new UserResponseVo();
				    BeanUtils.copyProperties(sUser, tUser);
				    resList.get(0).setUserInfo(tUser);
				    }
               response.setPolicyList(resList);
			}
		} catch (Exception e) {
			logger.error("policy pay",e);
			resTrans=new ResponseTrans(ResponseCode.FAILED.getValue(),ResponseCode.FAILED.getName(),transId);
		}
		response.setResponseTrans(resTrans);
		return response;
	}
	
	private ResponseTrans checkAndSavePolicy(PolicyRequestVo policyVo,PolicyMaster policy,String transId) throws Exception{
		if(StringUtils.isBlank(policyVo.getUserId())
				||StringUtils.isBlank(policyVo.getProductId())
				||StringUtils.isBlank(policyVo.getInsuredId())
				||StringUtils.isBlank(policyVo.getApplicantId())){
			 return new ResponseTrans(ResponseCode.DATAREAD_ERROR.getValue(),ResponseCode.DATAREAD_ERROR.getName(),transId);
		}
		
		Product product=productService.findByKey(policyVo.getProductId());
		// marked by G.F. 2015-11-02
//		if (!policyVo.getTotalPrem().equals(
//				product.getUnitPrem().multiply(
//						new BigDecimal(policyVo.getApplyNum())))) {
//			return new ResponseTrans(ResponseCode.POLICYTOTALPREM.getValue(),ResponseCode.POLICYTOTALPREM.getName(),transId);
//		}
		
		// Added by G.F. 2015-11-02
		BigDecimal totalPrem = product.getUnitPrem().multiply(new BigDecimal(policyVo.getApplyNum()));
		if(policyVo.getTotalPrem().compareTo(totalPrem) != 0){
		    return new ResponseTrans(ResponseCode.POLICYTOTALPREM.getValue(),ResponseCode.POLICYTOTALPREM.getName(),transId);
		}
		
		ResponseCode responseCode=ResponseCode.SUCCESS;
		if(StringUtils.isBlank(policyVo.getPolicyId())){
			//保存保单
			BeanUtils.copyProperties(policyVo, policy);
			policy.setInsuranceId(product.getInsuranceId());
			policy.setApplyTime(new Date());
			policy.setStatus(PolicyStatus.UWINIT.getValue());
			policy.setPrem(product.getUnitPrem());
			policy.setOrderNo(SeqNumUtils.geneTxSN(SeqNumUtils.ORDER_PREFIX));
			policy.setPayOrderNo(SeqNumUtils.geneTxSN(SeqNumUtils.PAY_PREFIX));
			policy.setValidateDate(DateExpectedUtils.validateDate(DateUtils.addDay(policy.getApplyTime(), 1)));
			policy.setExpireDate(DateExpectedUtils.expireDate(DateExpectedUtils
					.expectedDate(policy.getValidateDate(),product.getPeriodType(),
					new BigDecimal(product.getProductPeriod()))));
			if(StringUtils.isBlank(policy.getRelation())){
				policy.setRelation("1");//默认本人
			}
			
			if(StringUtils.isNotBlank(policyVo.getPolicyId())){
	              this.delete(SystemConstant.OP_USER, policyVo);
			}
			this.save(SystemConstant.OP_USER, policy);
		}
		return new ResponseTrans(responseCode.getValue(),responseCode.getName(),transId);
	}

	private ResponseTrans checkInsurePolicy(PolicyRequestVo policyVo,PolicyMaster policy, String transId) {
		if(StringUtils.isBlank(policyVo.getUserId())
				||StringUtils.isBlank(policyVo.getPolicyId())){
			 return new ResponseTrans(ResponseCode.DATAREAD_ERROR.getValue(),ResponseCode.DATAREAD_ERROR.getName(),transId);
		}
		
		User user=userService.findByKey(policyVo.getUserId());
		// marked by G.F. 不保存交易密码
//		if(!user.getPayPassword().equals(policyVo.getPayPassword())){
//			 return new ResponseTrans(ResponseCode.ORDERPAYPASSWORD.getValue(),ResponseCode.ORDERPAYPASSWORD.getName(),transId);
//		}
		
		 PolicyMaster policyMaster=this.findByKey(policyVo.getPolicyId());
		if(policyMaster==null||!policyMaster.getUserId().equals(policyVo.getUserId())){
			 return new ResponseTrans(ResponseCode.POLICYISNOTEXISTS.getValue(),ResponseCode.POLICYISNOTEXISTS.getName(),transId);
		}
		
		BeanUtils.copyProperties(policyMaster, policy);
		
		if(!PolicyStatus.UWSUCCESS.getValue().equals(policy.getStatus())&&
				!PolicyStatus.PAYFAILED.getValue().equals(policy.getStatus())){
			return new ResponseTrans(ResponseCode.POLICYNOTPAY.getValue(),ResponseCode.POLICYNOTPAY.getName(),transId);
		}
		
		UserAccount userAccount = userAccountService.findOneByUserId(policyVo.getUserId());
		if(userAccount.getAccountBalance().compareTo(policy.getTotalPrem())<0){
			return new ResponseTrans(ResponseCode.USERBALANCEERROR.getValue(),ResponseCode.USERBALANCEERROR.getName(),transId);
		}
		
		return new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getName(),transId);
	}

	private void updatePolicyStatus(PolicyMaster policy, String status) throws DataBaseAccessException {
		policy.setStatus(status);
		SaveEntityUtils.setUpdateForEntity(policy,SystemConstant.OP_USER);
		this.update(SystemConstant.OP_USER, policy);
	}
	

	@Override
	public List<PolicyMaster> findByCondition(PolicyMaster paramT) {
		//return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	    return masterDao.getList(paramT);
	}
	
	@Override
	public List<PolicyResponseVo> findVoByCondition(PolicyRequestVo paramT) {
		//List<PolicyResponseVo> list= this.baseVoDao.getList(mapperName + "Mapper.getVoList", paramT);
	    List<PolicyResponseVo> list = masterDao.getVoList(paramT);
	    calPolicyValues(list);
		return list;
	}
	
	@Override
	public Page<PolicyMaster> getPage(PolicyMaster paramT, Page<PolicyMaster> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public PolicyMaster findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return masterDao.selectByPrimaryKey(paramString);
	}

	@Override
	public boolean save(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
//		try {
//			SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    
	    SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
//	    logger.info("保单号码：" + paramT.getPolicyId());
	    masterDao.insert(paramT);
	    return true;
	}

	@Transactional
	@Override
	public boolean update(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    masterDao.updateByPrimaryKey(paramT);
	    return true;
	}

	@Override
	public boolean delete(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getPolicyId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
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
            	//modify by 2016-04-12 修改累计收益、昨天收益直接取数据库的值，不需要重新计算
            	/*
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
                */
            	//modify by 2016-04-12 修改累计收益、昨天收益直接取数据库的值，不需要重新计算
            	
            	policyResp.setNowRevenue(policyResp.getAccuIncome());
            	policyResp.setYesDayEarn(policyResp.getYesDayEarn());
                // 可贷金额
                BigDecimal loanable = getLoanableAmount(policyResp.getTotalPrem(), policyResp.getRateLoanable());
                policyResp.setLoanableAmount(loanable);
                //modify by sty 2016-04-12 锁定期修改为1 年，按天的代码逻辑不用了
                //int days = calPeriodDays(policyResp.getValidateDate(), policyResp.getExpireDate());
               // policyResp.setPeriodDays(days);
                
                policyResp.setPeriodDays(policyResp.getProductPeriod());
          
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
	
	 /**
     * 根据agency服务器传来的数据更新保险收益
     * 
     */
	@Override
	@Transactional
	public int updatePolicyWithAgency(List<PolicyData> policyDatas) {
		int result = 0;
		for (PolicyData policyData : policyDatas) {
			result+=masterDao.updateByAgencyData(policyData);
		}
		return result;
	}
   
}