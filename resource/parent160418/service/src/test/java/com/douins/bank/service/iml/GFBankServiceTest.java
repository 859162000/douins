/**
 * 
 */
package com.douins.bank.service.iml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.security.PublicKey;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.douins.AbstractTest;
import com.douins.account.domain.model.User;
import com.douins.account.service.iml.UserServiceImpl;
import com.douins.bank.domain.enums.BankTransType;

/** 
* @ClassName: GFBankServiceTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2016年1月21日 上午11:09:44 
*  
*/
public class GFBankServiceTest extends AbstractTest {
    @Resource
    private GFBankService service;
    @Resource
    private UserServiceImpl userService;
    
    @Test
    public void test() {
    	String token = "15a2e146efb94a148232906f1cb4958a";
    	String bAccount = "1156311100100000001";//"101001504010008346";
        // 查询开户结果
//       service.queryRegistResult(token);
    	
        // 开户
        User user = userService.getUser(token);
        service.openAccount(user);
        // 充值
//        service.recharge(token, new BigDecimal(10000D)); 
        // 支付
//        service.payment(token, bAccount,new BigDecimal(10000D), BankTransType.PURCHASE,null);
        //撤单
//        service.payment(token, bAccount, new BigDecimal(10000D), BankTransType.REFUND,"20712145586333588878");
   	 // 查询账户信息
//        service.queryAccount(token);
        //账户金额变动
//        String tradeNo = SeqNumUtils.geneTransNo("2", BankType.GFBC.getCode(), BankTransType.RECHARGE.getValue());
//        BigDecimal amount = new BigDecimal("1000");
//        service.saveAmountChange("0e0a332014b14db4b74cb31d512f8e35", amount, BankTransType.RECHARGE.getValue(), tradeNo,null,null,null,null);
     
//        service.updateAmountChange("20710145431220691489");
        
        //查询批量消费记录（暂不用）
//        service.queryConsumeRecords(token);
    	//提现
    	service.withdraw(token, new BigDecimal(1000D));
    	//查询单笔消费记录
//    	service.QuerySingleResults(token, BankTransType.PURCHASE);
        //已签约银行查询
//        service.queryBankNo(token);
    }
}
