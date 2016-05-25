package com.douins.rest.test;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.douins.account.domain.model.User;
import com.douins.account.service.UserService;
import com.douins.account.service.iml.UserServiceImpl;
import com.douins.bank.domain.model.nybc.RegistAccountResponseBody;
import com.douins.bank.domain.vo.RegistAccountResponseVo;
import com.douins.bank.service.iml.NYBankService;
import com.douins.common.rsa.Base64Utils;
import com.douins.common.rsa.MD5Utils;
import com.douins.common.util.Const;
import com.douins.common.util.JsonOnlineUtils;
import com.douins.common.util.ResponseUtils;
import com.douins.trans.domain.enums.ResponseCode;
import com.douins.trans.domain.vo.RequestTransVo;
import com.douins.trans.domain.vo.ResponseTransVo;
import com.mango.framework.controller.BasicController;

@Controller("testBank")
@RequestMapping("/test/")
public class BankController extends BasicController{

    
    @Resource
    private NYBankService nYBankService;
    @Inject
    private UserServiceImpl userService;
    
    /**
     * 银行的API请求
     * 
     * @param request
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping("bank")
    public String api(HttpServletRequest request, String content) {
        try{
            content = StringEscapeUtils.unescapeHtml4(content);
            nYBankService.getIp();
            return  nYBankService.send(content);
        }catch(Exception e){
            return "出错了："+ e.getMessage();
        }
    }
    
    @ResponseBody
    @RequestMapping("h5/sign")
    public String sign4h5(HttpServletRequest request, String content) {
        try{
//            content = StringEscapeUtils.unescapeHtml4(content);
            if(StringUtils.isBlank(content)){
                return "";
            }
            String md5Val = MD5Utils.getMd5(content);
            // 私钥加签
            String sign = nYBankService.getSignPrivKey(md5Val);
            return  sign;
        }catch(Exception e){
            return "出错了："+ e.getMessage();
        }
    }
    
    /**
     * 网页测试开户
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("account/regist")
    public String registAcc(HttpServletRequest request){
        ResponseTransVo< RegistAccountResponseBody> responseTransVo = new ResponseTransVo<RegistAccountResponseBody>();
        String transId = null;
        ResponseCode responseCode = ResponseCode.FAILED;
        String bank = null;
        try {
            String data = request.getParameter("data");
            String token = JSON.parseObject(data).getString(Const.VERIFY_TOKEN); 
            bank = JSON.parseObject(data).getString("bankCode"); 
           RequestTransVo<User> requestTrans = JsonOnlineUtils.readJson2Entity2(data, User.class);
           transId = requestTrans.getRequestTrans().getTransId();
           User user = requestTrans.getRequestParams();
           userService.getOpenAccountInfo(token, user);
           RegistAccountResponseVo responseVo = nYBankService.openAccount(user);    // 注册返回的数据
           responseTransVo.setResponseParams(responseVo.getBody());
           responseCode = responseVo.getResponseCode();
        } catch (Exception e) {
            logger.error("开设资金账户错误");
        }
        
        String result = ResponseUtils.getResponse(responseTransVo, responseCode, transId) +"\r\nbank= " + bank;
        return result;
    }
    
    
    private String genUid(){
        Random random = new Random(new Date().getTime());
        long uid = random.nextLong();
        return new Long(uid).toString();
    }
}
