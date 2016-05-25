/**
 * 
 */
package com.douins.statics.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.douins.common.dao.AbstractDao;
import com.douins.statics.domain.model.Image;

/** 
* @ClassName: ImagesDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月10日 下午3:49:53 
*  
*/
@Repository
public class ImageDao extends AbstractDao {

    /**
     * 保存图像
     * @param image
     */
    public void add(Image image){
        writeSqlSession.insert(sql(), image);
    }
    
    /**
     * 获取最新的图像列表
     * @return
     */
    public List<Image> findLastImages(){
       return list(writeSqlSession, sql(), null);
    }
}
