/**
 * 
 */
package com.douins.apply.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.douins.AbstractTest;
import com.douins.apply.dao.ApplyInfoDao;
import com.douins.apply.domain.model.ApplyInfo;
import com.douins.apply.domain.vo.LoanInfoVo;
import com.douins.apply.service.iml.LoanApplyServiceImpl;

/** 
* @ClassName: ApplyInfoServiceTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月19日 下午4:15:14 
*  
*/
public class ApplyInfoServiceTest extends AbstractTest{

    @Inject
    private ApplyInfoDao infoDao;
    @Inject
    private LoanApplyServiceImpl loanService;
    
    @Test
    public void test() {
        //ApplyInfo info = infoDao.find(1);
        //System.out.println(info.getApplyType());
        try {
            LoanInfoVo vo = loanService.getLoanInfo("6a9cb45dc103429dafb6caa3994a1c3b");
            System.out.println(vo.getCycleList().size());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
