/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.vo;

import java.util.List;

import com.douins.agency.service.channel.qunar.domain.model.GroupInfo;
import com.douins.agency.service.channel.qunar.domain.model.Header;
import com.douins.agency.service.channel.qunar.domain.model.PolicyInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: WithdrawReqVo 
* @Description: 退保请求数据结构
* @author G. F. 
* @date 2016年1月7日 下午5:51:11 
*  
*/
public class WithdrawReqVo {
    
    private Header header;
    @XStreamAlias("groupinfo")
    private GroupInfo groupInfo;
   @XStreamAlias("paramparamlist")
    private List<PolicyInfo> paramparamList;

   public WithdrawReqVo(Header header, GroupInfo groupInfo, List<PolicyInfo> policyInfos){
       this.header = header;
       this.groupInfo = groupInfo;
       this.paramparamList = policyInfos;
   }
   
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public GroupInfo getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        this.groupInfo = groupInfo;
    }

    public List<PolicyInfo> getParamparamList() {
        return paramparamList;
    }

    public void setParamparamList(List<PolicyInfo> paramparamList) {
        this.paramparamList = paramparamList;
    }
}
