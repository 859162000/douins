/**
 * 
 */
package com.douins.agency.rest.ccic;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.agency.annotation.IpAuth;
import com.douins.agency.rest.BaseController;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.common.util.AESUtils;
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

    private AESUtils aESUtils = new AESUtils();
    
    private String chnlFlag = "<partneridentifier>"; //lianlife header特殊标识

    
    /**
     * 核保
     * @param request
     * @return
     */
    @IpAuth(ipAuth = true)
    @ResponseBody
    @RequestMapping("/uw")
    public String doUW(HttpServletRequest request,@RequestParam(value = "encrypt", required = false)boolean encrypt){
        IpAuthority.getClientIpAddress(request);
        
        String reString;
        try {
            String body = getAllRequstParams(request);
       	    logger.info("收到的数据body= \n" + body);

       	    String data = getRequestConent(request);
            logger.info("收到的数据data= \n" + data);
            
            String swear ="";
            
       	    if(encrypt){
       	      logger.info("检测到密文请求－－－－执行解密：");
       	    	data =  aESUtils.decrypt(data);
       	    	if(!data.equals("")){
       	    		logger.info("解密结果："+data);
       	    	}else{
       	    		logger.info("解密失败");
       	    	}
       	    }
            
            if(data == null || data.length() ==0){
            	swear = body;
            }else{
            	swear = data;
            }
            
            if(swear.contains("content")){
                swear = request.getParameter("content");
            }
            
            String headXml = getRequestHeader(swear);
            int result = headXml.indexOf(chnlFlag);
            if(result >= 0){

            	//核保请求，元转分 金额转换
            	//swear=amountConversion(swear,new String[]{"InsuranceCoverage","PeriodPremium","TotalPremium"},1);
            	reString = chnlServiceQNF.doUW(swear); // 请求lianlife
            	
            	//核保响应，分转元 金额转换
             	//reString=amountConversion(reString,new String[]{"TotalPremium"},2);

            }else{
            	reString = chnlService.doUW(swear);  //请求 ccic
            }
            
       	    if(encrypt){
       	    	logger.info("密文返回－－－－执行加密：");
       	    	String reStringTemp =  aESUtils.encrypt(reString);
       	    	if(!reStringTemp.equals("")){
       	    		reString = reStringTemp;
       	    		logger.info("加密完成："+reString);
       	    	}else{
       	    		logger.info("加密失败-还原报文"+reString);
       	    	}
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
    public String doInsure(HttpServletRequest request,@RequestParam(value = "encrypt", required = false)boolean encrypt){
    	String reString;
        try {        	
            String body = getAllRequstParams(request);
            logger.info("收到的数据 ＝ "+ body);  
            String data = getRequestConent(request);
        	logger.info("收到的数据data＝"+data);
        	
            String swear = "";
            
       	    if(encrypt){
         	      logger.info("检测到密文请求－－－－执行解密：");
         	    	data =  aESUtils.decrypt(data);
         	    	if(!data.equals("")){
         	    		logger.info("解密结果："+data);
         	    	}else{
         	    		logger.info("解密失败");
         	    	}
         	    }
             
            if(data == null || data.length() == 0){
            	swear = body;
            }else {
            	swear = data;
            }
            if(swear.contains("content")){
                swear = request.getParameter("content");
            }

            String headXml = getRequestHeader(swear);
            int result = headXml.indexOf(chnlFlag);
            if(result >= 0){
            	reString = chnlServiceQNF.doInsure(swear);
                logger.info("承保响应转换前：－－分转元－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reString +"\n=========================");
                //承保响应，分转元 金额转换
             	reString=amountConversion(reString,new String[]{"TotalPremium"},2);
             	logger.info("承保响应－－分转元－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reString +"\n=========================");
            }else{
            	reString = chnlService.doInsure(swear);
            }

     	    if(encrypt){
       	    	logger.info("密文返回－－－－执行加密：");
       	    	String reStringTemp =  aESUtils.encrypt(reString);
       	    	if(!reStringTemp.equals("")){
       	    		reString = reStringTemp;
       	    		logger.info("加密完成："+reString);
       	    	}else{
       	    		logger.info("加密失败-还原报文"+reString);
       	    	}
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
    public String withdraw(HttpServletRequest request,@RequestParam(value = "encrypt", required = false)boolean encrypt){
        String reString;
        try {       	
            String body = getAllRequstParams(request);
            logger.info("收到的数据 ＝ "+ body);
            String data = getRequestConent(request);
        	logger.info("收到的数据data＝"+data);
        	
            String swear = "";
            
       	    if(encrypt){
         	      logger.info("检测到密文请求－－－－执行解密：");
         	    	data =  aESUtils.decrypt(data);
         	    	if(!data.equals("")){
         	    		logger.info("解密结果："+data);
         	    	}else{
         	    		logger.info("解密失败");
         	    	}
         	    }
       	    
            if(data ==null || data.length() == 0){
            	swear = body;
            }else{
            	swear = data ;
            }
            
            if(swear .contains("content")){
                swear = request.getParameter("content");
            }
            
            String headXml = getRequestHeader(swear);
            int result = headXml.indexOf(chnlFlag);
            if(result >=0){
            	reString = chnlServiceQNF.doWithdraw(swear);
                logger.info("退保响应转换前：－－分转元－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reString +"\n=========================");
                //退保响应，分转元 金额转换
             	reString=amountConversion(reString,new String[]{"RefundAmount"},2);
             	logger.info("退保响应－－分转元－－利安：＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n"+reString +"\n=========================");
            }else{
            	reString = chnlService.doWithdraw(swear);
            }

     	    if(encrypt){
       	    	logger.info("密文返回－－－－执行加密：");
       	    	String reStringTemp =  aESUtils.encrypt(reString);
       	    	if(!reStringTemp.equals("")){
       	    		reString = reStringTemp;
       	    		logger.info("加密完成："+reString);
       	    	}else{
       	    		logger.info("加密失败-还原报文"+reString);
       	    	}
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
   
   /**
    * 退保查询
    * @author panrui
    * @param
    */
   @IpAuth(ipAuth = true)
   @ResponseBody
   @RequestMapping("/queryWD")
   public String queryWD(HttpServletRequest request,@RequestParam(value = "encrypt", required = false)boolean encrypt){
	   
	   String reString;
       try {
	           String body = getAllRequstParams(request);
	      	    logger.info("收到的数据body= \n" + body);
	      	   String data = getRequestConent(request);
	      	   if(data != null && data.length() > 0){
	      		   logger.info("收到的数据data= \n" + data);
	      	   }
	      	   
	           String swear ="";
	       	    if(encrypt){
	         	      logger.info("检测到密文请求－－－－执行解密：");
	         	    	data =  aESUtils.decrypt(data);
	         	    	if(!data.equals("")){
	         	    		logger.info("解密结果："+data);
	         	    	}else{
	         	    		logger.info("解密失败");
	         	    	}
	         	    }
	       	    
	           if(data == null || data.length() ==0){
	            	swear = body;
	           	 }else{
	            	swear = data;
	           }
	           
	           if(swear.contains("content")){
	               swear = request.getParameter("content");
	           }
	           
            reString = chnlServiceQNF.doQueryWD(swear);
           
    	    if(encrypt){
       	    	logger.info("密文返回－－－－执行加密：");
       	    	String reStringTemp =  aESUtils.encrypt(reString);
       	    	if(!reStringTemp.equals("")){
       	    		reString = reStringTemp;
       	    		logger.info("加密完成："+reString);
       	    	}else{
       	    		logger.info("加密失败-还原报文"+reString);
       	    	}
        	  }
         } catch (Exception e) {
           logger.error("读取参数错误", e);
           reString = e.getMessage();
        }
    	return reString;
   }
   
   /**
    * @param xml 待转换的XML 报文
    * @param match 需替换值的 节点名
    * @param type 类型 1:元转分 2:分转元
    * @return
    */
	private static String amountConversion(String xml, String[] match, int type) {

		String result = "";
		String resultXml = xml;
		String orgain = "";

		for (int i = 0; i < match.length; i++) {
			String partner=match[i];
			
			String regex = "<" + partner + ">([^<]+)</" + partner + ">";
			Matcher m = Pattern.compile(regex).matcher(resultXml);
			if (m.find()) {
				String price = m.group(1);
				orgain="<"+partner+">"+price+"</"+partner+">";
				BigDecimal b = new BigDecimal(price);// 待转换的金额

				// 元转分 multiply
				if (type == 1) {
					result = "<" + partner + ">" + b.multiply(new BigDecimal("100")).toString() + "</" + partner+ ">";
					// 分转元 divide 保留两位小数
				} else if (type == 2) {
					//result = "<" + partner + ">"+ b.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "</"+ partner + ">";
					// 去掉2位小数
					result = "<" + partner + ">"+ b.divide(new BigDecimal("100")).setScale(0, BigDecimal.ROUND_HALF_UP).toString() + "</"+ partner + ">";
				}
				resultXml = resultXml.replace(orgain, result);
			}
		}
		return resultXml;
	}

}
