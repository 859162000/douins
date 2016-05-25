/**
 * 
 */
package com.douins.product.dao;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.product.domain.model.ProductDetail;

/** 
* @ClassName: ProductDetailDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午12:47:30 
*  
*/
@MyBatisMapper
public interface ProductDetailDao{

    public void insert(ProductDetail detail);
    
    public void updateByPrimaryKey(ProductDetail detail);
    
    public ProductDetail selectByPrimaryKey(@Param("productDetailId")String productDetailId);
    
    public ProductDetail getByProductId(@Param("productId")String productId);
}
