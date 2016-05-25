/**
 * 
 */
package com.douins.agency.service.douins.service;

/** 
* @ClassName: InsureServiceIFC 
* @Description: 保险相关业务的接口，该类定义所有对接的保险公司通用基本业务
*               每个保险公司对接的业务（service）都应实现该接口
* @author G. F. 
* @date 2015年12月29日 上午11:15:56 
*  
*/
public interface InsureServiceIFC {
    /**
     * 核保
     * @param data
     * @return
     */
    public String doUW(String data);
    /**
     * 承保
     * @param data
     * @return
     */
    public String doInsure(String data);
    /**
     * 退保
     * @param data
     * @return
     */
    public String doWithdraw(String data);
    /**
     * 对账
     * @param data
     * @return
     */
    public String doBalance(String data);
}
