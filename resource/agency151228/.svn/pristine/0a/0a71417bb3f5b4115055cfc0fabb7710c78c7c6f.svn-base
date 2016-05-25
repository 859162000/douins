package com.douins.agency.service.douins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.ProductFact;
import com.douins.agency.service.douins.domain.model.ProductValid;
import com.douins.agency.service.douins.domain.vo.ProductVo;
import com.douins.agency.service.douins.domain.vo.ProductWhrCondition;
import com.google.common.collect.Maps;

@Repository
public class ProductInfoDao extends AbstractDao{
	
   public List<ProductVo> getProductVoByChannl(ProductWhrCondition pdWhrC){
	   return list(writeSqlSession, sql(), pdWhrC);
   }
   
   /**
    * 根据购买时长获取产品信息
    * @param channlCode
    * @param chPdId
    * @param duration
    * @return
    */
   public List<ProductVo> getProductVoByDuration(String channlCode, String chPdId, int duration){
       Map<String, Object> params = Maps.newHashMap();
       params.put("channlCode", channlCode);
       params.put("chPdId", chPdId);
       params.put("duration", duration);
       
       return list(writeSqlSession, sql(), params);
   }
   
   public ProductFact test(String productId){
       return writeSqlSession.selectOne(sql(), productId);
   }
   
   /**
    * 插入产品销售额度数据
    * @param productValid
    * @return
    */
   public int saveProductSales(ProductValid productValid){
	   return writeSqlSession.insert(sql(), productValid);
   }
   
   /**
    * 插入产品销售额度数据
    * @param productValid
    * @return
    */
   public int updateProductSales(ProductValid productValid){
	   return writeSqlSession.update(sql(), productValid);
   }
   
   /**
    * 通过产品编号取回产品销售额度数据
    * @param productCode
    * @return
    */
   public ProductValid getProductSaleWithProductCode(String productCode){
	   return writeSqlSession.selectOne(sql(), productCode);
   }
   
   /**
    * 通过订单编号取回产品销售额度数据
    * @param orderId
    * @return
    */
   public ProductValid getProductSaleWithOrderId(String orderId){
	   return writeSqlSession.selectOne(sql(), orderId);
   }
   
   /**
    * 通过保单号取出销售额度数据
    * @param orderId
    * @return
    */
   public ProductValid getProductSaleWithPolicyNo(String policyNo){
	   return writeSqlSession.selectOne(sql(), policyNo);
   }
   
   /**
    * 通过订单号取出一个销售总额数据
    * @param orderId
    * @return
    */
   public ProductValid getProductSaleWithISS(String orderId){
	   return writeSqlSession.selectOne(sql(), orderId);
   }
   
   /**
    * 通过保单号取出一个销售总额数据
    * @param policyNo
    * @return
    */
   public ProductValid getProductSaleWithWD(String policyNo){
	   return writeSqlSession.selectOne(sql(), policyNo);
   }
   
   /**
    * 取回所有产品的销售总额 神经病
    * @param productCode
    * @return
    */
   public ProductValid getProductSaleSum(String productCode){
	   return writeSqlSession.selectOne(sql(),productCode);
   }
}
