/**
 * 
 */
package com.douins.agency.service.common;

/** 
* @ClassName: Constants 
* @Description: 公用常量定义
* @author G. F. 
* @date 2015年12月28日 上午8:56:31 
*  
*/
public class Constants {
    // ************** define business javabean name **************
    public static final String CCIC_SERVICE = "ccicService";            // 大地保险公司的业务对象名
    public static final String QUNAR_SERVICE = "qunarService";      // 去哪儿的业务对象名
    public static final String QUNARFN_SERVICE = "qunarfnService";  // 去哪儿理财业务对象名
    
    // ************** define adater of douins ********************
    public static final String QUNAR_CCIC_ADAPTER = "qcAdapter";         // qunar - ccic 适配器名称
    public static final String QUNAR_CCIC_ADAPTER_MULTI_THREAD = "qcAdapterMultiTread";
    public static final String QUNARFN_LIANLIFE_ADAPTER = "qflAdapter";  // qunarfn - lian 适配器名称
    // ************** define the channel and insure co. ************
    public static final String QUNAR_CCIC = "0101";         // qunar 和 CCIC 的对接通道代码，qunar - 01 & ccic - 01
    
    // ************** define trans. type code ********************
    public static final String UW_TRANS = "01";                                 // 核保交易
    public static final String APPLY_TRANS = "02";                           // 承保申请
    public static final String WITHDRAW_TRANS = "03";                 // 退保交易确认
    public static final String WITHDRAW_REQ = "04";                     // 退保交易申请
    
    // ************** define database service name ***************
    public static final String CCIC_DATA_SERVICE = "ccicDataService";
    
    // ************** define your constants below ****************
}
