/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.douins.common.params.WebUseConstant;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: AppHead 
* @Description: xml 中的 app head
* @author G. F. 
* @date 2015年12月5日 下午11:49:45 
*  
*/
public class AppHead {
    @XStreamAlias("CHANNEL_CODE")
    private final  String channelCode = WebUseConstant.CHANL_NO;

    public String getChannelCode() {
        return channelCode;
    }
}
