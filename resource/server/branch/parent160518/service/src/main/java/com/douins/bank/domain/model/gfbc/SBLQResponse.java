package com.douins.bank.domain.model.gfbc;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 已签约银行查询接口返回
 * @author hou
 *
 */
@XStreamAlias("sblqres")
public class SBLQResponse extends AbstractResposeGF{
	@XStreamAlias("version")
	private String version;//版本号
	@XStreamAlias("instid")
	private String instId;//机构标识
	@XStreamAlias("certid")
	private String certId;//数字证书标识
	@XStreamAlias("currpage")
	private String currPage;//当前页
	@XStreamAlias("pagesize")
	private String pageSize;//每页记录数
	@XStreamAlias("totalnum")
	private String totalNum;//总记录数
	@XStreamAlias("curpagenum")
	private String curPageNum;//实际本页记录 数
	@XStreamAlias("isignedbankslist")
	private List<ItemVo> iSignedBanksList;//循环开始标签名称
	
	public String getVersion() {
		return version;
	}
	public String getInstId() {
		return instId;
	}
	public String getCertId() {
		return certId;
	}
	public String getCurrPage() {
		return currPage;
	}
	public String getPageSize() {
		return pageSize;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public String getCurPageNum() {
		return curPageNum;
	}
	public List<ItemVo> getiSignedBanksList() {
		return iSignedBanksList;
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
	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public void setCurPageNum(String curPageNum) {
		this.curPageNum = curPageNum;
	}
	public void setiSignedBanksList(List<ItemVo> iSignedBanksList) {
		this.iSignedBanksList = iSignedBanksList;
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
