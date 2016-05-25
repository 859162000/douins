/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: PolicyInfo 
* @Description: 保单信息
* @author G. F. 
* @date 2016年1月7日 下午5:49:28 
*  
*/
public class PolicyInfo {
    @XStreamAlias("policyno")
    private String policyNo;
    
    private String tradeNo;

    public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }
}
