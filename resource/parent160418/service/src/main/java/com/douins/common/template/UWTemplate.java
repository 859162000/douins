/**
 * 
 */
package com.douins.common.template;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sun.istack.logging.Logger;

import freemarker.template.Template;

/** 
* @ClassName: UWTemplate 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月15日 下午2:35:59 
*  
*/
@Component
public class UWTemplate {
    private static final Logger  logger = Logger.getLogger(UWTemplate.class);
    private static String filePath = "classpath:template/uwtemp.ftl";
    private static Template template;

    @Resource
    private  FreeMarkerConfigurer freeMarkerConfigurer;
    
    @PostConstruct
    private void Init(){
        try {
            
            template = freeMarkerConfigurer.getConfiguration().getTemplate("uwtemp.ftl");
//            File file = ResourceUtils.getFile(filePath); 
//            logger.info("模板位置＝ "+filePath);
//            Configuration cfg = new Configuration();
//            cfg.setDirectoryForTemplateLoading(file);
//            template = cfg.getTemplate("uwtemp.ftl");
        } catch (IOException e) {
            logger.info("load uw template error.", e);
        }
    }

    public static Template getTemplate() {
        return template;
    }
    public  Template getTemplateByName(String templeName) {
    	try {
			template = freeMarkerConfigurer.getConfiguration().getTemplate(templeName);
		} catch (IOException e) {
			 logger.info("load uw template error.", e);
		}
        return template;
    }
    public static void setTemplate(Template template) {
        UWTemplate.template = template;
    }
}
