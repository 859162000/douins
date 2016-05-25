/**
 * 
 */
package com.douins.agency.service.channel.qunar.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.douins.agency.service.AbstractTest;
import com.douins.agency.service.common.Constants;
import com.douins.agency.service.common.util.FileLoader;
import com.douins.agency.service.douins.dao.ProductInfoDao;
import com.douins.agency.service.douins.service.ChannelServiceIFC;
import com.douins.agency.service.douins.service.database.CCICDataService;
import com.douins.agency.service.insurance.ccic.service.CCICDataAnalysis;

/** 
* @ClassName: ProcessServiceTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月30日 下午6:48:19 
*  
*/
public class ProcessServiceTest extends AbstractTest{
    
    @Resource(name=Constants.QUNAR_SERVICE)
    private ChannelServiceIFC service;
    @Resource 
    private CCICDataService dataService;

    @Test
    public void test() {
        String uw = FileLoader.readerFile("/Users/hou/Desktop/uw.txt");
        System.out.println(uw);
////        for(int i = 0; i<2;i++){
//            long start = System.currentTimeMillis();
////            ConvertDataService.getUWDatasFromXml(uw);
//            if(service == null){
//                System.out.println("error");
//            }
//            service.doUW(uw);
//            long end = System.currentTimeMillis(); 
//            System.out.println("耗时："+ (end - start) +" 毫秒");
////        } 
        

        String apply = FileLoader.readerFile("/Users/hou/Desktop/insureApply.txt");
        long start2 = System.currentTimeMillis();
        try {
            ConvertDataService.getInsureApplyDataFromXml(apply);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end2 = System.currentTimeMillis(); 
        System.out.println("耗时2："+ (end2 - start2) +" 毫秒");

        
        CCICDataAnalysis.getInsApplyResponse(uw);
    }

}
