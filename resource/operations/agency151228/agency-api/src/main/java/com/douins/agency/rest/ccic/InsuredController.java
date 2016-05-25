/**
 * 
 */
package com.douins.agency.rest.ccic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.agency.annotation.IpAuth;
import com.douins.agency.rest.BaseController;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.common.util.TemplateLoader;
import com.douins.agency.service.douins.domain.enums.DouinsException;
import com.douins.agency.service.douins.service.ChannelServiceIFC;
import com.douins.agency.service.douins.service.InsureServiceIFC;
import com.douins.agency.service.douins.service.impl.IpAuthority;
import com.douins.agency.service.douins.service.impl.StaticTemplate;

/** 
* @ClassName: InsuredController 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月3日 下午3:59:28 
*  
*/
@Controller
@RequestMapping("")
public class InsuredController extends BaseController{
    private Logger logger = Logger.getLogger(InsuredController.class);
    @Resource
    private TemplateLoader templateLoader;
    
    @Resource(name = Constants.CCIC_SERVICE)
    private InsureServiceIFC insService;
    @Resource(name = Constants.QUNAR_SERVICE)
    private ChannelServiceIFC chnlService;
    
    @Resource(name = Constants.QUNARFN_SERVICE)
    private ChannelServiceIFC chnlServiceQNF;
    
    private String chnlFlag = "<partneridentifier>"; //lianlife header特殊标识
    
    /**
     * 核保
     * @param request
     * @return
     */

    @IpAuth(ipAuth = true)
    @ResponseBody
    @RequestMapping("/uw")
    public String doUW(HttpServletRequest request){
        IpAuthority.getClientIpAddress(request);
        String reString;
        try {
        	String body = getAllRequstParams(request);
//            String data = getRequestConent(request);
//            logger.info("收到的数据= \n" + data);
//            body = getRequestConent(request);
            if(body.contains("content")){
                body = request.getParameter("content");
            }
            logger.info("收到的数据= \n" + body);
            
            String headXml = getRequestHeader(body);
            int result = headXml.indexOf(chnlFlag);
            if(result >= 0){
            	reString = chnlServiceQNF.doUW(body); // 请求lianlife
            }else{
            	reString = chnlService.doUW(body);  //请求 ccic
            }
           
        } catch (Exception e) {	
           logger.error("读取参数错误", e);
           reString = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.UW_DATA_ERROR));
        }
        return reString;
    }
   
    /**
     * 承保
     * @param request
     * @return
     */
    @IpAuth(ipAuth = true)
    @ResponseBody
    @RequestMapping("/insure")
    public String doInsure(HttpServletRequest request){
    	String reString;
        try {
            String body = getAllRequstParams(request);
            String data = getRequestConent(request);
            logger.info("收到的数据 ＝ "+ body);
//            if(body.contains("title")){
//                data = body.substring(16);
//            }else{
//                data = body;
//            }
            if(body.contains("content")){
                data = request.getParameter("content");
            }
//            body = getRequestConent(request);
//            logger.info("参数2 ＝ "+ body);
            String headXml = getRequestHeader(body);
            int result = headXml.indexOf(chnlFlag);
            if(result >= 0){
            	if(data != null && !"".equals(data)){
            		reString = chnlServiceQNF.doInsure(data);
            	}else{
            		reString = chnlServiceQNF.doInsure(body);
            	}
            }else{
            	reString = chnlService.doInsure(data);
            }
        } catch (Exception e) {
           logger.error("读取参数错误", e);
           reString = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.INSURE_DATA_ERROR));
        }
        
        return reString;
    }
    
    /**
     * 退保
     * @param request
     * @return
     */
    @IpAuth(ipAuth = true)
    @ResponseBody
    @RequestMapping("/withdraw")
    public String withdraw(HttpServletRequest request){
        String reString;
        try {
            String body = getAllRequstParams(request);
            String data = getRequestConent(request);
//            logger.info("data = "+ data);
            logger.info("收到的数据 ＝ "+ body);
//            if(body.contains("title")){
//                data = body.substring(16);
//            }else{
//                data = body;
//            }
            if(body.contains("content")){
                data = request.getParameter("content");
            }
            
            String headXml = getRequestHeader(body);
            int result = headXml.indexOf(chnlFlag);
            if(result >=0){
            	if(data != null && !"".equals(data)){
            		reString = chnlServiceQNF.doWithdraw(data);
            	}else{
            		reString = chnlServiceQNF.doWithdraw(body);
            	}
            }else{
                reString = chnlService.doWithdraw(data);
            }
        } catch (Exception e) {
           logger.error("读取参数错误", e);
           reString = StaticTemplate.getExceptionTemplate( templateLoader, DouinsException.getErrorMap(DouinsException.WITHDRAW_DATA_ERROR));
        }
        
        return reString;
    }
    
    /**
     *  对账
     * @param request
     * @return
     */
    @IpAuth(ipAuth = true)
    @ResponseBody
    @RequestMapping("/balance")
    public String balance(HttpServletRequest request){
        String reString;
        try {
            String body = getAllRequstParams(request);
            String data = body;//.substring(16);
//            logger.info("参数 ＝ "+ data);
//            body = getRequestConent(request);
           reString = chnlService.doBalance(data);
        } catch (Exception e) {
           logger.error("读取参数错误", e);
           reString = e.getMessage();
        }
        
        return reString;
    }
    
     /**
      * 查询
      * @author panrui
      * @param
      */
   @IpAuth(ipAuth = true)
    @ResponseBody
    @RequestMapping("/query")
    public String Query(HttpServletRequest request){
    	String reString;
        try {
            String body = getAllRequstParams(request);
            String data = body;
            reString = chnlServiceQNF.doQuery(data);
        } catch (Exception e) {
           logger.error("读取参数错误", e);
           reString = e.getMessage();
        }
    	return reString;
    }
}
