package com.douins.rest.bank;

import static org.junit.Assert.*;

import org.junit.Test;

import com.douins.AbstractTest;
import com.douins.account.domain.model.User;
import com.douins.bank.service.iml.GFBankService;
import com.douins.common.util.HttpClientUtils;
import com.douins.common.util.HttpClientUtils2;

public class Banktest extends AbstractTest{
	
	@Test
	public void testName() throws Exception {
		String request ="data:{\"accessToken\":\"5bd311ba6a114ebc9c905f30f3b77ed1\""
	      +",\"requestTrans\": {"
			   + "\"transId\":1453866254623,"
			    +"\"transChannel\":\"01\","
			   +"\"transTime\":1453866254623,"
			   + "\"transType\":\"000\"},"
			  +"\"requestParams\":{"
			    +"\"userName\":\"测试\","
			    +"\"certiCode\":\"310215198212041452\"},"
			+"}";
		
		try {
			 HttpClientUtils2.sendXml_Post_text("http://127.0.0.1:8080/api/bank/sign/url", request, "utf-8");
		} catch (Exception e) {
			System.out.println(e.getStackTrace()+e.getMessage() +e.getCause());
		}
      
 
	}
	
	@Test
	public void testName2() throws Exception {
	    	
	    	User user = new User();
	    	user.setUserName("test");
	    	user.setCertiCode("99008w8e77");
			String tradeNo ="000";
			String vAccountNo="9999";
			
			GFBankService gf =new GFBankService();
			//gf.catSignParamsWap( user, tradeNo, vAccountNo);
	}
	
	@Test
	public void testName3() throws Exception {
		String xml="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"<SDOROOT>"+"	<SYS_HEAD>"+"	<SIGN_MSG>qqlLzExAFg8L/Fxla/2BxHy3LERDZA12NULE7/64iYKp5PTm7jrCuTPS/lq0TJBczBg93nRZ/sFednI/ObzMAFi0UZkH6Qj9WUeHRrlPIqDa1LjC73AEkRlkVg5ut9ckJMSobbjBrjrAGajTgZa4p+VT8PHGXtK5/1jgleXfToc=</SIGN_MSG>"+"	</SYS_HEAD>"+"	<BODY>"+"		<FLOW_NO>0101062016020625637900</FLOW_NO>"+"		<CUST_NO>EC10000285592</CUST_NO>"+"		<CHANL_NO>PTP100000000001</CHANL_NO>"+"		<TRAN_DATE>20160206</TRAN_DATE>"+"		<TRAN_TIME>094152</TRAN_TIME>"+"		<STATUS>03</STATUS>"+"		<RET_CODE>0000</RET_CODE>"+"		<RET_MSG>交易成功</RET_MSG>"+"		<PROJ_CODE>PTP00224</PROJ_CODE>"+"		<PAY_ACCT>663300990000</PAY_ACCT>"+"		<PAY_OPENBRAN>1234</PAY_OPENBRAN>"+"		<PAY_ACCTNAME>324324132</PAY_ACCTNAME>"+"		<RCV_ACCT>3432</RCV_ACCT>"+"		<RCV_ACCTNAME>41</RCV_ACCTNAME>"+"		<RCV_BANK>14</RCV_BANK>"+"		<TRAN_TYPE>14</TRAN_TYPE>"+"		<CURR>CNY</CURR>"+"		<AMT>234</AMT>"+"		<FEE>234</FEE>"+"		<USAGE>324</USAGE>"+"		<PAY_TYPE>4231</PAY_TYPE>"+"		<FREEZE_NO>324</FREEZE_NO>"+"		<PROJ_NAME>324</PROJ_NAME>"+"		<TRAN_TITLE>1</TRAN_TITLE>"+"		<TRAN_DESC>23</TRAN_DESC>"+"		<CHANL_FLOW_NO>43242314</CHANL_FLOW_NO>"+"	</BODY>"+"</SDOROOT>";
		String encode="utf-8";
		System.out.println(HttpClientUtils2.sendXml_Post_text("http://dev.douins.com/api/bank/ny/callback/payinfo", xml, encode));
		
	}

}
