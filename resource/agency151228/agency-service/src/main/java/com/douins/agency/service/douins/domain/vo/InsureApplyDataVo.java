/**
 * 
 */
package com.douins.agency.service.douins.domain.vo;

import com.douins.agency.service.douins.domain.model.InsureRequestFeedback;

/** 
* @ClassName: InsureApplyVo 
* @Description: CCIC 承保需要的数据
* @author G. F. 
* @date 2016年1月2日 上午10:06:14 
*  
*/
public class InsureApplyDataVo {
    private InsureRequestFeedback back;

    public InsureApplyDataVo(InsureRequestFeedback fb){
        this.back = fb;
    }
    
    public String getProductCode() {
        return back.getProductCode();
    }

    public String getTradeNo() {
        return back.getTradeNo();
    }

    public String getProposalNo() {
        return back.getProposalNo();
    }

    public String getPrintNo() {
        return back.getPrintNo();
    }

    public String getApplySeq() {
        return back.getApplySeq();
    }
}
