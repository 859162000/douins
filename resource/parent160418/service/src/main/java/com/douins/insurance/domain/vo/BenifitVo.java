/**
 * 
 */
package com.douins.insurance.domain.vo;

import com.douins.insurance.domain.model.PolicyBenifit;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: BenifitVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月15日 上午11:31:03 
*  
*/
public class BenifitVo {
    @XStreamAlias("IsLegal")
    private String isLegal = "1";                 // 是否为法定继承人
    @XStreamAlias("Benefit")
    private PolicyBenifit benifit;

    public String getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(String isLegal) {
        this.isLegal = isLegal;
    }

    public PolicyBenifit getBenifit() {
        return benifit;
    }

    public void setBenifit(PolicyBenifit benifit) {
        this.benifit = benifit;
    }
}
