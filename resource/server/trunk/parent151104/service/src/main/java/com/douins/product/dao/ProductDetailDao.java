/**
 * 
 */
package com.douins.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.product.domain.model.ProductDetail;

/** 
* @ClassName: ProductDetailDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午12:47:30 
*  
*/
@Repository
public class ProductDetailDao extends AbstractDao {

    public void insert(ProductDetail detail){
        writeSqlSession.insert(sql(), detail);
    }
    
    public void updateByPrimaryKey(ProductDetail detail){
        writeSqlSession.update(sql(), detail);
    }
    
    public ProductDetail selectByPrimaryKey(String productDetailId){
        return writeSqlSession.selectOne(sql(), productDetailId);
    }
    
    public ProductDetail getByProductId(String productId){
        return writeSqlSession.selectOne(sql(), productId);
    }
}
