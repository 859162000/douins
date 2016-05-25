/**
 * 
 */
package com.douins.common.msgpush;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.web.servlet.mvc.ServletForwardingController;

import com.douins.AbstractTest;
import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserPrivateMessage;
import com.douins.common.msgpush.getui.GetuiPush;
import com.douins.common.util.InterfaceConfigUtil;
import com.douins.common.util.ResponseUtils;
import com.douins.insurance.service.iml.PfLifeServiceImpl;
import com.douins.partner.domain.model.InsuranceInterfaceConfig;
import com.douins.partner.service.iml.InsuranceInterfaceConfigServiceImpl;
import com.douins.policy.domain.enums.ConfigType;
import com.gexin.rp.sdk.base.IPushResult;
import com.google.common.collect.Lists;

/** 
* @ClassName: GetuiPushTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年11月11日 上午10:12:05 
*  
*/
public class GetuiPushTest extends AbstractTest {
    
    @Inject
    private GetuiPush push;
    
    @Inject
    private InsuranceInterfaceConfigServiceImpl configServiceImpl;
    
    private static String CID = "5f54b13817c84596d7fa5e3f07c41387";
    private static String CID2 = "00b4b5780a65df1fa87d863c6fae45b6";
//    private static String CID3 = "94d8880ff9524bbea01a22a1651cd52e";
//    private static String CID4 = "ca5656ec99e182f0fe9c0eaf71273ae4";
    
    @Test
    public void test() {
        String title = "推送测试";
        String content = "看看能否收得到";
        List<String> cidList = Lists.newArrayList();
        cidList.add(CID);
        cidList.add(CID2);
//        cidList.add(CID3);
//        cidList.add(CID4);
        
        //IPushResult res = push.push2Single(CID, title, content);
        UserPrivateMessage message = new UserPrivateMessage();
        message.setContent(content);
        message.setCreateTime(new Date());
        message.setMsgId(1);
        message.setTitle(title);
        message.setUrl("http://test");
        IPushResult res = push.push2ListPrivMsg(cidList, message);
        System.out.println(res.getResponse().toString());

//        InsuranceInterfaceConfig cfg = new InsuranceInterfaceConfig();
//        cfg.setInsuranceId("754cd44f583841348f4c6ad64e7a12a2");
//        cfg.setConfigType(ConfigType.UW.getValue());
//        cfg = InterfaceConfigUtil.getInsuranceConfig(cfg);
//        System.out.println(cfg.getTransType());
    }

}
