/**
 * 
 */
package com.douins.agency.service.insurance.ccic.domain.vo;

import java.util.List;

import com.douins.agency.service.insurance.ccic.domain.model.Applicant;
import com.douins.agency.service.insurance.ccic.domain.model.ChannelInfo;
import com.douins.agency.service.insurance.ccic.domain.model.Insured;
import com.douins.agency.service.insurance.ccic.domain.model.ItemKind;
import com.douins.agency.service.insurance.ccic.domain.model.UWmain;

/** 
* @ClassName: InsUWStructVo 
* @Description: CCIC 核保数据对象
* @author G. F. 
* @date 2015年12月31日 下午1:14:46 
*  
*/
public class InsUWStructVo {
    private ChannelInfo channel;
    private UWmain uwMain;
    private List<ItemKind> itemKindList;
    private Applicant applicant;
    private List<Insured> insureds;
    
    public ChannelInfo getChannel() {
        return channel;
    }
    public void setChannel(ChannelInfo channel) {
        this.channel = channel;
    }
    public UWmain getUwMain() {
        return uwMain;
    }
    public void setUwMain(UWmain uwMain) {
        this.uwMain = uwMain;
    }
    public List<ItemKind> getItemKindList() {
        return itemKindList;
    }
    public void setItemKindList(List<ItemKind> itemKindList) {
        this.itemKindList = itemKindList;
    }
    public Applicant getApplicant() {
        return applicant;
    }
    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
    public List<Insured> getInsureds() {
        return insureds;
    }
    public void setInsureds(List<Insured> insureds) {
        this.insureds = insureds;
    }
}
