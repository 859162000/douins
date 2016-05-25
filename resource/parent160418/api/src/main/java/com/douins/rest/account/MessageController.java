/**
 * 
 */
package com.douins.rest.account;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.MessageRequestVo;
import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.model.UserPrivateMessage;
import com.douins.account.domain.vo.PublicMessgaeVo;
import com.douins.account.domain.vo.UserPrivateMsgVo;
import com.douins.account.service.iml.PublicMessageService;
import com.douins.account.service.iml.UserPrivateMsgService;
import com.douins.common.Api;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.framework.controller.BasicController;

/** 
* @ClassName: MessageController 
* @Description: 消息 API，包括公有消息和私有消息
* @author G. F. 
* @date 2015年11月23日 上午10:06:00 
*  
*/
@Controller
@RequestMapping("/msg")
public class MessageController extends BasicController {
    private static final Logger logger = Logger.getLogger(MessageController.class);
    
    @Inject
    private UserPrivateMsgService privateMsgService;
    @Inject
    private PublicMessageService publicMessageService;
    
    /**
     * 列举私有消息
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("/priv/list")
    public String listPrivMsg(HttpServletRequest request){
        ResponseCode responseCode=ResponseCode.FAILED;
        UserPrivateMsgVo privateMsgVo = new UserPrivateMsgVo();
        String transId=null;
        
        try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN);
            MessageRequestVo messageRequest = JsonOnlineUtils.readJson2Entity(data, MessageRequestVo.class);
            if (messageRequest != null) {
                RequestTrans rt = messageRequest.getRequestTrans();
                transId = rt.getTransId();
                // 获取私有消息
                List<UserPrivateMessage> messages = privateMsgService.getUserPrivMsg(token, messageRequest.getMessageRequest());
                privateMsgVo.setMessages(messages);
                responseCode = ResponseCode.SUCCESS;
            }
        } catch (Exception e) {
            responseCode = ResponseCode.DATAREAD_ERROR;
        }
        
        ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
        privateMsgVo.setResponseTrans(responseTrans);
        String response = JsonOnlineUtils.object2json(privateMsgVo);
        
        return response;
    }
    
    /**
     * 软删除私有消息
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("/priv/delete")
    public String deletePrivMsg(HttpServletRequest request){
        ResponseCode responseCode=ResponseCode.FAILED;
        String transId = null;
        try{
            String data = request.getParameter("data");
            MessageRequestVo messageRequest = JsonOnlineUtils.readJson2Entity(data, MessageRequestVo.class);
            if (messageRequest != null) {
                RequestTrans rt = messageRequest.getRequestTrans();
                transId = rt.getTransId();
                // 删除消息
                privateMsgService.deleteMsg(messageRequest.getMessageRequest().getNotificationId());
                responseCode = ResponseCode.SUCCESS;
            }
        }catch (Exception e) {
            responseCode = ResponseCode.DATAREAD_ERROR;
            logger.error("删除消息错误：" , e);
        }
        
        UserPrivateMsgVo msgVo = new UserPrivateMsgVo();
        ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
        msgVo.setResponseTrans(responseTrans);
        String response = JsonOnlineUtils.object2json(msgVo);
        logger.info(response);
        
        return response;
    }
    
    /**
     * 将私有消息置为已读
     * @param request
     * @return
     */
    @Api(token = true)
    @ResponseBody
    @RequestMapping("/priv/read")
    public String readMsg(HttpServletRequest request){
        ResponseCode responseCode = ResponseCode.FAILED;
        String transId = null;
        
        try{
            String data = request.getParameter("data");
            MessageRequestVo messageRequest = JsonOnlineUtils.readJson2Entity(data, MessageRequestVo.class);
            if (messageRequest != null) {
                RequestTrans rt = messageRequest.getRequestTrans();
                transId = rt.getTransId();
                //消息置为已读
                privateMsgService.setIsRead(messageRequest.getMessageRequest().getNotificationId());
                responseCode = ResponseCode.SUCCESS;
            }
        }catch (Exception e) {
            responseCode = ResponseCode.DATAREAD_ERROR;
            logger.error("置为已读错误", e);
        }
        
        UserPrivateMsgVo msgVo = new UserPrivateMsgVo();
        ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
        msgVo.setResponseTrans(responseTrans);
        String response = JsonOnlineUtils.object2json(msgVo);
        logger.info(response);
        
        return response;
    }
    
    /**
     * 获取公有消息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/pub/list")
    public String listPubMsg(HttpServletRequest request){
        ResponseCode responseCode=ResponseCode.FAILED;
        PublicMessgaeVo publicMessgaeVo = new PublicMessgaeVo();
        String transId=null;
        
        try {
            String data = request.getParameter("data");
            logger.info("data = "+ data);
            MessageRequestVo messageRequest = JsonOnlineUtils.readJson2Entity(data, MessageRequestVo.class);
            if (messageRequest != null) {
                RequestTrans rt = messageRequest.getRequestTrans();
                transId = rt.getTransId();
                // 获取公有消息
                List<PublicMessage> notations = publicMessageService.getMessages(messageRequest.getMessageRequest());
                logger.info("count = "+notations.size());
                publicMessgaeVo.setMessages(notations);
                responseCode = ResponseCode.SUCCESS;
            }
        } catch (Exception e) {
            responseCode = ResponseCode.DATAREAD_ERROR;
            logger.error("load pub msg error.", e);
        }
        
        ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
        publicMessgaeVo.setResponseTrans(responseTrans);
        String response = JsonOnlineUtils.object2json(publicMessgaeVo);
        logger.info(response);
        
        return response;
    }
}
