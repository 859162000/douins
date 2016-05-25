/**
 * 
 */
package com.douins.bank.domain.model.gfbc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: AbstractResposeGF 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月30日 上午10:31:56 
*  
*/
public abstract class AbstractResposeGF {
    /**
     * 设置返回信息上下文的 id 字段
     * @param id
     */
    public abstract void setResponseId(String id);
    /**
     * 设置返回信息中的 Message id 字段
     * @param id
     */
    public abstract void setTransId(String id);
    
    protected String id;
    protected String transId;
	@XStreamAlias("errorcode")
	private String errorCode;//错误代码
	@XStreamAlias("errormessage")
	private String errorMessage;//错误􏰁信息
	@XStreamAlias("errordetail")
	private String errorDetail;//详细错误信息
	@XStreamAlias("vendorcode")
	private String vendorCode;//服务方代码
	
	public String getId() {
		return id;
	}
	public String getTransId() {
		return transId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorDetail() {
		return errorDetail;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	
}
