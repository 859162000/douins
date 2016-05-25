/**
 * 
 */
package com.douins.product.service.iml;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.jetty.util.ajax.JSONObjectConvertor;
import org.junit.Test;

import com.douins.AbstractTest;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.product.domain.model.ProductDetail;
import com.douins.product.domain.vo.ProductDetailResponse;
import com.douins.product.domain.vo.ProductResponse;
import com.douins.product.domain.vo.ProductVo;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.mongodb.util.JSON;

/** 
* @ClassName: ProductServiceImplTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月11日 下午2:53:52 
*  
*/
public class ProductServiceImplTest extends AbstractTest {

    @Inject
    private ProductServiceImpl service;
    @Inject
    private ProductDetailServiceImpl detailService;
    
    @Test
    public void test() {
       ProductDetail detail = new ProductDetail();//detailService.getByProductId("001");
       ProductDetailResponse productDetailResp = new ProductDetailResponse();
       productDetailResp.setProductDetail(detail);
//       List<ProductVo> vos = Lists.newArrayList();
//       productResp.setProductList(vos);
        
        String res = JsonOnlineUtils.object2json(productDetailResp);
        System.out.println(res);
    }

}
