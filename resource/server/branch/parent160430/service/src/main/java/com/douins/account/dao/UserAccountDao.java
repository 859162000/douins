/**
 * 
 */
package com.douins.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.account.domain.model.UserAccount;
import com.douins.common.persistence.annotation.MyBatisMapper;

/** 
* @ClassName: UserAccountDao 
* @Description: 用户 电子账户 DAO
* @author G. F. 
* @date 2015年10月20日 下午2:26:00 
*  
*/
@MyBatisMapper
public interface UserAccountDao  {

    public List<UserAccount> getList(UserAccount user);
    
    public int getList_Count(UserAccount user);
    
    public void add(UserAccount user);
    
    public void delete(@Param("userAccountId")String userAccountId);
    
    public void update(UserAccount user);
    
    public UserAccount findByUserId(@Param("userAccountId")String userAccountId);

	public UserAccount findUserAccountByToken(@Param("token") String token);//通过token查询用户电子账户信息

	public int updateCreditCardByAccountId(UserAccount userAccount);

	public int updateUserAccountBindCardByFlowNo(@Param("bindCardFlag") String bindCardFlag,@Param("bindFlowNo") String bindFlowNo);

	public int updateUserAccountUnbindCardByUnbindFlowNo(@Param("unbindflowNo") String unbindflowNo);//通过解绑流水号，解除卡号绑定
	
	public String getCardByUserId(@Param("userId") String userId);
}
