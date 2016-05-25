/**
 * 
 */
package com.douins.insurance.domain.vo;

import java.util.List;

import com.douins.insurance.domain.model.HoderInfo;
import com.douins.insurance.domain.model.PolicyHolder;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: HolderListVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月15日 上午10:59:52 
*  
*/
public class HolderVo {
    @XStreamAlias("Holder")
    private HolderList holderList;
    
    public HolderList getHolderList() {
        return holderList;
    }

    public void setHolderList(HolderList holderList) {
        this.holderList = holderList;
    }

    public class HolderList{
        @XStreamAlias("CustomList")
        private List<PolicyHolder> holders;

        public List<PolicyHolder> getHolders() {
            return holders;
        }

        public void setHolders(List<PolicyHolder> holders) {
            this.holders = holders;
        }
        
    }
}
