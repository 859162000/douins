/**
 * 
 */
package com.douins.product.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.douins.common.persistence.annotation.MyBatisMapper;
import com.douins.product.domain.model.Product;
import com.douins.product.domain.vo.ProductVo;

/**
 * @ClassName: ProductDao
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author G. F.
 * @date 2015年10月21日 下午3:00:55
 * 
 */
@MyBatisMapper
public interface ProductDao {

	public void deleteByPrimaryKey(@Param("productId") String productId);

	public void insert(Product product);

	public void updateByPrimaryKey(Product product);

	public Product selectByPrimaryKey(@Param("productId") String productId);

	public List<Product> getList(Product product);

	public List<ProductVo> getVoList(Product product);

	public void updateAfterPaySuccess(Product product);

	/**
	 * 下单时更新可售份额和已售份额
	 * 
	 * @param product
	 */
	public void updateWhileOrder(Product product);

	/**
	 * 获取最新推荐的产品
	 * 
	 * @return
	 */
	public ProductVo getRecommendProduct();

	/**
	 * 根据产品 ID 获取产品信息
	 * 
	 * @param productId
	 * @return
	 */
	public ProductVo getProductVoById(@Param("productId") String productId);

	public BigDecimal getSaledAmountById(@Param("productId") String productId);

}
