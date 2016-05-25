/**
 * 
 */
package com.douins.statics.dao;

import java.util.List;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.statics.domain.model.Image;

/**
 * @ClassName: ImagesDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年11月10日 下午3:49:53
 * 
 */
@MyBatisMapper
public interface ImageDao {

	/**
	 * 保存图像
	 * 
	 * @param image
	 */
	public void add(Image image);

	/**
	 * 获取最新的图像列表
	 * 
	 * @return
	 */
	public List<Image> findLastImages();
}
