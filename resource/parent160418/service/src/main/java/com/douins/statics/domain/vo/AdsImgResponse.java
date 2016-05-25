/**
 * 
 */
package com.douins.statics.domain.vo;

import java.util.List;

import com.douins.statics.domain.model.Image;
import com.douins.trans.domain.vo.ResponseTransVo;

/** 
* @ClassName: AdsImgVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月19日 下午4:27:00 
*  
*/
public class AdsImgResponse extends ResponseTransVo {
    private List<ImageVo> images;

    public List<ImageVo> getImages() {
        return images;
    }

    public void setImages(List<ImageVo> images) {
        this.images = images;
    }

    
}
