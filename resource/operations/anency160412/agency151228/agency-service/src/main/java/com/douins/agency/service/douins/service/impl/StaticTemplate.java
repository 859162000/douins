/**
 * 
 */
package com.douins.agency.service.douins.service.impl;

import java.util.Map;

import com.douins.agency.service.common.util.TemplateLoader;

/** 
* @ClassName: StaticTemplate 
* @Description: Douins 外送的静态模版
* @author G. F. 
* @date 2016年1月6日 上午9:40:33 
*  
*/
public class StaticTemplate {

    /**
     * 通用异常模版
     * @param code
     * @param msg
     * @return
     */
    public static  String getExceptionTemplate(TemplateLoader  templateLoader, Map<String, String> msg){
        String temp = templateLoader.processTemplate("/exception/exception.ftl", msg);
        return temp;
    }
}
