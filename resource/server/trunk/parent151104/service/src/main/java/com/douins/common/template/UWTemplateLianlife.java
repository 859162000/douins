package com.douins.common.template;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sun.istack.logging.Logger;

import freemarker.template.Template;

public class UWTemplateLianlife {
    private static final Logger  logger = Logger.getLogger(UWTemplateLianlife.class);
    private static String filePath = "classpath:template/uwLianlife.ftl";
    private static Template template;
    

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    @PostConstruct
    private void Init(){
        try {
            
            template = freeMarkerConfigurer.getConfiguration().getTemplate("uwLianlife.ftl");
        } catch (IOException e) {
            logger.info("load uw template error.", e);
        }
    }

    public static Template getTemplate() {
        return template;
    }

    public static void setTemplate(Template template) {
        UWTemplateLianlife.template = template;
    }
}
