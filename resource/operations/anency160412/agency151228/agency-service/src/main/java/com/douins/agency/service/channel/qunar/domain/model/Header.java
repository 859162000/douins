/**
 * 
 */
package com.douins.agency.service.channel.qunar.domain.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: Header 
* @Description: qunar 请求数据的 header
* @author G. F. 
* @date 2015年12月30日 下午4:46:04 
*  
*/
@XStreamAlias("header")
public class Header {
    @XStreamAlias("tran_code")
    private String tran_Code;
    @XStreamAlias("documentid")
    private String documentId;
    @XStreamAlias("profilerequest")
    private String profileRequest;
    private String function;
    private String from;
    private String to;
    
    public String getTran_Code() {
        return tran_Code;
    }
    public void setTran_Code(String tran_Code) {
        this.tran_Code = tran_Code;
    }
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public String getProfileRequest() {
        return profileRequest;
    }
    public void setProfileRequest(String profileRequest) {
        this.profileRequest = profileRequest;
    }
    public String getFunction() {
        return function;
    }
    public void setFunction(String function) {
        this.function = function;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
}
