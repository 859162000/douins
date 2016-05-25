package com.douins.product.service.iml;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.douins.product.dao.ProductDao;
import com.douins.product.dao.ProductDetailDao;
import com.douins.product.domain.model.Product;
import com.douins.product.domain.model.ProductDetail;
import com.douins.product.domain.vo.ProductRequest;
import com.douins.product.domain.vo.ProductVo;
import com.douins.product.service.ProductService;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.orm.page.Page;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
//	@Autowired
//	private BaseDao<Product> baseDao;
//
//	private String mapper = Product.class.getName();
//
//	@Autowired
//	private BaseDao<ProductVo> productVoDao;
	
	@Inject
	private ProductDao productDao;
	@Inject
	private ProductDetailDao detailDao;

	@Override
	public boolean delete(String arg0, Product arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product findByKey(String key) {
		//return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	    return productDao.selectByPrimaryKey(key);
	}

	@Override
	public Page<Product> getPage(Product arg0, Page<Product> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String arg0, Product arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String arg0, Product arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Product> findByCondition(Product product)
			throws DataBaseAccessException {
		//return baseDao.getList(mapper + "Mapper.getList", product);
	    return productDao.getList(product);
	}

	@Override
	public List<ProductVo> getProduct4Api(ProductRequest productRequest)
			throws DataBaseAccessException {
		return getProductVo(productRequest.getProduct());
	}
	
	public List<ProductVo> getProductVo(Product product)
			throws DataBaseAccessException {
		//return productVoDao.getList(mapper + "Mapper.getVoList", product);
	    return productDao.getVoList(product);
	}
	
	public boolean updateAfterPaySuccess(String userName, Product product)
			throws DataBaseAccessException {
		//return baseDao.update(mapper + "Mapper.updateAfterPaySuccess", product) > 0;
	    productDao.updateAfterPaySuccess(product);
	    return true;
	}
	
	/**
	 * 获取推荐的产品
	 * @return
	 */
	public ProductVo getRecommendProduct(){
	   // return productDao.getRecommendProduct();
		ProductVo product = productDao.getRecommendProduct();
		//modify by sty , 该方法执行的sql  SELECT SUM(TOTAL_PREM) FROM T_POLICY_MASTER WHERE PRODUCT_ID = #{productId};包括了取消的保单
		
		//product.setSaledAmount(productDao.getSaledAmountById(product.getProductId()));
		//累计售出金额： 已售份数＊单价
		product.setSaledAmount(product.getMinPrem().multiply( new BigDecimal(product.getSaleNum())));
		return product;
	}
	
	/**
	 * 根据产品 ID 获取产品信息
	 * @param productId
	 * @return
	 */
	public ProductVo getProductById(String productId){
	    ProductVo product = productDao.getProductVoById(productId);
	    ProductDetail detail = detailDao.getByProductId(productId);
	    product.setValueDate(detail.getValueDate());
	    product.setRedemptionMode(detail.getRedemptionMode());
	   //product.setSaledAmount(productDao.getSaledAmountById(productId)); //统计该产品已售总金额
	    product.setSaledAmount(product.getMinPrem().multiply( new BigDecimal(product.getSaleNum())));
	    return product;
	}
}
