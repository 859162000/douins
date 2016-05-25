/**
 * 
 */
package com.douins.common.util;

import com.douins.account.domain.vo.UserPayAccountRequest;
import com.douins.trans.domain.vo.RequestTransVo;

/** 
* @ClassName: RequestTemplate 
* @Description: 通用的请求交易模版类
* @author G. F. 
* @date 2015年11月30日 上午10:37:52 
*  
*/
public class RequestTemplate<T> extends RequestTransVo{
    private static final long serialVersionUID = 9127949427356039860L;
    
    private Class<T> clazz;
    private T target;
    
    public RequestTemplate(Class<T> clazz2){
        this.clazz = clazz2;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    
    public T getTarget() {
        return target;
    }

    public void setTarget(T target) {
        this.target = target;
    }

    public static void main (String[] args) {
        String data = "{\"accessToken\":\"a7f519a8dd60410b836ccdb654865223\",\"payAccountVo\":{\"userId\":\"21cae9846fe54b409924d85b5cd173e1\"},\"requestTrans\":{\"transType\":\"114\",\"transChannel\":\"02\",\"transNo\":\"171274755754818\",\"transId\":\"171274755791061\",\"transTime\":\"2015-11-30 11:30:04\",\"clientType\":\"1\"}}";
        RequestTemplate<UserPayAccountRequest> pRequestTemplate = new RequestTemplate<>(UserPayAccountRequest.class);
        //pRequestTemplate = JsonOnlineUtils.readJson2Entity(data, pRequestTemplate);
        
        System.out.println(pRequestTemplate.getClazz());
        System.out.println(pRequestTemplate.getClass());
    }
}
