/**
 * 
 */
package com.douins.insurance.domain.vo;

import java.util.List;

import com.douins.insurance.domain.model.Insured;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: InsuredVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月15日 上午11:06:19 
*  
*/
public class InsuredVo {
    @XStreamAlias("Insured")
    private List<Insured2> insureds;
    
    public List<Insured2> getInsureds() {
        return insureds;
    }

    public void setInsureds(List<Insured2> insureds) {
        this.insureds = insureds;
    }

    public class Insured2{
        @XStreamAlias("CustomList")
        private Insured insured;

        public Insured getInsureds() {
            return insured;
        }

        public void setInsureds(Insured insured) {
            this.insured = insured;
        }
    }
}
