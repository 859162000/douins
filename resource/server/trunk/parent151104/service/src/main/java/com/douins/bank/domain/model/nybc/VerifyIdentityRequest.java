/**
 * 
 */
package com.douins.bank.domain.model.nybc;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/** 
* @ClassName: VerifyIdentityRequest 
* @Description: 身份验证的请求体
* @author G. F. 
* @date 2015年12月6日 上午1:02:46 
*  
*/
@XStreamAlias("SDOROOT")
public class VerifyIdentityRequest extends NYTransRequest {
    @XStreamAlias("BODY")
    private VerifyIdentityRequestBody body;         // 请求参数体部分

    public VerifyIdentityRequestBody getBody() {
        return body;
    }

//    public void setBody(VerifyIdentityRequestBody body) {
//        this.body = body;
//    }
    
    /* (non-Javadoc)
     * @see com.douins.bank.domain.model.nybc.NYTransRequest#setBody(java.lang.Class)
     */
    @Override
    public <T> void setBody(T body) {
        this.body = (VerifyIdentityRequestBody)body;
    }
    
    
    public static void main(String[] args){
        NYTransRequest request = new VerifyIdentityRequest();
        SystermHead systermHead2 = new SystermHead();
        request.setSystermHead(systermHead2);
        request.getSystermHead().setScene("79");
        request.setAppHead(new AppHead());
        VerifyIdentityRequestBody body2 = new VerifyIdentityRequestBody();
        body2.setCertCode("02");
        request.setBody(body2);
        
        XStream xStream = new XStream();
        xStream.processAnnotations(VerifyIdentityRequest.class);
        String res = xStream.toXML(request);
        System.out.println(res);
    }


}
