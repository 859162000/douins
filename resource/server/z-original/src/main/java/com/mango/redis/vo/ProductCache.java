/*package com.mango.redis.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mango.api.productAPI.vo.ProductVo;
import com.mango.online.product.model.Product;
import com.mango.online.product.service.ProductService;
import com.mango.online.product.service.impl.ProductServiceImpl;
import com.mango.redis.RedisCacheService;
import com.mango.redis.exception.RedisExcepiton;
@Service
public class ProductCache {
	@Autowired
	private RedisCacheService redisCache;
	private static final int expireTime = 24*3600;
	public ProductVo getProductById(String productId){
		ProductVo productVo = null;
		try {
			productVo = redisCache.getValue("product:"+productId);
		} catch (RedisExcepiton e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productVo;
	}
	
	public List<ProductVo> getAllProduct(){
		List<ProductVo> productVos = new ArrayList<ProductVo>();
		Set <String>products=  redisCache.zrange("product",0,-1);
		for(String product : products){
			ProductVo productVo;
			try {
				productVo = redisCache.getValue(product);
			} catch (RedisExcepiton e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			}
			productVos.add(productVo);
		}
		return productVos;
	}
	public void writeProduct(List<ProductVo> productVos){
		for(ProductVo productVo:productVos){			
			redisCache.sAdd(productVo.getClass().getSimpleName(), "product:"+productVo.getProductId());
			
			redisCache.setValue(productVo.getClass().getSimpleName()+":"+productVo.getProductId(), productVo);
			redisCache.setKeyExpireTime(productVo.getClass().getSimpleName()+":"+productVo.getProductId(), expireTime);
		}
		redisCache.setKeyExpireTime(ProductVo.class.getSimpleName(), expireTime);
	}
	
	
	public void initAllProduct(List<ProductVo> productVos){
		redisCache.remove("product");
		for(int i=0;i<productVos.size();i++){			
			redisCache.zAdd("product",String.valueOf(i), "product:"+productVos.get(i).getProductId());
			
			redisCache.setValue("product:"+productVos.get(i).getProductId(), productVos.get(i));
			redisCache.setKeyExpireTime("product:"+productVos.get(i).getProductId(), expireTime);
		}
		redisCache.setKeyExpireTime("product", expireTime);
	}
	
	public boolean exitProduct(String productId){
		boolean exists;
		try {
			exists = redisCache.exists("product:"+productId);
		} catch (RedisExcepiton e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return exists;
	}

		;
		
}
*/