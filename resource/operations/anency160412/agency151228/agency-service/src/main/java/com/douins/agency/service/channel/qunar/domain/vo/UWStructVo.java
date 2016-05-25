/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.vo;

import java.util.List;

import com.douins.agency.service.channel.qunar.domain.model.GroupInfo;
import com.douins.agency.service.channel.qunar.domain.model.Header;

/** 
* @ClassName: UWStructVo 
* @Description: 核保数据对象
* @author G. F. 
* @date 2015年12月30日 下午4:59:01 
*  
*/
public class UWStructVo {
    private Header header;
    private GroupInfo groupInfo;
    private List<InsuredInfoVo> insuredInfoVos;
    
    public UWStructVo(Header header, GroupInfo groupInfo, List<InsuredInfoVo> insureds){
        this.header = header;
        this.groupInfo = groupInfo;
        this.insuredInfoVos = insureds;
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
    public List<InsuredInfoVo> getInsuredInfoVos() {
        return insuredInfoVos;
    }
    public void setInsuredInfoVos(List<InsuredInfoVo> insuredInfoVos) {
        this.insuredInfoVos = insuredInfoVos;
    }
    
}
