/**
 * 
 */
package com.douins.agency.service.douins.service.database;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.douins.agency.service.douins.dao.InsureCancelApplyHeaderDao;
import com.douins.agency.service.douins.dao.InsureCancelApplyItemDao;
import com.douins.agency.service.douins.dao.InsureCancelConfirmHeaderDao;
import com.douins.agency.service.douins.dao.InsureCancelConfirmItemDao;
import com.douins.agency.service.douins.dao.InsureConfirmHeaderDao;
import com.douins.agency.service.douins.dao.InsureConfirmItmDao;
import com.douins.agency.service.douins.dao.InsureRequestHeaderDao;
import com.douins.agency.service.douins.dao.InsureRequestItmDao;
import com.douins.agency.service.douins.dao.QunarGroupInfoDao;
import com.douins.agency.service.douins.domain.model.InsureCancelApplyHeader;
import com.douins.agency.service.douins.domain.model.InsureCancelApplyItem;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmHeader;
import com.douins.agency.service.douins.domain.model.InsureCancelConfirmItem;
import com.douins.agency.service.douins.domain.model.InsureConfirmHeader;
import com.douins.agency.service.douins.domain.model.InsureConfirmItem;
import com.douins.agency.service.douins.domain.model.InsureRequestFeedback;
import com.douins.agency.service.douins.domain.model.InsureRequestHeader;
import com.douins.agency.service.douins.domain.model.InsureRequestItem;
import com.douins.agency.service.douins.domain.model.QunarGroupInfo;
import com.douins.agency.service.douins.domain.model.QunarOrderStatu;
import com.douins.agency.service.douins.domain.model.QunarReqReturn;
import com.douins.agency.service.douins.domain.model.QunarReqWhrCondition;
import com.douins.agency.service.douins.domain.vo.InsureApplyDataVo;
import com.google.common.collect.Lists;

/** 
* @ClassName: QunarDataService 
* @Description: qunar 的数据操作业务
* @author G. F. 
* @date 2016年1月2日 上午9:58:29 
*  
*/
@Service
public class QunarDataService {
    @Resource
    private InsureRequestHeaderDao isReqHdDao;
    @Resource
    private InsureRequestItmDao isReqItmDao; 
    @Resource
    private InsureConfirmHeaderDao isCfmHdDao;
    @Resource
    private InsureConfirmItmDao isCfmItmDao;
    @Resource
    private QunarGroupInfoDao qnGpInfoDao;
    @Resource
    private InsureCancelApplyHeaderDao isCnlApplyHeaderDao;
    @Resource
    private InsureCancelApplyItemDao isCnlApplyItemDao;
    @Resource
    private InsureCancelConfirmHeaderDao isCnlCfmHeaderDao;
    @Resource
    private InsureCancelConfirmItemDao isCnlCfmItemDao;
    
    /**
     * 根据订单号查询相应的核保记录用于承保
     * @param orderNo
     * @return
     */
    public List<InsureApplyDataVo> getApplyByOrder(String orderNo){
        List<InsureRequestFeedback> listisReqFb = isReqItmDao.getCfmedReqItmByOrderNo(orderNo);
        List<InsureApplyDataVo> vos = Lists.newArrayList();
        if(listisReqFb != null && listisReqFb.size() > 0){
            for(InsureRequestFeedback fb : listisReqFb){
                InsureApplyDataVo vo = new InsureApplyDataVo(fb);
                vos.add(vo);
            }
        }
        return vos;
    }
    
    /**
     * 试算请求的落地
     * @param isReqhd             试算请求的header结构
     * @param listIsReqItm        试算请求的item结构List
     */   
	public void saveInsureReq(InsureRequestHeader isReqhd,List<InsureRequestItem> listIsReqItm){
//		InsureRequestHeaderDao isReqHdDao = new InsureRequestHeaderDao();
//		InsureRequestItmDao isReqItmDao = new InsureRequestItmDao();
	
		isReqHdDao.insert(isReqhd); 
		for(int i=0; i<listIsReqItm.size();i++){
			listIsReqItm.get(i).setHeadId(isReqhd.getId());   
			isReqItmDao.insert(listIsReqItm.get(i));
		}
	}
	
    /**
     * 承保请求的落地
     * @param isCfmhd             承保请求的header结构
     * @param listIsCfmItm        承保请求的item结构List
     */   	
	public int saveInsureCfm(InsureConfirmHeader isCfmhd,List<InsureConfirmItem> listIsCfmItm){	
		isCfmHdDao.insert(isCfmhd);
		List<InsureConfirmItem> items = Lists.newArrayList();
		for(int i=0;i<listIsCfmItm.size();i++){
			listIsCfmItm.get(i).setHeadId(isCfmhd.getId());
			items.add(listIsCfmItm.get(i));
		}
		int count = isCfmItmDao.insert(items);
		return count;
	}
	
    /**
     * 试算请求状态更新
     * @param isReqhd             试算请求的header结构
     */   
    public void updateInsureReqStatus(InsureRequestHeader isReqhd){
    	isReqHdDao.updateByOrder(isReqhd);
    }
    
    /**
     * 承保请求状态更新
     * @param isCfmhd             承保请求的header结构
     */  
    public void updateInsureCfmStatus(InsureConfirmHeader isCfmhd){
    	isCfmHdDao.updateByOrder(isCfmhd);
    }
    
    /**
     * 获得核保结果返回数据
     * @param qnGpInfo             核保请求header
     * @param listIsReqItm         核保请求item
     * @return 核保结果
     */  
    public List<QunarReqReturn> getQunarReqRetrun(InsureRequestHeader isReqhd,List<InsureRequestItem> listIsReqItm){
    	
    	QunarGroupInfo qnGpInfo = new QunarGroupInfo();
    	List<QunarReqReturn> listQnReqRetrun = new ArrayList<QunarReqReturn>();
//    	QunarGroupInfoDao qnGpInfoDao = new QunarGroupInfoDao();

    	QunarReqWhrCondition qnReqWhrCondit = new QunarReqWhrCondition();
    	String cfmFlag = "1";
    	
    	qnGpInfo.setOrderNo(isReqhd.getOrderNo());
    	qnGpInfo.setBusinessId(isReqhd.getBusinessId());
    	qnGpInfo.setAgencyNo(isReqhd.getAgencyNo());
    	qnReqWhrCondit = makeQnReqWhrCondi(qnGpInfo,listIsReqItm);
    	String reqHdId = qnGpInfoDao.getHeadidByGpInfo(qnGpInfo);
    	
    	//判断传入的qnGpInfo是不是已存在于豆芽db，如果已存在说明是二次核保
    	if(reqHdId != null){
    		listQnReqRetrun = isReqItmDao.getQunarReqReturn(qnReqWhrCondit);

    		qnReqWhrCondit.setCfmFlag(cfmFlag);
    		isReqItmDao.updateCfmflagByApplyseq(qnReqWhrCondit);    //一核二核都有的applySeq打上cfmFlg

    	}else{
    		saveInsureReq(isReqhd,listIsReqItm);
    		//调用试算请求发送大地的接口
    	}
    	return listQnReqRetrun;
    }
    
    /**
     * 拼接核保请求item表的检索条件，使用where in（）语句避免循环访问db
     * @param qnGpInfo             核保请求header
     * @param listIsReqItm         核保请求item
     * @return 检索条件的对象
     */  
    public QunarReqWhrCondition makeQnReqWhrCondi(QunarGroupInfo qnGpInfo,List<InsureRequestItem> listIsReqItm){
    	QunarReqWhrCondition  qnReqWhrCondit = new QunarReqWhrCondition();
    	String applySeqList=null;
    	
    	qnReqWhrCondit.setOrderNo(qnGpInfo.getOrderNo());
    	
    	for(int i=0;i<listIsReqItm.size();i++){
    		applySeqList += listIsReqItm.get(i).getApplySeq();
    		if(i<(listIsReqItm.size() - 1)){
    			applySeqList += ",";
    		}else{
    			continue;
    		}
    	}
    	applySeqList = "("+applySeqList+")" ;
    	qnReqWhrCondit.setApplySeqList(applySeqList);
    	return qnReqWhrCondit;
    }
    
    /**
     * 获取已确定购保的投保申请（一次二次核保都有的投保申请）
     * @param orderNo 订单号
     * @return 已确定购保的投保申请的信息
     */  
    public List<InsureRequestFeedback> getisCfmedProposal(String orderNo){
    	List<InsureRequestFeedback> listisReqFb = isReqItmDao.getCfmedReqItmByOrderNo(orderNo);
    	return listisReqFb;
    }
    
    /**
     * 更新状态－核保返回
     */
    public void updateStatu(InsureRequestItem insureRequestItem) {
		isReqItmDao.updateStatu(insureRequestItem);
	}
    
    /**
     * 返回去哪儿网订单下每个购保请求的状态
     * @param orderNo 订单号
     * @return 去哪儿网订单下每个购保请求的信息
     */  
    public List<QunarOrderStatu> getQunarOrderStatu(String orderNo){
    	List<QunarOrderStatu> listOrderStatu = new ArrayList<QunarOrderStatu>();
    	listOrderStatu = isReqHdDao.getQnOrderStatu(orderNo);
    	return listOrderStatu;
    }
    
    /**
     * 根据订单号查询核保记录
     * @param orderNo
     * @return
     */
    public List<InsureRequestFeedback> getUWRecordes(String orderNo){
        List<InsureRequestFeedback> listisReqFb = isReqItmDao.getReqItmByOrderNo(orderNo);
        return listisReqFb;
    }
    
    /**
     * 更新 cfmFlag
     * @param cfmFlag
     * @param orderNo
     * @param applySeqs
     */
    public void updateCfmFlagByOrderNoAndSeq(String cfmFlag, String orderNo, List<String> applySeqs){
        isReqItmDao.updateCfmFlagByOrderNoAndSeq(cfmFlag, orderNo, applySeqs);
    }
    
    /**
     * 保存退保的请求 header
     * @param header
     * @return
     */
    public long saveWithdrawApplyHeader(InsureCancelApplyHeader header){
        long id = isCnlApplyHeaderDao.insert(header);
        return id;
    }
    
    /**
     * 批量保存确认退保请求的 item 信息
     * @param items
     */
    public void saveWithdrawApplyItems(List<InsureCancelApplyItem> items){
        isCnlApplyItemDao.insert(items);
    }
    
    /**
     * 保存确认契撤的 header
     * @param header
     * @return
     */
    public long saveWithdrawConfirmHeader(InsureCancelConfirmHeader header){
        long id = isCnlCfmHeaderDao.insert(header);
        return id;
    }
    
    /**
     * 批量保存确认契撤的 item
     * @param items
     */
    public void saveWithdrawConfirmItems(List<InsureCancelConfirmItem> items){
        isCnlCfmItemDao.insert(items);
    }
}
