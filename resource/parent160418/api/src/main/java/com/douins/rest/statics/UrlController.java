/**
 * 
 */
package com.douins.rest.statics;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.xml.resolver.helpers.PublicId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.User;
import com.douins.bank.domain.model.nybc.RegistAccountResponseBody;
import com.douins.bank.domain.vo.RegistAccountResponseVo;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.ResponseUtils;
import com.douins.statics.domain.model.H5Url;
import com.douins.statics.domain.vo.H5UrlVo;
import com.douins.statics.service.H5UrlService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.vo.RequestTransVo;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.framework.controller.BasicController;

/** 
* @ClassName: UrlController 
* @Description: 静态 Url
* @author G. F. 
* @date 2015年12月21日 下午4:20:05 
*  
*/
@Controller
@RequestMapping("/url/")
public class UrlController extends BasicController {
    @Inject
    private H5UrlService urlService;
    
    @ResponseBody
    @RequestMapping("h5")
    public String getH5Url(HttpServletRequest request){
        ResponseTransVo< H5UrlVo> responseTransVo = new ResponseTransVo<H5UrlVo>();
        String transId = null;
        ResponseCode responseCode = ResponseCode.FAILED;
        try {
            String data = request.getParameter("data");
           RequestTransVo<H5Url> requestTrans = JsonOnlineUtils.readJson2Entity2(data, H5Url.class);
           transId = requestTrans.getRequestTrans().getTransId();
           H5UrlVo urlVo = urlService.getUrls();
           responseTransVo.setResponseParams(urlVo);
           responseCode = ResponseCode.SUCCESS;
        } catch (Exception e) {
            logger.error("获取 H5 url 错误");
        }
        
        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId) ;
        return result;
    }
}
