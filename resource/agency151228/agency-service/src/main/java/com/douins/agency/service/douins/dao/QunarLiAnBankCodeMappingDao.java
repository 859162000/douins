package com.douins.agency.service.douins.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.douins.agency.service.common.dao.AbstractDao;
import com.douins.agency.service.douins.domain.model.InsureCancelApplyHeader;
/**
 * @author shaotongyao
 *
 */
@Repository
public class QunarLiAnBankCodeMappingDao extends AbstractDao{

   //通过银行名称获利安对应代码
   public String selectLiAnCodeByBankName(String bankName){
	   
	  return writeSqlSession.selectOne(sql(), bankName);
	   
   }
}
