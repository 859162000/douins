/**
 * 
 */
package com.douins.agency.service.common.util;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/** 
* @ClassName: TemplateLoader 
* @Description: freemark 模版加载器
* @author G. F. 
* @date 2015年12月28日 下午4:52:11 
*  
*/
@Component
public class TemplateLoader {
    private static final Logger logger = Logger.getLogger(TemplateLoader.class);
    
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    public Template getTemplate(String filePath){
        Template template = null;
        try {
            template = freeMarkerConfigurer.getConfiguration().getTemplate(filePath);
        } catch (IOException e) {
           logger.error("load template error. ", e);
        }
        return template;
    }
    
    /**
     * 将模版以字符串形式返回
     * @param filePath
     * @param root
     * @return
     */
    public String processTemplate(String filePath, Map<String, Object> root){
        String result = null;
        Template template = getTemplate(filePath);
        if(template != null){
            try {
                result = FreeMarkerTemplateUtils.processTemplateIntoString(template, root);
            } catch (Exception e) {
                logger.error("process template error.", e);
            }
        }
        return result;
    }
    
    /**
     * 将模版以字符串形式返回
     * @param filePath
     * @param root
     * @return
     */
    public String processTemplate(String filePath, Object root){
        String result = null;
        Template template = getTemplate(filePath);
        if(template != null){
            try {
                result = FreeMarkerTemplateUtils.processTemplateIntoString(template, root);
            } catch (Exception e) {
                logger.error("process template error.", e);
            }
        }
        return result;
    }
}
