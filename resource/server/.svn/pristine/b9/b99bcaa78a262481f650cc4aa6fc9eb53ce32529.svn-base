/**
 * 
 */
package com.douins.common.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.User;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.google.common.collect.Maps;
import com.google.protobuf.UnknownFieldSet.Field;

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
