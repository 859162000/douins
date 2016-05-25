/**
 * 
 */
package com.douins.common.template;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sun.istack.logging.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;

/** 
* @ClassName: ProposalTemplate 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月16日 下午5:24:27 
*  
*/
@Component
public class ProposalTemplate {
    private static final Logger  logger = Logger.getLogger(ProposalTemplate.class);
    private static Template template;

    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    @PostConstruct
    private void Init(){
        try {
            
            template = freeMarkerConfigurer.getConfiguration().getTemplate("insured.ftl");
        } catch (IOException e) {
           logger.info("load insured template error.", e);
        }
    }

    public static Template getTemplate() {
        return template;
    }

    public static void setTemplate(Template template) {
        ProposalTemplate.template = template;
    }

}
