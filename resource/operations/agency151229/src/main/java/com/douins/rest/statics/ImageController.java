/**
 * 
 */
package com.douins.rest.statics;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.common.util.JsonOnlineUtils;
import com.douins.statics.domain.vo.AdsImgRequest;
import com.douins.statics.domain.vo.AdsImgResponse;
import com.douins.statics.domain.vo.ImageVo;
import com.douins.statics.service.ImageService;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.model.RequestTrans;
import com.douins.trans.domain.model.ResponseTrans;
import com.mango.framework.controller.BasicController;

/** 
* @ClassName: ImageController 
* @Description: 图像接口
* @author G. F. 
* @date 2015年11月10日 下午4:33:18 
*  
*/
@Controller
@RequestMapping("/img")
public class ImageController extends BasicController {
    
    @Inject
    private ImageService imgService;
    
    /**
     * 获取图像列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/get/list")
    public String getList(HttpServletRequest request){
        String data = request.getParameter("data");
        logger.info("data=" + data);
        String transId = "";
        ResponseCode responseCode = ResponseCode.FAILED;
        AdsImgResponse imgResponse = new AdsImgResponse();
        try {
            AdsImgRequest productReq = JsonOnlineUtils.readJson2Entity(data,AdsImgRequest.class);
            if (productReq != null) {
                RequestTrans rt = productReq.getRequestTrans();
                transId = rt.getTransId();
                // 获取数据
                List<ImageVo> images = imgService.getLastImages();
                imgResponse.setImages(images);
                responseCode = ResponseCode.SUCCESS;
            }else{
                responseCode = ResponseCode.DATAREAD_ERROR;
            }
        } catch (Exception e) {
            responseCode = ResponseCode.FAILED;
            logger.error("get product error", e);
        }
        
        ResponseTrans responseTrans = new ResponseTrans(responseCode.getValue(),responseCode.getName(), transId);
        imgResponse.setResponseTrans(responseTrans);
        String response = JsonOnlineUtils.object2json(imgResponse);
        logger.info("result="+response);
        
        return response;
    }
}
