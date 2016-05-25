/**
 * 
 */
package com.douins.rest.idea;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.SaveEntityUtils;
import com.douins.idea.domain.model.IdeaFeedBack;
import com.douins.idea.domain.vo.IdeaRequestTrans;
import com.douins.idea.service.IdeaFeedBackService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.core.common.util.DateUtils;
import com.mango.core.logger.SimpleLogger;
import com.mango.framework.controller.BasicController;

/** 
* @ClassName: IdeaAPICtroller 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月4日 下午1:14:23 
*  
*/
@Controller
@Scope("prototype")
@RequestMapping("/idea")
public class IdeaAPIController extends BasicController {
   private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());

   @Autowired
   private IdeaFeedBackService ideaFeedBackService;

   @ResponseBody
   @RequestMapping(value = "/save", method = RequestMethod.POST)
   public String idea(HttpServletRequest request) {
       ResponseTransVo responseTrans = new ResponseTransVo();
       ResponseCode responseCode = ResponseCode.FAILED;
       String transId = null;
       try {
           String data = request.getParameter("data");
           IdeaRequestTrans ideaReqTrans = JsonOnlineUtils.readJson2Entity(data, IdeaRequestTrans.class);
           RequestTrans requestTrans = ideaReqTrans.getRequestTrans();
           transId = requestTrans.getTransId();
           IdeaFeedBack ideaFeedBack = ideaReqTrans.getIdeaVo();
           ideaFeedBack.setFeedbackTime(DateUtils.today());
           SaveEntityUtils.initEntityBeforeInsert(ideaFeedBack, "");
           ideaFeedBackService.save("", ideaFeedBack);
           responseCode = ResponseCode.SUCCESS;
       } catch (Exception e) {
           logger.error("login error", e);
       }
       ResponseTrans trans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
       responseTrans.setResponseTrans(trans);
       String result = JsonOnlineUtils.bean2json(responseTrans);
       logger.info(result);
       return result;
   }
}
