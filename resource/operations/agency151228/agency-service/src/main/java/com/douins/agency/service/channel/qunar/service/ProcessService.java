/**
 * 
 */
package com.douins.agency.service.channel.qunar.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.douins.agency.service.channel.qunar.domain.enums.QunarTransType;
import com.douins.agency.service.channel.qunar.domain.vo.InsureApplyVo;
import com.douins.agency.service.channel.qunar.domain.vo.UWStructVo;
import com.douins.agency.service.channel.qunar.domain.vo.WithdrawReqVo;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.douins.service.AdapterServiceIFC;
import com.douins.agency.service.douins.service.ChannelServiceIFC;

/** 
* @ClassName: ProcessService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月30日 下午6:12:29 
*  
*/
@Service(Constants.QUNAR_SERVICE)
public class ProcessService implements ChannelServiceIFC {
    Logger logger = Logger.getLogger(ProcessService.class);
    
    @Resource(name = Constants.QUNAR_CCIC_ADAPTER)
    private AdapterServiceIFC adapterService;
    
    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.ChannelServiceIFC#doUW(java.lang.String)
     */
    public String doUW(String data) throws Exception {
        // TODO Auto-generated method stub
        UWStructVo uwStructVo = ConvertDataService.getUWDatasFromXml(data);         // data 必须是 xml 格式
        // 去核保
        String result = adapterService.doUW(uwStructVo);

        return result;
    }

    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.ChannelServiceIFC#doInsure(java.lang.String)
     */
    public String doInsure(String data) throws Exception {
        // TODO Auto-generated method stub
        InsureApplyVo insApplyVo = ConvertDataService.getInsureApplyDataFromXml(data);      // data 必须是 xml 格式
        // 申请承保
        String result = adapterService.doInsure(insApplyVo);
        
//        String url = "https://ywtest.ccic-net.com.cn:8912/newlife";
//        String result = HttpClientUtils.httpsPostCCIC(url, data, "GBK", CCICTransType.INSURE.getValue());
        return result;
    }

    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.ChannelServiceIFC#doWithdraw(java.lang.String)
     */
    public String doWithdraw(String data) throws Exception {
        // TODO Auto-generated method stub
        WithdrawReqVo reqVo = ConvertDataService.getWithdrawReqDataFromXml(data);
        String result = null;
        if(reqVo.getHeader().getProfileRequest().equals(QunarTransType.NETSELLREVOKECONFIRM.getValue())){
            result = adapterService.doWithdraw(reqVo);
        }else{
            result = adapterService.applyWithdraw(reqVo);
        }
        
//        String url = "https://ywtest.ccic-net.com.cn:8912/newlife";
//        String result = HttpClientUtils.httpsPostCCIC(url, data, "GBK", CCICTransType.CANCEL.getValue());
        return result;
    }

    /* (non-Javadoc)
     * @see com.douins.agency.service.douins.service.ChannelServiceIFC#doBalance(java.lang.String)
     */
    @Override
    public String doBalance(String data) {
        // TODO Auto-generated method stub
//        String url = "https://ywtest.ccic-net.com.cn:8912/newlife";
//        String result = HttpClientUtils.httpsPostCCIC(url, data, "GBK", CCICTransType.BALANCE.getValue());
//        return result;
        return data;
    }

    @Override
    public String doQuery(String data){
    	return data;
    }
    // ********************************** 返回数据解析 ***************************************
    
}
