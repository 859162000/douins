package com.douins.agency.service.douins.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.ProductFact;
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
}
