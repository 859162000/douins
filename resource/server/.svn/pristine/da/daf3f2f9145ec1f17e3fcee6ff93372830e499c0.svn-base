package com.douins.policy.service.iml;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAuthority;
import com.douins.account.domain.vo.UserResponseVo;
import com.douins.account.service.UserAccountService;
import com.douins.account.service.UserService;
import com.douins.account.service.iml.UserAuthorityService;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.common.util.DateExpectedUtils;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SeqNumUtils;
import com.douins.common.util.SystemConstant;
import com.douins.insurance.service.InsuranceWorkService;
import com.douins.policy.domain.enums.PolicyStatus;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.domain.vo.PolicyRequest;
import com.douins.policy.domain.vo.PolicyRequestVo;
import com.douins.policy.domain.vo.PolicyResponse;
import com.douins.policy.domain.vo.PolicyResponseVo;
import com.douins.policy.domain.vo.PolicyResult;
import com.douins.policy.domain.vo.QueryPolicyRequest;
import com.douins.policy.service.PolicyManageService;
import com.douins.policy.service.PolicyUwService;
import com.douins.policy.service.Policyservice;
import com.douins.product.domain.model.Product;
import com.douins.product.service.ProductService;
import com.douins.trans.domain.enums.BusinessTransStatus;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.enums.TransChannel;
import com.douins.trans.domain.enums.TransType;
import com.douins.trans.domain.model.BusinessTrans;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.CanclePolicyRequest;
import com.douins.trans.service.TrasBusinessService;
import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
@Service
public class PolicyManageServiceImp implements PolicyManageService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	@Qualifier("policyUwServiceImpl")
	private PolicyUwService policyUwService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private TrasBusinessService trasBusinessService;
	@Autowired
	@Qualifier("liAnService")
	private InsuranceWorkService insuranceWorkService;
	
	@Autowired
	private Policyservice<PolicyMaster> policyservice;
	
	@Autowired
	private UserAuthorityService userAuthorityService;
	
	@Override
	@Transactional
	public PolicyResponse processUnderwrite(PolicyRequest policyRequest) {
		PolicyResponse response=new PolicyResponse();
		RequestTrans transVo=policyRequest.getRequestTrans();
		String transId= transVo.getTransId();
		ResponseTrans resTrans=new ResponseTrans();
		try {
			//更新客户信息
			this.updateUserInfo(policyRequest.getPolicyVo());
			//保存保单
			PolicyMaster policy = new PolicyMaster();
			resTrans= this.checkAndSavePolicy(policyRequest.getPolicyVo(),policy,transId);
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				//TODO核保------------policyUwService
				PolicyResult uwResult=policyUwService.doThirdUw(policy);
				if(uwResult.isSuccess()){
					policy.setUnderwritindTime(uwResult.getFinishTime());
					//policy.setSendCode(uwResult.getSendCode());
					policy.setSendCode(uwResult.getApplyno());
					policy.setProposalNo(uwResult.getSendCode());
					policy.setApplyno(uwResult.getApplyno());
					policy.setChanlFlowNo(policyRequest.getPolicyVo().getChanlFlowNo());
					
					
					this.updatePolicyStatus(policy, PolicyStatus.UWSUCCESS.getValue());
					resTrans=new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getValue(),transId);
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.UWFAILED.getValue());
					policy.setChanlFlowNo(policyRequest.getPolicyVo().getFlowNo());
					resTrans=new ResponseTrans(uwResult.getResultCode(),uwResult.getMsg(),transId);
				}
			}
			
			logger.info("核保结果="+ resTrans.getResponseCode());
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				PolicyRequestVo policyVo=new PolicyRequestVo();
				policyVo.setPolicyId(policy.getPolicyId());
				policyVo.setUserId(policyRequest.getPolicyVo().getUserId());
				List<PolicyResponseVo> resList=policyservice.findVoByCondition(policyVo);
               if(resList!=null&&resList.size()>0){
                   User sUser = userService.findByKey(policyVo.getUserId());
                   UserResponseVo tUser=new UserResponseVo();
                   BeanUtils.copyProperties(sUser, tUser);
                   resList.get(0).setUserInfo(tUser);
                   resList.get(0).setApplyNo(policy.getApplyno());
               }
               logger.info("记录数="+resList.size());
               response.setPolicyList(resList);
               trasBusinessService.saveTrasBusiness(creatBusinessTrans(policyRequest,transId, resList.get(0).getOrderNo()));
			}
		} catch (Exception e) {
			logger.error("policy pay error",e);
			resTrans=new ResponseTrans(ResponseCode.FAILED.getValue(),ResponseCode.FAILED.getName(),transId);
		}
		response.setResponseTrans(resTrans);
		return response;
	}
	
	
	private BusinessTrans creatBusinessTrans(PolicyRequest policyRequest,String transId, String orderNumber) {
	     BusinessTrans businessTrans= new BusinessTrans();
         businessTrans.setBusinessId(policyRequest.getPolicyVo().getInsuredId());
         businessTrans.setOpUser(SystemConstant.OP_USER);
         businessTrans.setCreateDate(new Date());
         //TODO
         businessTrans.setIsvalid(SystemConstant.ISVALID_YES);
         businessTrans.setPayMoney(policyRequest.getPolicyVo().getRepayAmount());
         String transChannel = policyRequest.getRequestTrans().getTransChannel();
         if(TransChannel.AND.getValue().equals(transChannel)){
        	 businessTrans.setTransChannel(TransChannel.AND.getValue());
         }
         if(TransChannel.IOS.getValue().equals(transChannel)){
        	 businessTrans.setTransChannel(TransChannel.IOS.getValue());
         }
         businessTrans.setStatus(BusinessTransStatus.SUCCESS.getValue());
         //TODO
         businessTrans.setTransNo(orderNumber);
         businessTrans.setTransType(TransType.POLICYUW.getValue());
         businessTrans.setTransId(transId);
         businessTrans.setTransTime(new Date());
         businessTrans.setUpdateDate(new Date());
         businessTrans.setTransUserToken(policyRequest.getPolicyVo().getUserId());
          //businessTrans.setTransChannelNo(policyRequest.getPolicyVo().);
         //businessTrans
         //购买保单时候把保单id传来
         businessTrans.setTransInsureId(policyRequest.getPolicyVo().getProductId());
         businessTrans.setTransProductName(policyRequest.getPolicyVo().getProductName());
		return businessTrans;
	}
	

	@Override
	@Transactional
	@Deprecated
	public PolicyResponse processInsuredAndPay(PolicyRequest policyRequest) {
		PolicyResponse response=new PolicyResponse();
		RequestTrans transVo=policyRequest.getRequestTrans();
		String transId=transVo.getTransId();
		ResponseTrans resTrans=null;
		try {
			//保存保单
			PolicyMaster policy=new PolicyMaster();
			resTrans= this.checkInsurePolicy(policyRequest.getPolicyVo(),policy,transId);
			policy.setApplyno(policyRequest.getPolicyVo().getApplyno()); //获取保险公司返回的投保单号，用于发送承保请求
			Product product = productService.findByKey(policy.getProductId());
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
					policy.setPayTime(new Date());
					// 预期总收益
//					policy.setTotalRevenue(CalculateUtils.calculateTotalRevenueByDay(policy.getTotalPrem(), 
//							product.getExpectRate(), policy.getValidateDate(), policy.getExpireDate()));
					// 赎回金额
					//policy.setRepayAmount(policy.getTotalPrem().add(policy.getTotalRevenue()));
					this.updatePolicyStatus(policy, PolicyStatus.PAYSUCCESS.getValue());
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.PAYFAILED.getValue());
				}
			//承保
			//修改产品可售份额和累计销售份额
			int stock = product.getStock();
			int saleNum = product.getSaleNum();
			stock =  (int) (product.getStock() - policy.getApplyNum());
			saleNum = (int) (product.getSaleNum() + policy.getApplyNum());
			product.setStock(stock);
			product.setSaleNum(saleNum);
			SaveEntityUtils.setUpdateForEntity(product, "");
			productService.updateAfterPaySuccess("", product);
			
			String insuranceId = policy.getProductId();
			//String orderNo = policy.getOrderNo();
			String userId = policy.getUserId();
			BusinessTrans businessTrans= new BusinessTrans();
			businessTrans.setTransInsureId(insuranceId);
			businessTrans.setTransType(TransType.POLICYUW.getValue());
			businessTrans.setTransUserToken(userId);
			List<BusinessTrans> businessTransList = trasBusinessService.getList(businessTrans);
			BusinessTrans trans =new BusinessTrans();
			trans.setTransProductName(product.getProductName());
			if(!CollectionUtils.isEmpty(businessTransList)){
				BusinessTrans businessTrans2 = businessTransList.get(0);
				BeanUtils.copyProperties(businessTrans2, trans);
				trans.setTransId(UUID.randomUUID().toString());
				trans.setTransNo(UUID.randomUUID().toString());
				trans.setBusinessId(UUID.randomUUID().toString());
				trans.setTransType(TransType.POLICYINSURE.getValue());
				trans.setIsvalid(SystemConstant.ISVALID_NO);
				trans.setUpdateDate(new Date());
				trans.setCreateDate(new Date());
				trans.setTransTime(new Date());
				trasBusinessService.saveTrasBusiness(trans);
			}
			
			//承保
			PolicyResult insureResult=policyUwService.doThirdInsure(policy);
			if(insureResult.isSuccess()){
				//承包成功更改保单状态
				policy.setPolicyCode(insureResult.getPolicyCode());
				this.updatePolicyStatus(policy, PolicyStatus.INSURESUCCESS.getValue());
				
				trans.setTransUserToken(userId);
				trans.setTransId(UUID.randomUUID().toString());
				trans.setBusinessId(UUID.randomUUID().toString());
				trans.setTransNo(UUID.randomUUID().toString());
				trans.setTransType(TransType.BUY.getValue());
				trans.setIsvalid(SystemConstant.ISVALID_YES);
				trans.setStatus(BusinessTransStatus.SUCCESS.getValue());
				trans.setUpdateDate(new Date());
				trans.setTransTime(new Date());
				trans.setPayMoney(policy.getTotalPrem());
				String transType = policyRequest.getRequestTrans().getTransType();
				trans.setTransChannel(transType);
				
				trans.setCreateDate(new Date());
				trasBusinessService.saveTrasBusiness(trans);
				policy.setApplyno(insureResult.getApplyno());
				policy.setPolicyCode(insureResult.getPolicyCode());
				policy.setPolicyUrl(insureResult.getPolicyUrl());
				this.updatePolicyStatus(policy, PolicyStatus.INSURESUCCESS.getValue());
				resTrans=new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getName(),transId);
				
			}else{
				this.updatePolicyStatus(policy, PolicyStatus.INSUREFAILED.getValue());
				resTrans=new ResponseTrans(insureResult.getResultCode(),insureResult.getMsg(),transId);
				trans.setStatus(BusinessTransStatus.FAILURE.getValue());
				trans.setIsvalid(SystemConstant.ISVALID_NO);
				trans.setTransTime(new Date());
				trans.setPayMoney(policy.getTotalPrem());
				trasBusinessService.saveTrasBusiness(trans);
			}
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				PolicyRequestVo policyVo=new PolicyRequestVo();
				policyVo.setPolicyId(policy.getPolicyId());
				policyVo.setUserId(policyRequest.getPolicyVo().getUserId());
				policyVo.setPolicyUrl(policy.getPolicyUrl());
//				response.setPolicyList(this.findVoByCondition(policyVo));
				List<PolicyResponseVo> resList=policyservice.findVoByCondition(policyVo);
				    if(resList!=null&&resList.size()>0){
				    User sUser = userService.findByKey(policyVo.getUserId());
				    UserResponseVo tUser=new UserResponseVo();
				    BeanUtils.copyProperties(sUser, tUser);
				    resList.get(0).setUserInfo(tUser);
				    resList.get(0).setPolicyUrl(policyVo.getPolicyUrl());
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
	@Override
	public PolicyResult queryPolicy(QueryPolicyRequest queryPolicyRequest) {
		
		return null;
		
	}
	
	private void updatePolicyStatus(PolicyMaster policy, String status) throws DataBaseAccessException {
		policy.setStatus(status);
		SaveEntityUtils.setUpdateForEntity(policy,SystemConstant.OP_USER);
		policyservice.update(SystemConstant.OP_USER, policy);
	}
	
	private ResponseTrans checkAndSavePolicy(PolicyRequestVo policyVo,PolicyMaster policy,String transId) throws Exception{
		if(nullNotSafe(policyVo.getUserId())
				||nullNotSafe(policyVo.getProductId())
				||nullNotSafe(policyVo.getInsuredId())
				||nullNotSafe(policyVo.getApplicantId())){
			 return new ResponseTrans(ResponseCode.DATAREAD_ERROR.getValue(),ResponseCode.DATAREAD_ERROR.getName(),transId);
		}
		
		Product product=productService.findByKey(policyVo.getProductId());
		// marked by G.F. 2015-11-02
//		if (!policyVo.getTotalPrem().equals(
//				product.getUnitPrem().multiply(
//						new BigDecimal(policyVo.getApplyNum())))) {
//			return new ResponseTrans(ResponseCode.POLICYTOTALPREM.getValue(),ResponseCode.POLICYTOTALPREM.getName(),transId);
//		}
		
		BigDecimal totalPrem = product.getUnitPrem().multiply(new BigDecimal(policyVo.getApplyNum()));
		if(policyVo.getTotalPrem().compareTo(totalPrem) != 0){
		    return new ResponseTrans(ResponseCode.POLICYTOTALPREM.getValue(),ResponseCode.POLICYTOTALPREM.getName(),transId);
		}
		
		ResponseCode responseCode=ResponseCode.SUCCESS;
		if(nullNotSafe(policyVo.getPolicyId())){
			//保存保单
			BeanUtils.copyProperties(policyVo, policy);
			policy.setInsuranceId(product.getInsuranceId());
			policy.setApplyTime(new Date());
			policy.setStatus(PolicyStatus.UWINIT.getValue());
			policy.setPrem(product.getUnitPrem());
			policy.setOrderNo(SeqNumUtils.geneTxSN(SeqNumUtils.ORDER_PREFIX));
			policy.setPayOrderNo(SeqNumUtils.geneTxSN(SeqNumUtils.PAY_PREFIX));
			policy.setValidateDate(DateExpectedUtils.validateDate(DateUtils.addDay(policy.getApplyTime(), 1)));
			policy.setExpireDate(DateExpectedUtils.expireDate(DateExpectedUtils.expectedDate(policy.getValidateDate(),product.getPeriodType(),
					new BigDecimal(product.getProductPeriod()))));
			// 保存犹豫期
			//policy.setHesitateTerm(product.getHesitateTerm());
			if(nullNotSafe(policy.getRelation())){
				policy.setRelation("1");//默认本人
			}
			policyservice.save(SystemConstant.OP_USER, policy);
		}else{
			//policyservice.update(SystemConstant.OP_USER, policyVo);
			policyservice.update(SystemConstant.OP_USER, policy);
		
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
		
		 PolicyMaster policyMaster=policyservice.findByKey(policyVo.getPolicyId());
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

	
	public boolean nullNotSafe(String text){
		return StringUtils.isBlank(text);
	}


	@Override
	@Transactional
	public PolicyResult canclePolicy(CanclePolicyRequest canclePolicyRequest) {
		//TODO
		PolicyResult policyResult=insuranceWorkService.canclePolicy(canclePolicyRequest.getCanclePolicyRequestVo());
		UserAuthority userAuthority = userAuthorityService.findUserByToken(canclePolicyRequest.getAccessToken());
		String uid = userAuthority.getUid();
		BusinessTrans businessTrans= new BusinessTrans();
		//businessTrans.setTransInsureId(policyNo);
		//businessTrans.setUserName(applicantName);
		businessTrans.setTransUserToken(uid);
		businessTrans.setStatus(BusinessTransStatus.SUCCESS.getValue());
		businessTrans.setTransType(TransType.BUY.getValue());
		List<BusinessTrans> businessTransList = trasBusinessService.getList(businessTrans);
		BusinessTrans businessTrans2 = businessTransList.get(0);
		BusinessTrans businessTrans1= new BusinessTrans();
		BeanUtils.copyProperties(businessTrans2, businessTrans1);
		if(businessTrans2!=null){
			businessTrans1.setTransType(TransType.POLICYCANCLEREQUEST.getValue());
			businessTrans1.setUpdateDate(new Date());
			businessTrans1.setTransTime(new Date());
			businessTrans1.setStatus(BusinessTransStatus.INIT.getValue());
			businessTrans1.setIsvalid(SystemConstant.ISVALID_NO);
			businessTrans1.setTransId(UUID.randomUUID().toString());
			trasBusinessService.saveTrasBusiness(businessTrans1);
		}
		if(policyResult.isSuccess()){
			businessTrans2.setStatus(BusinessTransStatus.SUCCESS.getValue());
			businessTrans2.setIsvalid(SystemConstant.ISVALID_YES);
			businessTrans2.setTransType(TransType.CHEBAO.getValue());
			businessTrans2.setTransTime(new Date());
			//保存交易记录
			trasBusinessService.saveTrasBusiness(creatBusinessTrans(businessTrans2));
			//更改保单状态 －－－退保处理中状态
			String policyMasterId = canclePolicyRequest.getCanclePolicyRequestVo().getPolicyMasterId();
			policyservice.updateStatus(policyMasterId,PolicyStatus.DRAW_BACK.getValue());
			//softDelete(policyMasterId);
		}else{
			businessTrans2.setStatus(BusinessTransStatus.FAILURE.getValue());
			businessTrans2.setIsvalid(SystemConstant.ISVALID_NO);
			businessTrans2.setTransType(TransType.CHEBAO.getValue());
			businessTrans2.setTransTime(new Date());
			trasBusinessService.saveTrasBusiness(creatBusinessTrans(businessTrans2));
			
		}
		return policyResult;
	}


	private void softDelete(String policyMasterId) {
		PolicyMaster master = new PolicyMaster();
		master.setPolicyId(policyMasterId);
		try {
			boolean delete = policyservice.delete("",master);
			if(delete){
				logger.info("delete policy Master Id["+policyMasterId+"] success" );
			}else{
				logger.info("delete policy Master Id["+policyMasterId+"] failed" );
			}
		} catch (DataBaseAccessException e) {
			logger.error("delete error["+policyMasterId+"]", e);
		}
	}


	private BusinessTrans creatBusinessTrans(BusinessTrans businessTrans2) {
		BusinessTrans businessTrans1= new BusinessTrans();
		 BeanUtils.copyProperties(businessTrans2, businessTrans1);
		if(businessTrans2!=null){
			businessTrans1.setTransType(TransType.CHEBAO.getValue());
			businessTrans1.setUpdateDate(new Date());
			businessTrans1.setTransTime(new Date());
			businessTrans1.setStatus(BusinessTransStatus.SUCCESS.getValue());
			businessTrans1.setIsvalid(SystemConstant.ISVALID_YES);
			businessTrans1.setTransId(UUID.randomUUID().toString());
		}
		return businessTrans1;
	}




	@Override
	public PolicyResponse processInsuredAndPay(PolicyMaster policy, CallBackRequest callBackRequest) {
		logger.info("start processInsuredAndPay policy id:"+policy.getPolicyId());
		//TODO 处理 callBackRequest
		PolicyResponse response=new PolicyResponse();
		ResponseTrans resTrans=null;
		Random random = new Random();
		String transId =System.currentTimeMillis()+""+random.nextInt(4);
		try {
			//保存保单
			//PolicyMaster policy=new PolicyMaster();
			resTrans= this.checkInsurePolicy(policy,transId);
			String applyno = policy.getApplyno();
			if(StringUtils.isNotEmpty(applyno)){
				policy.setApplyno(applyno); //获取保险公司返回的投保单号，用于发送承保请求
			}
			String sendCode = policy.getSendCode();
			if(StringUtils.isNotEmpty(sendCode)){
				policy.setApplyno(sendCode); //获取保险公司返回的投保单号，用于发送承保请求
			}
			Product product = productService.findByKey(policy.getProductId());
			logger.info("productId:"+policy.getProductId());
			if(ResponseCode.POLICYNOTPAY.getValue().equals(resTrans.getResponseCode())||ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
					this.updatePolicyStatus(policy, PolicyStatus.PAYSUCCESS.getValue());
				}else{
					this.updatePolicyStatus(policy, PolicyStatus.PAYFAILED.getValue());
				}
			//承保
			//修改产品可售份额和累计销售份额
			if(product!=null){
				int stock = product.getStock();
				int saleNum = product.getSaleNum();
				stock =  (int) (product.getStock() - policy.getApplyNum());
				saleNum = (int) (product.getSaleNum() + policy.getApplyNum());
				product.setStock(stock);
				product.setSaleNum(saleNum);
				SaveEntityUtils.setUpdateForEntity(product, "");
				productService.updateAfterPaySuccess("", product);
			}
			
			String insuranceId = policy.getProductId();
			//String orderNo = policy.getOrderNo();
			String userId = policy.getUserId();
			BusinessTrans businessTrans= new BusinessTrans();
			businessTrans.setTransInsureId(insuranceId);
			businessTrans.setTransType(TransType.POLICYUW.getValue());
			businessTrans.setTransUserToken(userId);
			List<BusinessTrans> businessTransList = trasBusinessService.getList(businessTrans);
			BusinessTrans trans =new BusinessTrans();
			trans.setTransProductName(product.getProductName());
			if(!CollectionUtils.isEmpty(businessTransList)){
				BusinessTrans businessTrans2 = businessTransList.get(0);
				BeanUtils.copyProperties(businessTrans2, trans);
				//TODO 渠道交易流水号
				trans.setTransNo(callBackRequest.getBody().getChanlFlowNo());
				//TODO 银行交易流水号
				trans.setTransId(callBackRequest.getBody().getFlowNo());
				trans.setBusinessId(UUID.randomUUID().toString());
				trans.setTransType(TransType.POLICYINSURE.getValue());
				trans.setIsvalid(SystemConstant.ISVALID_NO);
				trans.setUpdateDate(new Date());
				trans.setCreateDate(new Date());
				trans.setTransTime(new Date());
				trasBusinessService.saveTrasBusiness(trans);
			}
			
			//承保
			PolicyResult insureResult=policyUwService.doThirdInsure(policy);
			if(insureResult.isSuccess()){
				//承包成功更改保单状态
				policy.setPolicyCode(insureResult.getPolicyCode());
				this.updatePolicyStatus(policy, PolicyStatus.INSURESUCCESS.getValue());
				
				trans.setTransUserToken(userId);
				//TODO 渠道交易流水号
				trans.setTransNo(callBackRequest.getBody().getChanlFlowNo());
				//TODO 银行交易流水号
				trans.setTransId(callBackRequest.getBody().getFlowNo());
				trans.setBusinessId(UUID.randomUUID().toString());
				
				logger.info("TransType.BUY.getValue():============="+TransType.BUY.getValue());
				trans.setTransType(TransType.BUY.getValue());
				trans.setIsvalid(SystemConstant.ISVALID_YES);
				trans.setStatus(BusinessTransStatus.SUCCESS.getValue());
				trans.setUpdateDate(new Date());
				trans.setTransTime(new Date());
				trans.setPayMoney(policy.getTotalPrem());
				String transType = policy.getStatus();
				trans.setTransChannel(transType);
				
				trans.setCreateDate(new Date());
				logger.info("zuo tian jia de dai ma ");
				BusinessTrans findTrasBusiness = trasBusinessService.findTrasBusiness(callBackRequest.getBody().getChanlFlowNo());
				 if(findTrasBusiness == null){
					logger.info("南粤第一次callback提交－保存");
					trasBusinessService.saveTrasBusiness(trans);
				}else if(!trans.getTransNo().equals(findTrasBusiness.getTransNo())){
					logger.info("南粤第一次callback提交－保存");
					trasBusinessService.saveTrasBusiness(trans);
				}else{
					logger.info("南粤第二次callback提交不保存");
				}
				
				policy.setApplyno(insureResult.getApplyno());
				policy.setPolicyCode(insureResult.getPolicyCode());
				policy.setPolicyUrl(insureResult.getPolicyUrl());
				this.updatePolicyStatus(policy, PolicyStatus.INSURESUCCESS.getValue());
				resTrans=new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getName(),transId);
				
			}else{
				this.updatePolicyStatus(policy, PolicyStatus.INSUREFAILED.getValue());
				resTrans=new ResponseTrans(insureResult.getResultCode(),insureResult.getMsg(),transId);
				trans.setStatus(BusinessTransStatus.FAILURE.getValue());
				trans.setIsvalid(SystemConstant.ISVALID_YES);
				trans.setTransTime(new Date());
				trans.setPayMoney(policy.getTotalPrem());
				trasBusinessService.saveTrasBusiness(trans);
			}
			if(ResponseCode.SUCCESS.getValue().equals(resTrans.getResponseCode())){
				PolicyRequestVo policyVo=new PolicyRequestVo();
				policyVo.setPolicyId(policy.getPolicyId());
				policyVo.setUserId(policy.getUserId());
				policyVo.setPolicyUrl(policy.getPolicyUrl());
                //response.setPolicyList(this.findVoByCondition(policyVo));
				List<PolicyResponseVo> resList=policyservice.findVoByCondition(policyVo);
				    if(resList!=null&&resList.size()>0){
				    User sUser = userService.findByKey(policyVo.getUserId());
				    UserResponseVo tUser=new UserResponseVo();
				    BeanUtils.copyProperties(sUser, tUser);
				    resList.get(0).setUserInfo(tUser);
				    resList.get(0).setPolicyUrl(policyVo.getPolicyUrl());
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


	private ResponseTrans checkInsurePolicy(PolicyMaster policyMaster, String transId) {
		if(StringUtils.isBlank(policyMaster.getUserId())
				||StringUtils.isBlank(policyMaster.getPolicyId())){
			 return new ResponseTrans(ResponseCode.DATAREAD_ERROR.getValue(),ResponseCode.DATAREAD_ERROR.getName(),transId);
		}
		
		User user=userService.findByKey(policyMaster.getUserId());
		// marked by G.F. 不保存交易密码
//		if(!user.getPayPassword().equals(policyVo.getPayPassword())){
//			 return new ResponseTrans(ResponseCode.ORDERPAYPASSWORD.getValue(),ResponseCode.ORDERPAYPASSWORD.getName(),transId);
//		}
		
//		 PolicyMaster policyMaster=policyservice.findByKey(policyMaster.getPolicyId());
//		if(policyMaster==null||!policyMaster.getUserId().equals(policyMaster.getUserId())){
//			 return new ResponseTrans(ResponseCode.POLICYISNOTEXISTS.getValue(),ResponseCode.POLICYISNOTEXISTS.getName(),transId);
//		}
		
		//BeanUtils.copyProperties(policyMaster, policy);
		
		if(!PolicyStatus.UWSUCCESS.getValue().equals(policyMaster.getStatus())&&
				!PolicyStatus.PAYFAILED.getValue().equals(policyMaster.getStatus())){
			return new ResponseTrans(ResponseCode.POLICYNOTPAY.getValue(),ResponseCode.POLICYNOTPAY.getName(),transId);
		}
		
		UserAccount userAccount = userAccountService.findOneByUserId(policyMaster.getUserId());
		if(userAccount.getAccountBalance().compareTo(policyMaster.getTotalPrem())<0){
			return new ResponseTrans(ResponseCode.USERBALANCEERROR.getValue(),ResponseCode.USERBALANCEERROR.getName(),transId);
		}
		
		return new ResponseTrans(ResponseCode.SUCCESS.getValue(),ResponseCode.SUCCESS.getName(),transId);
	}


	


	

}
 