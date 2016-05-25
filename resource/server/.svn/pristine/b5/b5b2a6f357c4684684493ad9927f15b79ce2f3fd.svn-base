package com.douins.account.service.iml;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douins.account.dao.UserAccountDao;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.vo.BindCardReponseVO;
import com.douins.account.domain.vo.BindCardRequest;
import com.douins.account.domain.vo.BindCardRequestVO;
import com.douins.account.service.ProcessBindOrUnBindCardService;
import com.douins.bank.dao.BankResponeDao;
import com.douins.bank.service.BankServiceIFC;
import com.douins.common.bankUtil.BankInfo;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;

@Service
public class ProcessBindOrUnBindCardServiceImpl implements ProcessBindOrUnBindCardService {

	@Autowired
	private UserAccountDao userAccountDao;

	@Resource(name = "nyBankService")
	private BankServiceIFC nyBankService;
	
	@Autowired
	private BankResponeDao bankResponeDao;

	@Override
	@Transactional
	public ResponseTransVo<BindCardReponseVO> processBindCard(BindCardRequest bindCardRequest) {

		ResponseCode responseCode = ResponseCode.FAILED;

		BindCardReponseVO bindCardReponseVO = new BindCardReponseVO();


		String accessToken = bindCardRequest.getAccessToken();// 令牌token

		BindCardRequestVO bindCardVo = bindCardRequest.getRequestParams();// 获取绑卡VO
																			// 对象

		UserAccount userAccount = userAccountDao.findUserAccountByToken(accessToken);

		String creditCard = userAccount.getCreditCard();
		String bindCardFlag = userAccount.getBindCardFlag();
		String bindCardFlowNo = System.currentTimeMillis() + "";
		
		List<String> list= bankResponeDao.getBankName(bindCardVo.getAccountNo().trim());

		if (StringUtils.isNotBlank(creditCard) && "1".equals(bindCardFlag)) {
			// 1:已经绑定，不允许继续绑卡
			responseCode = ResponseCode.HASBINDCARD;

		} else if(list.isEmpty()){
			
			// 1:利安保险不支持的
			responseCode = ResponseCode.NOTSUPPORTCARD;
			
		}else {

			// 2:插入银行卡号
			userAccount.setBindCardFlag("0");
			userAccount.setCreditCard(bindCardVo.getAccountNo());
			userAccount.setBindCardFlowNo(bindCardFlowNo);
			userAccount.setBindPhone(bindCardVo.getBindPhone());

			int resultCode = userAccountDao.updateCreditCardByAccountId(userAccount);

			if (resultCode == 1) {

				responseCode = ResponseCode.SUCCESS;

				// 3:生成南粤绑卡网关地址URL
				String url = getBindOrUnbindCardUrl(userAccount,1);

				bindCardReponseVO.setNyBindCardUrl(url);

			}

		}
		// 4:生成响应信息
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(),
				bindCardRequest.getRequestTrans().getTransId());
	
		
		//5:返回bindCardResponse对象
		return new ResponseTransVo<BindCardReponseVO>(responseTrans,accessToken,bindCardReponseVO);
	}

	@Override
	@Transactional
	public ResponseTransVo<BindCardReponseVO> processUnBindCard(BindCardRequest bindCardRequest) {
		ResponseCode responseCode = ResponseCode.FAILED;
		BindCardReponseVO bindCardReponseVO = new BindCardReponseVO();
		String unbindCardFlowNo = System.currentTimeMillis() + "";

		String accessToken = bindCardRequest.getAccessToken();// 令牌token

		BindCardRequestVO unbindCardVo = bindCardRequest.getRequestParams();// 获取解绑卡VO对象

		// 获取账户信息
		UserAccount userAccount = userAccountDao.findUserAccountByToken(accessToken);

// 这段逻辑 不需要，先注释
//		// 1:绑卡预留手机号校验
//		if (unbindCardVo.getBindPhone().equals(userAccount.getBindPhone())) {

			BigDecimal accountBalance = userAccount.getAccountBalance();
			// 2:查询电子账户是否有余额
			if (accountBalance.equals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_DOWN))) {
				// 3:userAccount 更新unbindFlowNo 解绑流水号

				userAccount.setUnbindCardFlowNo(unbindCardFlowNo);

				userAccountDao.update(userAccount);

				responseCode = ResponseCode.SUCCESS;

				// 4:生成南粤解绑网关地址URL
				String url = getBindOrUnbindCardUrl(userAccount, 2);
				bindCardReponseVO.setNyUnbindCardUrl(url);

			} else {
				responseCode = ResponseCode.ACCOUNTHASBALANCE;//电子账户有余额，不能解绑
			}

//		} else {
//			responseCode = ResponseCode.PHONENOTMATCH;//预留手机号 不匹配
//		}
		// 5:生成响应信息
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(),bindCardRequest.getRequestTrans().getTransId());

		// 6:返回bindCardResponse对象
		return new ResponseTransVo<BindCardReponseVO>(responseTrans,accessToken,bindCardReponseVO);

	}


	// 更新 绑卡状态 －－》绑定成功
	@Override
	public int updateUserAccountBindCardFlagByFlowNo(String bindCardFlag, String flowNo) {
		// TODO Auto-generated method stub
		return userAccountDao.updateUserAccountBindCardByFlowNo(bindCardFlag, flowNo);
	}
	
	//解除绑定 －－删除 绑定卡号，手机号，绑定流水号
	@Override
	public int updateUserAccountUnBindCardByUnbindFlowNo(String unbindflowNo){
	
		return userAccountDao.updateUserAccountUnbindCardByUnbindFlowNo(unbindflowNo);
	}
	
	// 生成 南粤 绑卡,解绑 URL ,type==1 绑卡 ，type==2 解绑
	private String getBindOrUnbindCardUrl(UserAccount account,int type) {
		String chanlNo = "PTP0000005";
		String baseUrl="";
		String callbackUrl="";
		String flowNo ="";
		if(type==1){
			baseUrl="http://14.23.114.92:81//moneymanager/H5/ptp_ej_add_card.html?";//绑定
			
				callbackUrl="http://dev.douins.com/api/bankCard/bindCardCallback";//绑卡回调API
				//callbackUrl="http://192.168.1.114:8080/api/bankCard/bindCardCallback";//绑卡回调API
			
			
			flowNo = account.getBindCardFlowNo();
		}else if(type==2){
			baseUrl="http://14.23.114.92:81/moneymanager/H5/ptp_unbind.html?";// 解绑
			callbackUrl="http://dev.douins.com/api/bankCard/unbindCardCallback";//解绑回调API
			
			//callbackUrl="http://192.168.1.114:8080/api/bankCard/unbindCardCallback";//解绑回调API
			flowNo = account.getUnbindCardFlowNo();
		}
		
		//String acctNoId = account.getUserAccountId();
		String acctNoId = account.getVirtualAccountId();
		
		
		String accountNo = account.getCreditCard();

		//String plainSignMsg = chanlNo + "|"+ flowNo + "|" + acctNoId + "|" + callbackUrl + "|" + accountNo;
		
		String plainSignMsg = chanlNo + "|" + acctNoId + "|" + callbackUrl;
		
		System.out.println("眼前内容："+plainSignMsg);
		String signMsg = nyBankService.signForRequest(plainSignMsg);

		StringBuffer buffer = new StringBuffer();

		buffer.append(baseUrl).append("chanlNo=").append(chanlNo).append("&chanlFlowNo=").append(flowNo)
				.append("&acctNoId=").append(acctNoId).append("&url=").append(callbackUrl).append("&accountNo=")
				.append(accountNo).append("&signMsg=").append(signMsg).toString();

		return buffer.toString();
	}

	@Override
	public ResponseTransVo<BindCardReponseVO> getBindCardInfo(BindCardRequest bindCardRequest) {

		ResponseCode responseCode = ResponseCode.FAILED;

		BindCardReponseVO bindCardReponseVO = new BindCardReponseVO();


		String accessToken = bindCardRequest.getAccessToken();// 令牌token

		UserAccount userAccount = userAccountDao.findUserAccountByToken(accessToken);
		if (StringUtils.isNotBlank(userAccount.getCreditCard()) && "1".equals(userAccount.getBindCardFlag())) {
		String creditCard = userAccount.getCreditCard();
		String baseCard="**** **** **** ";
	
		String bankName =BankInfo.getNameOfBank(creditCard.substring(0,6)) ;
		bindCardReponseVO.setAccountNo(baseCard+creditCard.substring(creditCard.length()-4,creditCard.length()));
		
		if(StringUtils.isBlank(bankName)){
			bankName="银联卡";
		}
		
		bindCardReponseVO.setBankName(bankName);
		
		}
		responseCode = ResponseCode.SUCCESS;
		// 4:生成响应信息
		ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(),
				bindCardRequest.getRequestTrans().getTransId());
	
		
		//5:返回bindCardResponse对象
		return new ResponseTransVo<BindCardReponseVO>(responseTrans,accessToken,bindCardReponseVO);
	}
}
