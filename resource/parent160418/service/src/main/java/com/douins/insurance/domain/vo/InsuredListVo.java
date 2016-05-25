/**
 * 
 */
package com.douins.insurance.domain.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: InsuredListVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月15日 上午11:29:52 
*  
*/
public class InsuredListVo {
    @XStreamAlias("InsuredList")
    private InsuredVo insuredVo;

    public InsuredVo getInsuredVo() {
        return insuredVo;
    }

    public void setInsuredVo(InsuredVo insuredVo) {
        this.insuredVo = insuredVo;
    }
}
