/**
 * 
 */
package com.douins.common.util;

import org.apache.log4j.Logger;

import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: ResponseUtils 
* @Description: Http 响应工具类
* @author G. F. 
* @date 2015年11月18日 下午5:48:39 
*  
*/
public class ResponseUtils {
    private static final Logger logger = Logger.getLogger(ResponseUtils.class);

    public static String DEFAULT_ENCODING = "UTF-8";
    
    public static <T> String getResponse(ResponseTransVo<?> responseTransVo,ResponseCode responseCode, String transId){
        ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(), responseCode.getName(), transId);
        responseTransVo.setResponseTrans(responseTrans);
        String result = JsonOnlineUtils.bean2json(responseTransVo);
        logger.info(result);
        
        return result;
    }
}
