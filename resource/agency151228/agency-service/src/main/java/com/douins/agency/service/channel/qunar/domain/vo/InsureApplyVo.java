/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.vo;

import java.util.List;

import com.douins.agency.service.channel.qunar.domain.model.Header;
import com.douins.agency.service.channel.qunar.domain.model.PolicyConfirm;

/** 
* @ClassName: InsureApplyVo 
* @Description: 承保请求数据
* @author G. F. 
* @date 2015年12月30日 下午11:19:34 
*  
*/
public class InsureApplyVo {
    private Header header;
    private List<PolicyConfirm> confirms;
    
    public InsureApplyVo(Header header, List<PolicyConfirm> confirms){
        this.header = header;
        this.confirms = confirms;
    }
    
    public Header getHeader() {
        return header;
    }
    public void setHeader(Header header) {
        this.header = header;
    }
    public List<PolicyConfirm> getConfirm() {
        return confirms;
    }
    public void setConfirm(List<PolicyConfirm> confirms) {
        this.confirms = confirms;
    }
}
