/**
 * 
 */
package com.douins.bank.service;

import java.math.BigDecimal;

import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccountDetail;
import com.douins.account.domain.vo.UserStatusInfoVo;
import com.douins.bank.domain.enums.BankTransType;
import com.douins.bank.domain.model.BankResponseModel;
import com.douins.bank.domain.model.nybc.BankDict;
import com.douins.bank.domain.model.nybc.CallBackRequest;
import com.douins.bank.domain.model.nybc.ProjecteRegistRequestBody;
import com.douins.bank.domain.vo.BankChangeRequest;
import com.douins.bank.domain.vo.BankChangeResponseVo;
import com.douins.bank.domain.vo.RegistAccountResponseVo;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: BankServiceIFC 
* @Description: 银行业务接口
* @author G. F. 
* @date 2015年12月3日 下午2:11:57 
*  
*/
public interface BankServiceIFC {
	/**
	 * 加载豆芽的私钥
	 * 
	 * @return
	 */
	public String loadPrivateKey();
    /**
     * 加载豆芽的公钥
     * @return
     */
    public String loadPublicKey();
    /**
     * 加载银行的公钥
     * @return
     */
    public String loadBankPublicKey();
    /**
     * 身份验证
     * @param user
     * @return
     */
    public ResponseCode verifyIdentity(User user);
    /**
     * 注册电子账户（开户）
     * @param user
     * @return
     */
    public RegistAccountResponseVo openAccount(User user);
    /**
     * 查询(同步)账户信息
     * @param token
     * @return
     */
    public ResponseCode queryAccount(String token);
    /**
     * 保存与银行的交易记录
     * @param user
     * @param transId   交易流水号，后台生成
     * @param chanlNo   银行渠道号
     * @param transType 交易类型
     */
    public void saveTransRecord(User user, String transId, String chanlNo, String transType);
    /**
     * 更新交易记录
     * @param response
     */
    public void updateTransRecord(BankResponseModel response);
    /**
     * 为网关的请求参数加签
     * @param Params
     * @return
     */
    public String signForRequest(String params);
    /**
     * 解析银行网关操作返回的参数信息
     * @param params
     * @param 
     * @return
     */
    public ResponseCode analyzeGatewayResponse(String params, String token);
    /**
     * 查询电子账户开户结果
     * @param token
     * @return
     */
    public ResponseCode queryRegistResult(String token);
    /**
     * 电子账户充值
     * @param token
     * @param amount    充值金额
     * @return
     */
    public ResponseCode recharge(String token, BigDecimal amount);
    /**
     * 消费／支付 或 撤单
     * @param token
     * @param transType     交易类型：消费；撤单
     * @param bAccount      商户账号
     * @param amount          交易金额
     * @return
     */
    public ResponseCode payment(String token, String bAccount, BigDecimal amoutn, BankTransType transType,String orderNo);
    /**
     * 批量查询用户交易记录
     * @param token
     * @return
     */
    public ResponseCode queryConsumeRecords(String token);
    /**
     * 单笔交易结果查询
     */
    public ResponseCode QuerySingleResults(String token,BankTransType transType,String channelSeq);
    
    /**
     * @param token
     * @param amount 提现金额
     * @return
     */
    public ResponseCode withdraw(String token, BigDecimal amount);
    /**
     * 保存账户金额变动信息
     */
    public void  saveAmountChange(String token ,BigDecimal amount,String transType,String tradeNo,String bankName,String bankCode,String accountName,String accountNo);
    /**
     * 更新账户金额变动信息
     */
    public void updateAmountChange(String tradeNo,String status);
    /**
     * 解除银行卡绑定
     */
    public ResponseCode UnbindAccount(String token,String channelSeq);
    /**
     * 已签约银行查询
     * @param token
     * @return
     */
    public ResponseCode queryBankNo(String token);
    /**
     * 5.25	支付项目信息登记接口
     * @return
     */
	public String projecteRegist(ProjecteRegistRequestBody requestBody);
	 /**
     * 	支付成功callback
     * @return
     */
	public void callBack(User user, String transId, String transType);
	// 保存南粤银行回调douins 信息
	public int insertNYCallbackInfo(CallBackRequest callbackInfo);
	 /**
     * 	查询支付状态
     * @return
     */
	public String queryPayResult(String transNo);
	/**
	 * 关联银行的虚拟帐号管理
	 * 设置支付密码、修改支付密码、重置支付密码
	 * 充值、提现
	 * @return
	 */
	public ResponseTransVo<BankChangeResponseVo> AccoutManagerWithBank(BankChangeRequest trans);
	

	/**
	 *  返回银行数据字典
	 * @param bankDict
	 * @return
	 */
	public BankDict getBankDict(BankDict bankDict);
}
