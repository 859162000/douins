/**
 * 
 */
package com.douins.bank.domain.model.gfbc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: TPResponse 
* @Description: GF 充值响应报文模型
* @author G. F. 
* @date 2016年1月30日 下午1:11:05 
*  
*/
@XStreamAlias("wdres")
public class TPResponse extends AbstractResposeGF {
//    private String transId;     // 交易流水号 Message 的 id 属性
//    private String id;              // 银行返回的交易标帜符
    @XStreamAlias("version")
    private  String version;//版本号
    @XStreamAlias("instid")
    private String instId;//机构标识
    @XStreamAlias("certid")
    private String certId;//数字证书标识
//    @XStreamAlias("errorcode")
//    private String errorCode;//错误代码
//    @XStreamAlias("errormessage")
//    private String errorMessage;//错误􏰁述
    @XStreamAlias("channelseq")
    private String channelSeq;
    @XStreamAlias("dealres")
    private String DealRes;//处理结果  0:成功1:失败 2:处理中
    private String extension;
    @XStreamAlias("transtime")
    private String transTime;
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.gfbc.AbstractResposeGF#setResponseId(java.lang.String)
     */
    @Override
    public void setResponseId(String id) {
        // TODO Auto-generated method stub
        this.id = id;
    }

    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.gfbc.AbstractResposeGF#setTransId(java.lang.String)
     */
    @Override
    public void setTransId(String id) {
        // TODO Auto-generated method stub
        this.transId = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }
    
    public String getDealRes() {
        return DealRes;
    }

    public void setDealRes(String dealRes) {
        DealRes = dealRes;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTransId() {
        return transId;
    }

    public String getChannelSeq() {
        return channelSeq;
    }

    public void setChannelSeq(String channelSeq) {
        this.channelSeq = channelSeq;
    }

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
    
}
