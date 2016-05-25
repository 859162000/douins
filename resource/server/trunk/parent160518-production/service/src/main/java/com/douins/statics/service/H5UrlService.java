/**
 * 
 */
package com.douins.statics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douins.statics.domain.vo.H5UrlVo;

/** 
* @ClassName: H5UrlService 
* @Description: 获取静态 url
* @author G. F. 
* @date 2015年12月21日 下午4:16:00 
*  
*/
@Service
public class H5UrlService {
    public H5UrlVo getUrls(){
        return new H5UrlVo();
    }
}
