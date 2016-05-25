package com.douins.bank.domain.model.gfbc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 消费／撤单接口返回
 * @author hou
 */
@XStreamAlias("secres")
public class SECResponse extends AbstractResposeGF{
	@XStreamAlias("version")
	private String version;//版本号
	@XStreamAlias("instid")
	private String instId;//机构标识
	@XStreamAlias("certid")
	private String certId;//数字证书标识
	@XStreamAlias("dealres")
	private String DealRes;//处理结果  0:成功1:失败 2:处理中
	@XStreamAlias("channelseq")
	private String channelSeq;
	
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
	
	@Override
	public void setResponseId(String id) {
		this.id = id;
	}
	@Override
	public void setTransId(String id) {
		this.transId = id;
	}
	
}
