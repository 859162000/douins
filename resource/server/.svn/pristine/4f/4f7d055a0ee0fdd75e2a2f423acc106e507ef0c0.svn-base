/**
 * 
 */
package com.douins.product.dao;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.product.domain.model.Product;
import com.douins.product.domain.vo.ProductVo;

/** 
* @ClassName: ProductDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午3:00:55 
*  
*/
@Repository
public class ProductDao extends AbstractDao {
    
    public void deleteByPrimaryKey(String productId){
        writeSqlSession.update(sql(), productId);
    }
    
    public void insert(Product product){
        writeSqlSession.insert(sql(), product);  
    }
    
    public void updateByPrimaryKey(Product product){
        writeSqlSession.update(sql(), product);
    }
    
    public Product selectByPrimaryKey(String productId){
        return writeSqlSession.selectOne(sql(),  productId);
    }
    
    public List<Product> getList(Product product){
        return list(writeSqlSession, sql(), product);
    }
    
    public List<ProductVo> getVoList(Product product){
        return list(writeSqlSession, sql(), product);
    }
    
    public void updateAfterPaySuccess(Product product){
        writeSqlSession.update(sql(), product);
    }
    
    /**
     * 下单时更新可售份额和已售份额
     * @param product
     */
    public void updateWhileOrder(Product product){
        writeSqlSession.update(sql(), product);
    }
    
    /**
     * 获取最新推荐的产品
     * @return
     */
    public ProductVo getRecommendProduct(){
        return writeSqlSession.selectOne(sql(), null);
    }
}
