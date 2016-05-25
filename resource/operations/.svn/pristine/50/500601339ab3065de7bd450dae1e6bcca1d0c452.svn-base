/**
 * 
 */
package com.douins.agency.service.douins.service.database;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.douins.agency.service.common.Constants;
import com.douins.agency.service.douins.dao.InsureCancelConfirmFeedbackDao;
import com.douins.agency.service.douins.dao.InsureConfirmFeedbackDao;
import com.douins.agency.service.douins.dao.InsureRequestFeedbackDao;
import com.douins.agency.service.douins.dao.ProductInfoDao;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmFeedback;
import com.douins.agency.service.douins.domain.model.InsureConfirmFeedback;
import com.douins.agency.service.douins.domain.model.InsureRequestFeedback;
import com.douins.agency.service.douins.domain.vo.ProductVo;
import com.douins.agency.service.douins.domain.vo.ProductWhrCondition;

/** 
* @ClassName: CCICDataService 
* @Description: CCIC 数据访问
* @author G. F. 
* @date 2016年1月1日 下午3:51:41 
*  
*/
@Service(Constants.CCIC_DATA_SERVICE)
public class CCICDataService {
    @Resource
    private ProductInfoDao productInfoDao;
    @Resource
    private InsureRequestFeedbackDao isReqFbDao;
    @Resource
    private InsureConfirmFeedbackDao isCfmFbDao;
    @Resource
    private InsureCancelConfirmFeedbackDao isCnlFbDao;
   
    
    /**
     * 获取产品相关信息
     * @param channlCode        销售渠道的代码
     * @param chPdId                产品在销售渠道的编码
     * @param stategyCode       销售策略代码
     * @return  相关信息列表
     */
    public List<ProductVo> getProductInfos(String channlCode, String chPdId, String stategyCode){
       ProductWhrCondition condition = new ProductWhrCondition();
        condition.setChannlCode(channlCode);
        condition.setChannlPdId(chPdId);
        condition.setSaleStrategy(stategyCode);
        
        List<ProductVo> infos = productInfoDao.getProductVoByChannl(condition);
        return infos;
    }
    
    /**
     * 根据购买的时长获取产品相关信息
     * @param channlCode
     * @param chPdId
     * @param duration
     * @return
     */
    public List<ProductVo> getProductInfos(String channlCode, String chPdId, int duration){
        List<ProductVo> infos = productInfoDao.getProductVoByDuration(channlCode, chPdId, duration);
        return infos;
    }
    
    /**
     * 试算(核保)请求的返回结果落地
     * @param isReqFb        试算请求结果的返回结构
     */    
    public void saveInsureReqFeedback(InsureRequestFeedback isReqFb){
    	//InsureRequestFeedbackDao isReqFbDao = new InsureRequestFeedbackDao();
    	isReqFbDao.insert(isReqFb);
    }

    /**
     * 承保请求的返回结果落地
     * @param isCfmFb        承保结果的返回结构
     */    
    public void saveInsureCfmFeedback(List<InsureConfirmFeedback> isCfmFbs){
    	//InsureConfirmFeedbackDao isCfmFbDao = new InsureConfirmFeedbackDao();
    	isCfmFbDao.insert(isCfmFbs);
    }
    
    /**
     * 保存退保返回的数据
     * @param items
     */
    public void saveInsureCancelCfmFeedback(List<InsureCancelConfirmFeedback> items){
        isCnlFbDao.insert(items);
    }
}
