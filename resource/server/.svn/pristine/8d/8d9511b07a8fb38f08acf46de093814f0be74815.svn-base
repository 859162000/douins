/**
 * 
 */
package com.douins.rest.ads;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.chrono.BuddhistChronology;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douins.common.util.JsonOnlineUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.framework.controller.BasicController;

/** 
* @ClassName: AdvertiseController 
* @Description: 广告相关的接口
* @author G. F. 
* @date 2015年11月5日 下午4:51:12 
*  
*/
@Controller
@RequestMapping("/ads")
public class AdvertiseController extends BasicController {
    public static final String BASE_URL = "http://120.26.218.0/advImgs";
    
    /**
     * 获取广告图像
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/imgs/get")
    public String getImgs(HttpServletRequest request){
        List<Map<String, String>> imgs = Lists.newArrayList();
        Map<String, String> item = Maps.newHashMap();
        
        item.put("name", "img1");
        String img1 =  BASE_URL+"/banner01.png";
        item.put("url", img1);
        imgs.add(item);
        
        Map<String, String> item2 = Maps.newHashMap();
        item2.put("name", "img2");
        String img2 = BASE_URL+"/banner02.png";
        item2.put("url", img2);
        imgs.add(item2);
        
        String result = JsonOnlineUtils.object2json(imgs);
        return result;
    }
}
