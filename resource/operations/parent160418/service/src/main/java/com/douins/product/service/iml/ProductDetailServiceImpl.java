package com.douins.product.service.iml;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.core.logger.SimpleLogger;
import com.mango.exception.DataBaseAccessException;
import com.douins.product.dao.ProductDetailDao;
import com.douins.product.domain.model.ProductDetail;
import com.douins.product.service.ProductDetailService;
import com.mango.orm.BaseDao;
import com.mango.orm.page.Page;

@Service("productDetailService")
public class ProductDetailServiceImpl implements ProductDetailService {

	private SimpleLogger logger = SimpleLogger.getLogger(this.getClass());
	
//	@Autowired
//	private BaseDao<ProductDetail> baseDao;
//
//	private String mapper = ProductDetail.class.getName();
	
	@Inject
	private ProductDetailDao detailDao;

	@Override
	public boolean delete(String arg0, ProductDetail arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductDetail findByKey(String key) {
		//return baseDao.get(mapper + "Mapper.selectByPrimaryKey", key);
	    ProductDetail detail = detailDao.selectByPrimaryKey(key);
//	    if(detail == null){
//	        detail = new ProductDetail();
//	    }
	    
	    return detail;
	}
	
	@Override
	public ProductDetail getByProductId(String productId) {
		//return baseDao.get(mapper + "Mapper.getByProductId", productId);
	    return detailDao.getByProductId(productId);
	}
	
	@Override
	public Page<ProductDetail> getPage(ProductDetail arg0,
			Page<ProductDetail> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(String arg0, ProductDetail arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String arg0, ProductDetail arg1)
			throws DataBaseAccessException {
		// TODO Auto-generated method stub
		return false;
	}
 

	
}
