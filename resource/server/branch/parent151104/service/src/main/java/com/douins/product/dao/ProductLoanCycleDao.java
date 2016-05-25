/**
 * 
 */
package com.douins.product.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.douins.common.dao.AbstractDao;
import com.douins.product.domain.model.ProductLoanCycle;

/** 
* @ClassName: ProductLoanCycleDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月21日 下午2:35:52 
*  
*/
@Repository
public class ProductLoanCycleDao extends AbstractDao {

    public void deleteByPrimaryKey(String loanCycleId){
        writeSqlSession.update(sql(), loanCycleId);
    }
    
    public void insert(ProductLoanCycle loanCycle){
        writeSqlSession.insert(sql(), loanCycle);
    }
    
    public void updateByPrimaryKey(ProductLoanCycle loanCycle){
        writeSqlSession.update(sql(), loanCycle);
    }
    
    public ProductLoanCycle selectByPrimaryKey(String loanCycleId){
        return writeSqlSession.selectOne(sql(), loanCycleId);
    }
    
    public List<ProductLoanCycle> getList(ProductLoanCycle  loanCycle){
        return list(writeSqlSession, sql(), loanCycle);
    }
}
