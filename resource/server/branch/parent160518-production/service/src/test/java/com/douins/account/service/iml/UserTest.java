/**
 * 
 */
package com.douins.account.service.iml;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.cxf.common.i18n.Exception;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.douins.AbstractTest;
import com.douins.account.dao.UserDao;
import com.douins.account.domain.model.MessageRequestVo;
import com.douins.account.domain.model.PublicMessage;
import com.douins.account.domain.model.User;
import com.douins.account.domain.vo.MessageRequest;
import com.douins.account.service.UserService;
import com.douins.common.msgpush.getui.GetuiConfigs;
import com.douins.product.domain.model.Product;
import com.douins.product.domain.vo.ProductResponse;
import com.douins.product.domain.vo.ProductVo;
import com.douins.statics.domain.model.Image;
import com.douins.statics.service.ImageService;
import com.mango.exception.DataBaseAccessException;

/** 
* @ClassName: UserTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年10月22日 下午1:44:33 
*  
*/
public class UserTest extends AbstractTest {
    @Inject
    private UserDao userDao;
    @Inject
    private UserService service;
    @Inject
    private UserAccountServiceImpl accountService;
    
    @Inject
    private PublicMessageService pubMsgService;

    @Test
    public void userTest() throws java.lang.Exception{
//        userDao.add(createUser());
        //service.save("", createUser());
       
//        throw new java.lang.Exception("test");
//        try {
//            service.save(" ", createUser());
//        } catch (DataBaseAccessException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
       //User user = service.findByKey("fh283r");    //userDao.find("fh283r");
        //System.out.println(user.getUserName());
        //service.login(user);
        //service.setClientId("5ea683e11b09485fa75c4ab381ad4503", "test001");
        
        //accountService.findOneByUserId("110");
        
        MessageRequest req = new MessageRequest();
        req.setAmount(10);
        req.setNotificationId(1);
        List<PublicMessage> messages = pubMsgService.getMessages(req);
        if(messages.size() > 0){
            
        }
    }
    
    private User createUser(){
        User user = new User();
        user.setAccountType("1");
        user.setCertiCode("110119");
        user.setCertiType("2");
        user.setCertiValidDate(new Date());
        user.setCity("上海");
        user.setCreateDate(new Date());
        user.setDetailedAdress("黄浦区新桥路68号1807室");
        user.setDistrict("No");
        user.setFetchTime(new Date());
        user.setFetchType("0");
        user.setGesturePassword("123");
        user.setGrade("1");
        user.setId(1L);
        user.setIpAddress("192.168.1.1");
        user.setIsvalid("0");
        user.setLastLoginTime(new Date());
        user.setLoginPassword("123456");
        user.setNickName("m");
        user.setNovice("1");
        user.setOpenId("fahs378");
        user.setOpUser("afh");
        user.setPayPassword("fashdif");
        user.setPrimaryKey("3685");
        user.setProvince("sh");
        user.setRegisterTime(new Date());
        user.setRegisterType("0");
        user.setStatus("0");
        user.setSwitchGesturePassword("1");
        user.setSwitchPayPassword("0");
        user.setUpdateDate(new Date());
        user.setUserAccount("4132");
        user.setUserBirthDay(new Date());
        user.setUserEmail("af@douins.com");
        user.setUserId("fh283r");
        user.setUserMobile("13598743444");
        user.setUserName("你大爷");
        user.setUserSex("2");
        user.setZipcode("237894");
        
        return user;
    }
}
