package com.douins.account.service.iml;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.account.domain.vo.UserAccountRequestVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.account.dao.UserAccountDao;
import com.douins.account.domain.enums.DetailIo;
import com.douins.account.domain.enums.DetailType;
import com.douins.account.domain.enums.OpenStatus;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.domain.model.UserPayAccount;
import com.douins.account.service.UserAccountDetailService;
import com.douins.account.service.UserAccountOpenApplyService;
import com.douins.account.service.UserAccountService;
import com.douins.account.service.UserPayAccountService;
import com.douins.apply.domain.vo.ApplyResult;
import com.douins.pay.domain.enums.PayStatus;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.account.domain.model.User;
import com.douins.account.service.UserService;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.SaveEntityUtils;
import com.douins.common.util.SeqNumUtils;
import com.douins.common.util.SystemConstant;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service
public class UserAccountServiceImpl implements UserAccountService{
	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
//	@Autowired
//	private BaseDao<UserAccount> baseDao;
	@Autowired
	private UserAccountOpenApplyService userAccountOpenApplyService;
	@Autowired
	private UserPayAccountService userPayAccountService;
	@Autowired
	private UserAccountDetailService userAccountDetailService;
	@Autowired
	private UserService userService;
	
	@Inject
	private UserAccountDao accountDao;
	
	//String mapperName = UserAccount.class.getName();
	
	@Override
	@Transactional(rollbackFor = Exception.class)
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
	@Transactional(rollbackFor = Exception.class)
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
		//return this.baseDao.getList(mapperName + "Mapper.getList", paramT);
	    return accountDao.getList(paramT);
	}

	@Override
	public Page<UserAccount> getPage(UserAccount paramT, Page<UserAccount> paramPage) {
//		if (paramPage != null) {
//			return this.baseDao.getList(mapperName + "Mapper.getList", paramT, paramPage);
//		}
		return null;
	}

	@Override
	public UserAccount findByKey(String paramString) {
		//return baseDao.get(mapperName + "Mapper.selectByPrimaryKey", paramString);
	    return accountDao.findByUserId(paramString);
	}

	@Override
	public boolean save(String paramString, UserAccount paramT) throws DataBaseAccessException {
//		try {
//			return baseDao.save(mapperName + "Mapper.insert", paramT) > 0;
//		} catch (DataBaseAccessException e) {
//			logger.error("create DataBaseAccessException", e);
//			throw e;
//		}
	    
	    accountDao.add(paramT);
	    return Boolean.TRUE;
	}

	@Override
	public boolean update(String paramString, UserAccount paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.save(mapperName + "Mapper.updateByPrimaryKey", paramT) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("update DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    accountDao.update(paramT);
	    return Boolean.TRUE;
	}

	@Override
	public boolean delete(String paramString, UserAccount paramT) throws DataBaseAccessException {
//		try {
//			if (baseDao.delete(mapperName + "Mapper.softDeleteByPrimaryKey", paramT.getUserAccountId()) > 0) {
//				return Boolean.TRUE;
//			}
//		} catch (DataBaseAccessException e) {
//			logger.error("delete DataBaseAccessException", e);
//			throw e;
//		}
//		return Boolean.FALSE;
	    accountDao.delete(paramT.getUserAccountId());
	    return Boolean.TRUE;
	}
	
}