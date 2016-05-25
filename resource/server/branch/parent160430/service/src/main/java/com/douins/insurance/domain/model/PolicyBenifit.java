/**
 * 
 */
package com.douins.insurance.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/** 
* @ClassName: PolicyBenifit 
* @Description: 保单受益人
* @author G. F. 
* @date 2015年12月15日 上午10:24:02 
*  
*/
public class PolicyBenifit {
    @XStreamAlias("Name")
    private String name;
    @XStreamAlias("Gender")
    private String gender;
    @XStreamAlias("Birthday")
    private String birthday;
    @XStreamAlias("CertiType")
    private String certiType;
    @XStreamAlias("CertiCode")
    private String certiCode;
    @XStreamAlias("Designation")
    private String designation;         // 与保险人的关系
    @XStreamAlias("ShareOrder")
    private String order;           // 受益顺位
    @XStreamAlias("ShareRate")
    private String rate;            // 受益比例
    
    private String legalBenifit = "1";          // 1--法定受益人；2-- 指定受益人
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getCertiType() {
        return certiType;
    }
    public void setCertiType(String certiType) {
        this.certiType = certiType;
    }
    public String getCertiCode() {
        return certiCode;
    }
    public void setCertiCode(String certiCode) {
        this.certiCode = certiCode;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public String getOrder() {
        return order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getLegalBenifit() {
        return legalBenifit;
    }
    public void setLegalBenifit(String legalBenifit) {
        this.legalBenifit = legalBenifit;
    }
 
}
