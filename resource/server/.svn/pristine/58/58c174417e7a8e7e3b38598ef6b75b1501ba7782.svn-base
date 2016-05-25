package com.mango.fortune.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.api.userAccountAPI.vo.UserAccountRequestVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.account.enums.DetailIo;
import com.mango.fortune.account.enums.DetailType;
import com.mango.fortune.account.enums.OpenStatus;
import com.mango.fortune.account.model.UserAccount;
import com.mango.fortune.account.model.UserAccountDetail;
import com.mango.fortune.account.model.UserPayAccount;
import com.mango.fortune.account.service.UserAccountDetailService;
import com.mango.fortune.account.service.UserAccountOpenApplyService;
import com.mango.fortune.account.service.UserAccountService;
import com.mango.fortune.account.service.UserPayAccountService;
import com.mango.fortune.apply.vo.ApplyResult;
import com.mango.fortune.pay.enums.PayStatus;
import com.mango.fortune.trans.enums.ResponseCode;
import com.mango.fortune.user.model.User;
import com.mango.fortune.user.service.UserService;
import com.mango.fortune.util.JsonOnlineUtils;
import com.mango.fortune.util.SaveEntityUtils;
import com.mango.fortune.util.SeqNumUtils;
import com.mango.fortune.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	@Autowired
	private BaseDao<UserAccount> baseDao;
	@Autowired
	private UserAccountOpenApplyService userAccountOpenApplyService;
	@Autowired
	private UserPayAccountService userPayAccountService;
	@Autowired
	private UserAccountDetailService userAccountDetailService;
	@Autowired
	private UserService userService;
	
	
	String mapperName = UserAccount.class.getName();
	
	@Override
	public ResponseCode add(String userId,String transChannel) throws Exception {
		ResponseCode responseCode=ResponseCode.FAILED;
		boolean isApplyOpen=false;
		UserAccount userAccount=null;
		
		UserAccount paraAccount=new UserAccount();
		paraAccount.setUserId(userId);
		List<UserAccount> list=this.findByCondition(paraAccount);
		if(list!=null&&list.size()>0){
			userAccount =list.get(0);
			if(userAccount.getStatus().equals(OpenStatus.SUCCESS.getValue())){
				responseCode=ResponseCode.USEROPENERROR;
			}else{
				isApplyOpen=true;
			}
		}else{
			userAccount =this.addUserAccount(userId);
			isApplyOpen=true;
		}
		
		if(isApplyOpen){
			ApplyResult result=userAccountOpenApplyService.openApply(userAccount.getUserAccountId(),userId,transChannel);
			if(result.isSuccess()){
				responseCode=ResponseCode.SUCCESS;
			}
		}
		return responseCode;
	}

	@Override
	public ResponseCode pay(UserAccountRequestVo userAccountVo,String transChannel) throws Exception {
		ResponseCode responseCode=ResponseCode.FAILED;
		String userId=userAccountVo.getUserId();
		
		User user=userService.findByKey(userId);
		if(!user.getPayPassword().equals(userAccountVo.getPayPassword())){
			return ResponseCode.ORDERPAYPASSWORD;
		}
		
		boolean isVerity=true;
		UserAccount userAccount=this.findOneByUserId(userId);
		if(userAccount==null){
			isVerity=false;
			responseCode=ResponseCode.USERNOTOPENERROR;
		}else{
			if(!userAccount.getUserAccountId().equals(userAccountVo.getUserAccountId())){
				isVerity=false;
				responseCode=ResponseCode.USEROPENINFOERROR;
			}else if(userAccountVo.getDetailType().equals(DetailType.WITHDRAW.getValue())){
				if(userAccountVo.getApplyAmount().compareTo(userAccount.getAccountBalance())>0){
					isVerity=false;
					responseCode=ResponseCode.USERBALANCEERROR;
				}
			}
		}
		
		if (isVerity) {
			ApplyResult result=this.doPayBusiness(userAccountVo,userAccount,transChannel);
			if(result.isSuccess()){
				responseCode=ResponseCode.SUCCESS;
			}
		}
		return responseCode;
	}
	
	private ApplyResult doPayBusiness(UserAccountRequestVo userAccountVo,UserAccount userAccount,String transChannel)
			throws Exception {
		
		if (userAccountVo.getDetailType().equals(DetailType.RECHARGE.getValue())) {
			return doRecharge(userAccountVo,userAccount,transChannel);
		} else if (userAccountVo.getDetailType().equals(DetailType.WITHDRAW.getValue())) {
			return doWithdraw(userAccountVo,userAccount,transChannel);
		}
		
		return null;
	}
	
	private ApplyResult doRecharge(UserAccountRequestVo userAccountVo,
			UserAccount userAccount, String transChannel) throws Exception {
		UserPayAccount fromAccount = userPayAccountService.findOneByUserId(userAccountVo.getUserId());
		System.out.println("fromAcount**************"+JsonOnlineUtils.object2json(fromAccount));
		
		UserAccountDetail detail=new UserAccountDetail();
		detail.setUserAccountId(userAccount.getUserAccountId());
		detail.setBusinessId(fromAccount.getAccountId());
		detail.setBusinessNo(SeqNumUtils.geneTxSN(SeqNumUtils.DISCHARGE_PREFIX));
		detail.setApplyAmount(userAccountVo.getApplyAmount());
		detail.setDetailType(DetailType.RECHARGE.getValue());
		detail.setDetailIo(DetailIo.IN.getValue());
		detail.setFromAccountName(fromAccount.getCardholderName());
		detail.setFromAccountNo(fromAccount.getAccountNo());
		detail.setFromBankCode(fromAccount.getBankCode());
		detail.setToAccountName("");
		detail.setToAccountNo(userAccount.getVirtualAccountNo());
		detail.setToBankCode("");
		detail.setStatus(PayStatus.NOTTOPAY.getValue());
		detail.setPrincipalAmount(userAccountVo.getApplyAmount());

		ApplyResult result = userAccountDetailService.doBusiness(detail, transChannel);
		return result;
	}
	
	private ApplyResult doWithdraw(UserAccountRequestVo userAccountVo,
			UserAccount userAccount, String transChannel) throws Exception {
		UserPayAccount toAccount = userPayAccountService.findOneByUserId(userAccountVo.getUserId());

		UserAccountDetail detail=new UserAccountDetail();
		detail.setUserAccountId(userAccount.getUserAccountId());
		detail.setBusinessId(userAccount.getUserAccountId());
		detail.setBusinessNo(SeqNumUtils.geneTxSN(SeqNumUtils.DISCHARGE_PREFIX));
		detail.setApplyAmount(userAccountVo.getApplyAmount());
		detail.setDetailType( DetailType.WITHDRAW.getValue());
		detail.setDetailIo(DetailIo.OUT.getValue());
		detail.setFromAccountName("");
		detail.setFromAccountNo(userAccount.getVirtualAccountNo());
		detail.setFromBankCode("");
		detail.setToAccountName( toAccount.getCardholderName());
		detail.setToAccountNo(toAccount.getAccountNo());
		detail.setToBankCode(toAccount.getBankCode());
		detail.setStatus(PayStatus.NOTTOPAY.getValue());
		detail.setPrincipalAmount(userAccountVo.getApplyAmount());
		
		ApplyResult result = userAccountDetailService.doBusiness(detail, transChannel);
		return result;
	}
	
	private UserAccount addUserAccount(String userId) throws DataBaseAccessException {
		UserAccount userAccount=new UserAccount();
		userAccount.setAccountAmount(new BigDecimal("0"));
		userAccount.setPoint(new BigDecimal("0"));
		userAccount.setAccountBalance(new BigDecimal("0"));
		userAccount.setPolicyValue(new BigDecimal("0"));
		userAccount.setLoanAmount(new BigDecimal("0"));
		userAccount.setSurrAmount(new BigDecimal("0"));
		userAccount.setRepayAmount(new BigDecimal("0"));
		userAccount.setUserId(userId);
		userAccount.setStatus(OpenStatus.INIT.getValue());
		SaveEntityUtils.initEntityBeforeInsert(userAccount, SystemConstant.OP_USER);
		this.save(SystemConstant.OP_USER, userAccount);
		return userAccount;
	}
	
	@Override
	public UserAccount findOneByUserId(String userId) {
		UserAccount paramT=new UserAccount();
		paramT.setUserId(userId);
		paramT.setStatus(OpenStatus.SUCCESS.getValue());
		List<UserAccount> list=this.findByCondition(paramT);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<UserAccount> findByCondition(UserAccount paramT) {
		return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	}

	@Override
	public Page<UserAccount> getPage(UserAccount paramT, Page<UserAccount> paramPage) {
		if (paramPage != null) {
			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
		}
		return null;
	}

	@Override
	public UserAccount findByKey(String paramString) {
		return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	}

	@Override
	public boolean save(String paramString, UserAccount paramT) throws DataBaseAccessException {
		try {
			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
		} catch (DataBaseAccessException e) {
			logger.error("create DataBaseAccessException", e);
			throw e;
		}
	}

	@Override
	public boolean update(String paramString, UserAccount paramT) throws DataBaseAccessException {
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
	public boolean delete(String paramString, UserAccount paramT) throws DataBaseAccessException {
		try {
			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getUserAccountId()) > 0) {
				return Boolean.TRUE;
			}
		} catch (DataBaseAccessException e) {
			logger.error("delete DataBaseAccessException", e);
			throw e;
		}
		return Boolean.FALSE;
	}
	
}