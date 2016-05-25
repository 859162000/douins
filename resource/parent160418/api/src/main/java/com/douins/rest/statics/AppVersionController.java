/**
 * 
 */
package com.douins.rest.statics;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.ResponseUtils;
import com.douins.statics.domain.model.AppVersion;
import com.douins.statics.service.AppVersionService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.vo.RequestTransVo;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.framework.controller.BasicController;

/** 
* @ClassName: AppVersionController 
* @Description: App 版本控制
* @author G. F. 
* @date 2016年2月1日 上午11:20:00 
*  
*/
@Controller
@RequestMapping("sys/version/")
public class AppVersionController extends BasicController {
    private Logger logger = Logger.getLogger(AppVersionController.class);
    
    @Resource
    private AppVersionService versionService;
    
    /**
     * 获取是否有新版本存在
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("new")
    public String newVersion(HttpServletRequest request){
//        ResponseTransVo<String> responseTransVo = new ResponseTransVo<String>();
        ResponseTransVo<String> responseTransVo = new ResponseTransVo<String>();
        ResponseCode responseCode = ResponseCode.FAILED;
        String transId = null;
        try {
            String data = request.getParameter("data");
            logger.info("请求数据 ＝"+ data);
            RequestTransVo<AppVersion> versionVo = JsonOnlineUtils.readJson2Entity2(data, AppVersion.class);
            String result = versionService.newVersionExist(versionVo.getRequestParams().getVersion());
            responseTransVo.setResponseParams(result);
            transId = versionVo.getRequestTrans().getTransId();
            responseCode = ResponseCode.SUCCESS;
        } catch (Exception e) {
            logger.error("检查新版本失败====================", e);
        }
        
        String returnStr = ResponseUtils.getResponse(responseTransVo, responseCode, transId);
        logger.info("返回数据===================\n" + returnStr);
        return returnStr;
    }
}
