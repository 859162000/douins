/**
 * 
 */
package com.douins.bank.service.iml;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.apache.tools.ant.taskdefs.MacroDef;
import org.junit.Test;

import com.douins.AbstractTest;
import com.douins.account.domain.model.User;
import com.douins.account.domain.model.UserAccount;
import com.douins.account.domain.vo.UserInfoVo;
import com.douins.account.domain.vo.UserStatusInfoVo;
import com.douins.account.service.iml.UserServiceImpl;
import com.douins.bank.domain.model.nybc.GateWayUrl;
import com.douins.bank.domain.model.nybc.ProjecteRegistRequestBody;
import com.douins.bank.domain.model.nybc.VerifyIdentityResponse;
import com.douins.common.rsa.RSAUtils;
import com.douins.common.util.ReadConfig;
import com.mango.exception.DataBaseAccessException;
import com.thoughtworks.xstream.XStream;

/** 
* @ClassName: NYBankServiceTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月6日 上午9:29:52 
*  
*/
public class NYBankServiceTest extends AbstractTest {

    @Inject
    private NYBankService nyService;
    @Inject
    private UserServiceImpl userService;
    
    @Test
    public void test() {
    	//企业账户＝96000127005010006
//    	String token = "38ebafcea14a4285af960723bb5a9c27";	
//    	userService.getUserStatusInfo(token);
        // 开户
//        User user = userService.getUser(token);
//        nyService.verifyIdentity(user);
//        nyService.queryAccount(token);
        
        ProjecteRegistRequestBody requestBody =new ProjecteRegistRequestBody();
        requestBody.setName("测试one");
        requestBody.setDesc("....");
        requestBody.setSize("100000000");
        requestBody.setStatus("0");
        requestBody.setCode("DY000001");
        nyService.projecteRegist(requestBody);
        
//        user.setCertiCode("410726198911236626");
//        user.setUserName("侯海萍");
//        nyService.openAccount(user);
//        System.out.println("bank = " + nyService.loadBankPublicKey());
//        System.out.println("dy = " + nyService.loadPrivateKey());
        
//        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><SDOROOT><SYS_HEAD><CONSUMER_SEQ_NO>0101062015121066900100</CONSUMER_SEQ_NO><SERVICE_CODE>PTP1001</SERVICE_CODE><SERVICE_SCENE>01</SERVICE_SCENE><TRAN_DATE>20151210</TRAN_DATE><TRAN_TIMESTAMP>170832</TRAN_TIMESTAMP><SIGN_MSG>ZfAlXT/z0PkUiTfr2eVWHPja5Mscye0x1ibxgj474q7A2TcRyBdAQxNhRuwAtCjsWHRl/FfFpJPD/F60lMlPLQ4xSm+h3FvKDmUp+bKlgRj3n0M+n7kannletXmxUFi0qeV2o7XQwq1ysqGz0uujDGx4vPMKq1tiASGuRx6jC8M=</SIGN_MSG></SYS_HEAD><APP_HEAD><CHANNEL_CODE>PTP0000005</CHANNEL_CODE></APP_HEAD><BODY><ERROR_CODE>0000</ERROR_CODE><ERROR_MSG>核查通过</ERROR_MSG><CHECK_FLAG>0</CHECK_FLAG></BODY></SDOROOT>";
//       String sign = "ZfAlXT/z0PkUiTfr2eVWHPja5Mscye0x1ibxgj474q7A2TcRyBdAQxNhRuwAtCjsWHRl/FfFpJPD/F60lMlPLQ4xSm+h3FvKDmUp+bKlgRj3n0M+n7kannletXmxUFi0qeV2o7XQwq1ysqGz0uujDGx4vPMKq1tiASGuRx6jC8M=";
//        String sign2 = "Tgax5CIRXmMo258K1cTQekp6l3Qhq Sm9PylOU3B/cZsoqMkKtyrEbGzepLN5O4dS6DkkzxWreiQ9x5og2sOxmlMYiJ8UEOtjRnoIgqW4 0gope1 KOzlSZcvRlvZlsoyAxUpgFbMGaVIWd3sG/30gv/cF1fWesBXFePKIOWX6k=";
//        User user = new User();
//        user.setUserId("110");
//        user.setCertiType("0");
//        user.setCertiCode("4391571-");
//        user.setUserName("你大爷");
//        user.setUserMobile("13312497399");
//        user.setStatus("2");
        
//        String transId = "31794043175";
        //nyService.verifyIdentity(user, "39517-43");
        //nyService.openAccount(user, transId);
        //nyService.queryAccount(user, transId);
//        String sign2 = nyService.signForRequest("test|");
//        System.out.println(sign2);
//        XStream xStream= new XStream();
//        xStream.processAnnotations(VerifyIdentityResponse.class);
//        VerifyIdentityResponse response = (VerifyIdentityResponse)xStream.fromXML(xml);
        //System.out.println(GateWayUrl.getUrlBuyH5());
        
//        UserStatusInfoVo vo = new UserStatusInfoVo(user, new UserAccount());
//        System.out.println(vo.getGateWayUrl().getUrlBuyH5());
        //nyService.getSignPrivKey("PTP0000005|7e9e9e80-a59d-44a6-95c3-cdb4b9f0665e|http%3%24319jfd");
        
//        String teString = "errorCode=0000&errorMsg=修改密码成功&flowNo=&signMsg=Tgax5CIRXmMo258K1cTQekp6l3Qhq Sm9PylOU3B/cZsoqMkKtyrEbGzepLN5O4dS6DkkzxWreiQ9x5og2sOxmlMYiJ8UEOtjRnoIgqW4 0gope1 KOzlSZcvRlvZlsoyAxUpgFbMGaVIWd3sG/30gv/cF1fWesBXFePKIOWX6k=";
//        nyService.analyzeGatewayResponse(teString);
        
//        String temp = nyService.getSignPrivKey("PTP000005|0000|错误||");
//        System.out.println(temp);
        
//        System.out.println(sign.length());
//        byte[] signBytes = Base64.decodeBase64(sign.getBytes());
//        try {
//            String md5Val = new String(RSAUtils.decryptByPublicKey(signBytes, nyService.loadBankPublicKey()), "UTF-8");
//            System.out.println(md5Val);
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
//        try {
//           if(sign2.contains(" ")){
//               System.out.println("OK");
//               
//           }
//           String sign3 = sign2.replace(" ", "+");
//            byte[] signBytes = Base64.decodeBase64(sign3.getBytes());
//            String md5Val = new String(RSAUtils.decryptByPublicKey(signBytes, nyService.loadBankPublicKey()), "UTF-8");
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
//        String content = readerFile("/Users/gaofu/Desktop/02.txt");
//        System.out.println(content);
//        nyService.analyzeGatewayResponse(content, "273f3b2d1733481f8684afc5427f2a67");
//    }
//
//    private String readerFile(String filepath){
//        StringBuffer sb=new StringBuffer();
//        String tempstr=null;
//        try
//        {   
//            FileInputStream fis=new FileInputStream(filepath);
//            BufferedReader br=new BufferedReader(new InputStreamReader(fis));
//            while((tempstr=br.readLine())!=null)
//                sb.append(tempstr);
//        }
//        catch(Exception ex)
//        {
//            System.out.println(ex.getStackTrace());
//        }
//        
//        return sb.toString();
   }
}
