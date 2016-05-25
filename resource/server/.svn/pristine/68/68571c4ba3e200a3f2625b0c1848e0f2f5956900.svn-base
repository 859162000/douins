package com.mango.fortune.policy.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.api.policyAPI.vo.PolicyRequest;
import com.mango.api.policyAPI.vo.PolicyRequestVo;
import com.mango.api.policyAPI.vo.PolicyResponse;
import com.mango.api.policyAPI.vo.PolicyResponseVo;
import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.pay.service.PaymentApplyService;
import com.mango.fortune.policy.enums.PolicyStatus;
import com.mango.fortune.policy.model.PolicyMaster;
import com.mango.fortune.policy.service.PolicyMasterService;
import com.mango.fortune.policy.service.PolicyUwService;
import com.mango.fortune.policy.vo.PolicyResult;
import com.mango.fortune.product.model.Product;
import com.mango.fortune.product.service.ProductService;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.trans.model.RequestTrans;
import com.mango.fortune.trans.model.ResponseTrans;
import com.mango.fortune.user.model.User;
import com.mango.fortune.user.service.UserService;
import com.mango.fortune.util.CalculateUtils;
import com.mango.fortune.util.DateExpectedUtils;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SeqNumUtils;
import com.mango.fortune.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class PolicyMasterServiceImpl implements PolicyMasterService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private BaseDao<PolicyMaster> baseDao;
	@Autowired
	private BaseDao<PolicyResponseVo> baseVoDao;
	@Autowired
	private PaymentApplyService paymentApplyService;
	@Autowired
	private PolicyUwService policyUwService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserAccountService userAccountService;

	String mapperName =PolicyMaster.class.getName();
	String mapperVoName =PolicyRequestVo.class.getName();
	
	@Override
	public PolicyResponse add2Uw(PolicyRequest policyRequest){
		PolicyResponse response=new PolicyResponse();
		RequestTrans transVo=policyRequest.getRequestTrans();
		String transId=transVo.getTransId();
		ResponseTrans resTrans=null;
		try {
			boolean isUw=false;
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
					this.updatePolicyStatus(policy, PolicyStatus.UWSUCCESS.getValue());
					resTrans=new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getName(),transId);
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.UWFAILED.getValue());
					resTrans=new ResponseTrans(uwResult.getResultCode(),uwResult.getMsg(),transId);
				}
			}
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				PolicyRequestVo policyVo=new PolicyRequestVo();
				policyVo.setPolicyId(policy.getPolicyId());
				policyVo.setUserId(policyRequest.getPolicyVo().getUserId());
				response.setPolicyList(this.findVoByCondition(policyVo));
			}
		} catch (Exception e) {
			logger.error("policy pay",e);
			resTrans=new ResponseTrans(ResponseCode.FAILED.getValue(),ResponseCode.FAILED.getName(),transId);
		}
		response.setResponseTrans(resTrans);
		return response;
	}

	
	@Override
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
				ApplyResult result=paymentApplyService.payApply(policy, transVo.getTransChannel());
				if(result.isSuccess()){
					isInsure=true;
					policy.setPayTime(result.getFinishTime());
//					policy.setValidateDate(DateExpectedUtils.validateDate(DateUtils.addDay(policy.getPayTime(), 1)));
//					policy.setExpireDate(DateExpectedUtils.expireDate(DateExpectedUtils
//							.expectedDate(policy.getValidateDate(),product.getPeriodType(),
//							new BigDecimal(product.getProductPeriod()))));
					policy.setTotalRevenue(CalculateUtils.calculateTotalRevenueByDay(policy.getTotalPrem(), 
							product.getExpectRate(), policy.getValidateDate(), policy.getExpireDate()));
					policy.setRepayAmount(policy.getTotalPrem().add(policy.getTotalRevenue()));
					this.updatePolicyStatus(policy, PolicyStatus.PAYSUCCESS.getValue());
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.PAYFAILED.getValue());
					resTrans=new ResponseTrans(result.getResultCode(),result.getMsg(),transId);
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
				response.setPolicyList(this.findVoByCondition(policyVo));
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
		if (!policyVo.getTotalPrem().equals(
				product.getUnitPrem().multiply(
						new BigDecimal(policyVo.getApplyNum())))) {
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
		if(!user.getPayPassword().equals(policyVo.getPayPassword())){
			 return new ResponseTrans(ResponseCode.ORDERPAYPASSWORD.getValue(),ResponseCode.ORDERPAYPASSWORD.getName(),transId);
		}
		
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
		return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	}
	
	@Override
	public List<PolicyResponseVo> findVoByCondition(PolicyRequestVo paramT) {
		List<PolicyResponseVo> list= this.baseVoDao.getList(mapperName + "Mapper.getVoList", paramT);
		if(list!=null&&list.size()>0){
			for(PolicyResponseVo policyResp:list){
				if (PolicyStatus.INSURESUCCESS.getValue().equals(policyResp.getStatus())) {
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
			}
		}else{
			list=new ArrayList<PolicyResponseVo>();
		}
		return list;
	}
	
	@Override
	public Page<PolicyMaster> getPage(PolicyMaster paramT, Page<PolicyMaster> paramPage) {
		if (paramPage != null) {
			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
		}
		return null;
	}

	@Override
	public PolicyMaster findByKey(String paramString) {
		return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	}

	@Override
	public boolean save(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
		try {
			SaveEntityUtils.initEntityBeforeInsert(paramT, SystemConstant.OP_USER);
			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
		} catch (DataBaseAccessException e) {
			logger.error("create DataBaseAccessException", e);
			throw e;
		}
	}

	@Override
	public boolean update(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
		try {
			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("update DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean delete(String paramString, PolicyMaster paramT) throws DataBaseAccessException {
		try {
			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getPolicyId()) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("delete DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}
}