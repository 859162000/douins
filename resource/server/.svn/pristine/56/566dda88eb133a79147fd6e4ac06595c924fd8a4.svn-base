package com.mango.fortune.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.api.productAPI.vo.ProductRequest;
import com.mango.api.productAPI.vo.ProductVo;
import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.mango.fortune.product.model.Product;
import com.mango.fortune.product.service.ProductService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
	@Autowired
	private BaseDao<Product> baseDao;

	private String mapper = Product.class.getName();

	@Autowired
	private BaseDao<ProductVo> productVoDao;

	@Override
	public boolean delete(String arg0, Product arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product findByKey(String key) {
		return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
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
		return baseDao.getList(mapper + "Mapper.getList", product);
	}

	@Override
	public List<ProductVo> getProduct4Api(ProductRequest productRequest)
			throws DataBaseAccessException {
		return getProductVo(productRequest.getProduct());
	}
	
	public List<ProductVo> getProductVo(Product product)
			throws DataBaseAccessException {
		return productVoDao.getList(mapper + "Mapper.getVoList", product);
	}
	
	public boolean updateAfterPaySuccess(String userName, Product product)
			throws DataBaseAccessException {
		return baseDao.update(mapper + "Mapper.updateAfterPaySuccess", product) > 0;
	}
}
