/**
 * 
 */
package com.douins.bank.domain.model.gfbc;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: BQResponse 
* @Description: GF 账户余额查询请求应答数据模型
* @author G. F. 
* @date 2016年1月30日 下午3:55:55 
*  
*/
@XStreamAlias("bqres")
public class BQResponse extends AbstractResposeGF {

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
    
    private  String version;//版本号
    @XStreamAlias("instid")
    private String instId;//机构标识
    @XStreamAlias("certid")
    private String certId;//数字证书标识
    @XStreamAlias("vaccountno")
    private String vAccountNo;  // 电子账户
    @XStreamAlias("vaccamt")
    private String vAccAmt;     // 电子账户可用余额
    @XStreamAlias("vaccfreezeamt")
    private String vAccFreezeAmt;       // 电子账户冻结余额
    @XStreamAlias("vacctotalamt")
    private String vAccTotalAmt;        // 总余额 ＝ 可用余额 ＋ 冻结余额
    private String extension;               // 预留的扩展字段
    
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

    public String getvAccountNo() {
        return vAccountNo;
    }

    public void setvAccountNo(String vAccountNo) {
        this.vAccountNo = vAccountNo;
    }

    public String getvAccAmt() {
        return vAccAmt;
    }

    public void setvAccAmt(String vAccAmt) {
        this.vAccAmt = vAccAmt;
    }

    public String getvAccFreezeAmt() {
        return vAccFreezeAmt;
    }

    public void setvAccFreezeAmt(String vAccFreezeAmt) {
        this.vAccFreezeAmt = vAccFreezeAmt;
    }

    public String getvAccTotalAmt() {
        return vAccTotalAmt;
    }

    public void setvAccTotalAmt(String vAccTotalAmt) {
        this.vAccTotalAmt = vAccTotalAmt;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
