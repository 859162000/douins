/**
 * 
 */
package com.douins.statics.service;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.douins.statics.dao.ImageDao;
import com.douins.statics.domain.model.Image;
import com.douins.statics.domain.vo.ImageVo;
import com.google.common.collect.Lists;

/** 
* @ClassName: ImageService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月10日 下午4:14:51 
*  
*/
@Service
public class ImageService {
 
    @Inject
    private ImageDao imageDao;
    
    /**
     * 保存图像
     * @param img
     */
    public void save(Image img){
        if(img != null){
            imageDao.add(img);
        }
    }
    
    /**
     * 获取最新的图像列表
     * @return
     */
    public List<ImageVo> getLastImages(){
        List<Image> images =imageDao.findLastImages();
        if(images == null){
            images = Collections.emptyList();
        }
        
        List<ImageVo> vos = Lists.newArrayList();
        for(int i =0; i < images.size();i++){
            ImageVo vo = new ImageVo(images.get(i));
            vos.add(vo);
        }
        
        return vos;
    }
}
