/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/** 
* @ClassName: NYTransRequest 
* @Description: 向南粤银行发送交易请求的xml 通用数据模型
* @author G. F. 
* @date 2015年12月5日 下午11:55:12 
*  
*/
public abstract class NYTransRequest {
    @XStreamAsAttribute
    protected  final String package_type = "xml";
    @XStreamAlias("SYS_HEAD")
    private SystermHead systermHead;
    @XStreamAlias("APP_HEAD")
    private AppHead appHead;
    
    public String getPackage_type() {
        return package_type;
    }
    public SystermHead getSystermHead() {
        return systermHead;
    }
    public void setSystermHead(SystermHead systermHead) {
        this.systermHead = systermHead;
    }
    public AppHead getAppHead() {
        return appHead;
    }
    public void setAppHead(AppHead appHead) {
        this.appHead = appHead;
    }
    
    public abstract <T>  void  setBody(T body);
    
//    public static void main(String[] args){
//        NYTransRequest request = new NYTransRequest();
//        SystermHead systermHead2 = new SystermHead();
//        request.setSystermHead(systermHead2);
//        request.getSystermHead().setScene("79");
//        request.setAppHead(new AppHead());
//        
//        XStream xStream = new XStream();
//        xStream.processAnnotations(NYTransRequest.class);
//        String rus = xStream.toXML(request);
//        System.out.println(rus);
//    }
}
