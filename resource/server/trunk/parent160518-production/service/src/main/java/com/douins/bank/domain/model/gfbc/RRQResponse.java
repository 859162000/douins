package com.douins.bank.domain.model.gfbc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("rrqres")
public class RRQResponse extends AbstractResposeGF{
//	@XStreamAsAttribute
//	private String id;
	@XStreamAlias("version")
	private  String version;//版本号
	@XStreamAlias("instid")
	private String instId;//机构标识
	@XStreamAlias("certid")
	private String certId;//数字证书标识
//	@XStreamAlias("errorcode")
//	private String errorCode;//错误代码
//	@XStreamAlias("errormessage")
//	private String errorMessage;//错误􏰁述
	@XStreamAlias("dealres")
	private String DealRes;//处理结果  0:成功1:失败 2:处理中
	@XStreamAlias("accountno")
	private String accountNo;//账户
	@XStreamAlias("certtype")
	private String certType;//证件类型
	@XStreamAlias("certno")
	private String certNo;//证件号	
	@XStreamAlias("cusname")
	private String cusName;//用户姓名
	@XStreamAlias("phoneno")
	private String phoneNo;//手机号码
	@XStreamAlias("cardno")
	private String cardNo;//付款卡号
	@XStreamAlias("protocolcode")
	private String protocolCode;//协议号
	@XStreamAlias("obankcode")
	private String oBankCode;//开户银行代码
	@XStreamAlias("obankname")
	private String oBankName;//开户行名称
	
//	private String transId;//本地系统流水交易号
	
	private String extension;//扩展信息,预留字段
	
	public String getVersion() {
		return version;
	}
	public String getInstId() {
		return instId;
	}
	public String getCertId() {
		return certId;
	}
	public String getDealRes() {
		return DealRes;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public String getCertType() {
		return certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public String getCusName() {
		return cusName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public String getProtocolCode() {
		return protocolCode;
	}
	public String getoBankCode() {
		return oBankCode;
	}
	public String getoBankName() {
		return oBankName;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public void setCertId(String certId) {
		this.certId = certId;
	}
	public void setDealRes(String dealRes) {
		DealRes = dealRes;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setProtocolCode(String protocolCode) {
		this.protocolCode = protocolCode;
	}
	public void setoBankCode(String oBankCode) {
		this.oBankCode = oBankCode;
	}
	public void setoBankName(String oBankName) {
		this.oBankName = oBankName;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.gfbc.AbstractResposeGF#setResponseId(java.lang.String)
     */
    @Override
    public void setResponseId(String id) {
        // TODO Auto-generated method stub
        this.id = id;
    }
	@Override
	public void setTransId(String id) {
		// TODO Auto-generated method stub
		this.transId = id;
	}
}
