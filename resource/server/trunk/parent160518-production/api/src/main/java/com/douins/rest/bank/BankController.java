/**
 * 
 */
package com.douins.rest.bank;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.User;
import com.douins.account.domain.vo.UserRequestTrans;
import com.douins.bank.service.iml.GFBankService;
import com.douins.common.Api;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.ResponseUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.vo.RequestTransVo;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.google.common.util.concurrent.Service;

/** 
* @ClassName: BankControoler 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月27日 上午10:29:38 
*  
*/
@Controller
@RequestMapping("bank/")
public class BankController {
    private Logger logger = Logger.getLogger(BankController.class);
    
    @Resource
    private GFBankService gfBankService;
    
    /**
     * 拼接带签名的 url 串
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("sign/url")
    public String signUrl(HttpServletRequest request){
        ResponseTransVo<String> responseTransVo = new ResponseTransVo<String>();
        ResponseCode responseCode = ResponseCode.FAILED;
        String transId = null;
        try {
            String data = request.getParameter("data");
            logger.info("请求数据 ＝"+ data);
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN); 
            RequestTransVo<User> userRequestVo = JsonOnlineUtils.readJson2Entity2(data, User.class);
            transId = userRequestVo.getRequestTrans().getTransId();
            User user = userRequestVo.getRequestParams();
            String url = gfBankService.signUrl(token, user);
            responseTransVo.setResponseParams(url);
            responseCode = ResponseCode.SUCCESS;
        } catch (Exception e) {
            logger.error("url 拼接错误", e);
        }
        
        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId);
        logger.info("响应数据＝"+ result);
        return result;
    }
    
    /**
     * 查询开户结果
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("query/reg/status")
    public String queryRegistStatus(HttpServletRequest request){
//    	ResponseTransVo<String> responseTransVo = new ResponseTransVo<String>();
//        ResponseCode responseCode = ResponseCode.FAILED;
//        String transId = null;
//        try{
//	        String data = request.getParameter("data");
//	        logger.info("发送的数据为＝＝＝＝＝\n"+data);
//	        String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);
//	        UserRequestTrans userRequestTrans = JsonOnlineUtils.readJson2Entity(data, UserRequestTrans.class);
//	        transId = userRequestTrans.getRequestTrans().getTransId();        
//	        responseCode = gfBankService.queryRegistResult(token);
//        }catch(Exception e){
//        	logger.error("查询开户结果出错",e);
//        }
//        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId);
//        logger.info("响应数据为"+result);
        return null;
    }
}
