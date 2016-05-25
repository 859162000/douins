package com.douins.account.domain.model;

import com.mango.core.abstractclass.AbstractModel;
import com.mango.orm.KeyGenerator;

/**
* @ClassName: UserAccountOpenApply 
* @Description: 用户开户申请
* @author G. F. 
* @date 2015年10月20日 下午3:26:08 
*
 */
public class UserAccountOpenApply extends AbstractModel{
	private static final long serialVersionUID = 6103751504152200907L;
	
	private Long id;
	
	private String openApplyId;

    private String userId;

    private String accountId;

    private String status;

    private String virtualAccountNo;

    private String isvalid;

    public String getOpenApplyId() {
        return openApplyId;
    }

    public void setOpenApplyId(String openApplyId) {
        this.openApplyId = openApplyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVirtualAccountNo() {
        return virtualAccountNo;
    }

    public void setVirtualAccountNo(String virtualAccountNo) {
        this.virtualAccountNo = virtualAccountNo;
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid;
    }

	@Override
	public void initPrimaryKey() {
		this.setOpenApplyId(KeyGenerator.randomSeqNum());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}