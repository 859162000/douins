/**
 * 
 */
package com.douins.agency.service.douins.service;

/** 
* @ClassName: ChannelServiceIFC 
* @Description: 销售渠道对应的业务接口，所有非银行渠道的业务实现都要实现该接口
* @author G. F. 
* @date 2015年12月29日 上午11:35:09 
*  
*/
public interface ChannelServiceIFC {
    /**
     * 核保
     * @param data
     * @return
     */
    public String doUW(String data) throws Exception;
    /**
     * 承保
     * @param data
     * @return
     */
    public String doInsure(String data) throws Exception;
    /**
     * 退保
     * @param data
     * @return
     */
    public String doWithdraw(String data) throws Exception;
    /**
     * 对账
     * @param data
     * @return
     */
    public String doBalance(String data);
    /**
     * 查询
     * @param data
     * @return
     */
    public String doQuery(String data);
    /**
     * 退保查询
     * @param data
     * @return
     */
	public String doQueryWD(String data);
}
