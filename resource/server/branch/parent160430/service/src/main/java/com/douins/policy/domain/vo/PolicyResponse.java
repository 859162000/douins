package com.douins.policy.domain.vo;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.douins.bank.domain.vo.NYGateWayUrlH5;
import com.douins.trans.domain.vo.ResponseTransVo;

public class PolicyResponse extends ResponseTransVo{
    private static final Logger logger = Logger.getLogger(PolicyResponse.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5599713129471857325L;
	private BigDecimal totalAmount;                        // 保险总资产
	private BigDecimal totalRevenue;                        // 保险总资产收益
	private BigDecimal totalYesterdayIncome;                //昨日总收益
	private String nyCallBackUrl;            // 
	private List<PolicyResponseVo> policyList;
	    
	public List<PolicyResponseVo> getPolicyList() {
		return policyList;
	}
	public void setPolicyList(List<PolicyResponseVo> policyList) {
		this.policyList = policyList;
		this.totalAmount = calTotalAmount();
		this.totalRevenue = caltotalRevenue();
		this.totalYesterdayIncome = TotalYesterdayIncome();
		
		
	}
   
	public String getNyCallBackUrl() {
		return nyCallBackUrl;
	}
	public void setNyCallBackUrl(String nyCallBackUrl) {
		this.nyCallBackUrl = nyCallBackUrl;
	}
	public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public BigDecimal getTotalYesterdayIncome() {
		return totalYesterdayIncome;
	}
	
	
	/**
     * 计算所有保单的总本金
     * @return
     */
    private BigDecimal calTotalAmount(){
        BigDecimal total = new BigDecimal(0.00);
        if(policyList != null){
            for(PolicyResponseVo vo: policyList){
                total = total.add(vo.getTotalPrem());//总保费
            }
        }
        
        return total;
    }
    private BigDecimal caltotalRevenue() {
    	BigDecimal total = new BigDecimal(0.00);
    	if(policyList != null){
    		for(PolicyResponseVo vo: policyList){
    			// total = total.add(vo.getTotalRevenue()==null?new BigDecimal(0):vo.getTotalRevenue()); //totalRevenue 预计总收益  sty    
    			total = total.add(vo.getAccuIncome()==null?new BigDecimal(0):vo.getAccuIncome());//累计总收益 对应数据库中 T_policy_master中的 ACCUMULATED_INCOME （累计收益）
    			
    		}
    	}
    	
    	return total;
    }

    private BigDecimal TotalYesterdayIncome() {
    	BigDecimal total = new BigDecimal(0.00);
    	if(policyList != null){
    		for(PolicyResponseVo vo: policyList){
    			total = total.add(vo.getYesDayEarn()==null?new BigDecimal(0):vo.getYesDayEarn());//昨日收益
    		}
    	}
    	
    	return total;
    }

}

