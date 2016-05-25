/**
 * 
 */
package com.douins.agency.service.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LifeHttpsTest {
    private static void testPost(String sendxml) {
        try {
            String u="https://ywtest.ccic-net.com.cn:8912/newlife";
            System.setProperty("javax.net.ssl.trustStore", "/Users/gaofu/Desktop/caTest2.jks");    
            System.setProperty("javax.net.ssl.trustStorePassword","123456");    
            System.setProperty("javax.net.ssl.keyStoreType","JKS");    
            System.setProperty("javax.net.ssl.keyStore","/Users/gaofu/Desktop/caTest2.jks") ;    
            System.setProperty("javax.net.ssl.keyStorePassword","123456") ;
            URL url = new URL(u);
            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            http.setDoOutput(true);  
            http.setDoInput(true);
            http.setRequestProperty("GW_CH_CODE", "310015");//渠道代码
            http.setRequestProperty("GW_CH_TX", "0011");//交易代码
            http.setRequestMethod("POST");  
            OutputStreamWriter out = new OutputStreamWriter(http.getOutputStream());    
            String xmlInfo = sendxml;
//            System.out.println("xmlInfo=" + xmlInfo);
            out.write(xmlInfo);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(http
                    .getInputStream()));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getXmlInfo() {
        //测试报文，换成相应渠道方报文。
        String lifeTest=
            "<?xml version=\"1.0\" encoding=\"GBK\"?>  <root>\n" +
            "    <addressList/>\n" + 
            "    <applicant>\n" + 
            "      <account/>\n" + 
            "      <accountNo/>\n" + 
            "      <age/>\n" + 
            "      <air>\n" + 
            "        <airLineOrderNo/>\n" + 
            "        <airlineNo/>\n" + 
            "        <buyTime/>\n" + 
            "        <destAirport/>\n" + 
            "        <eticketNo/>\n" + 
            "        <flightDate/>\n" + 
            "        <flightNumber/>\n" + 
            "        <flightTime/>\n" + 
            "        <iataNo/>\n" + 
            "        <insureNo/>\n" + 
            "        <operateTime/>\n" + 
            "        <operator/>\n" + 
            "        <originAirport/>\n" + 
            "        <returnFlightDate/>\n" + 
            "        <returnFlightNumber/>\n" + 
            "      </air>\n" + 
            "      <appliAddress/>\n" + 
            "      <appliCode/>\n" + 
            "      <appliName>1111111</appliName>\n" + 
            "      <appliNature/>\n" + 
            "      <appliType/>\n" + 
            "      <bank/>\n" + 
            "      <birthDay/>\n" + 
            "      <businessSource/>\n" + 
            "      <construct>\n" + 
            "        <buildingUnit/>\n" + 
            "        <constructAddress/>\n" + 
            "        <constructName/>\n" + 
            "        <constructStruct/>\n" + 
            "        <constructType/>\n" + 
            "        <constructUnit/>\n" + 
            "        <constructValue/>\n" + 
            "        <constructionArea/>\n" + 
            "        <designUnit/>\n" + 
            "        <premiumMode/>\n" + 
            "        <superVisionUnit/>\n" + 
            "      </construct>\n" + 
            "      <email/>\n" + 
            "      <height/>\n" + 
            "      <identifyNumber>420525198701143418</identifyNumber>\n" + 
            "      <identifyPeriod/>\n" + 
            "      <identifyType>01</identifyType>\n" + 
            "      <insuredIdentity>01</insuredIdentity>\n" + 
            "      <insureds>\n" + 
            "        <insured>\n" + 
            "          <age/>\n" + 
            "          <appliIdentity/>\n" + 
            "          <beneficiaries/>\n" + 
            "          <birthDay/>\n" + 
            "          <email/>\n" + 
            "          <height/>\n" + 
            "          <identifyNumber>420525198701143418</identifyNumber>\n" + 
            "          <identifyPeriod/>\n" + 
            "          <identifyType>01</identifyType>\n" + 
            "          <insuredAddress/>\n" + 
            "          <insuredCode/>\n" + 
            "          <insuredName>1111111</insuredName>\n" + 
            "          <mobile/>\n" + 
            "          <nationality/>\n" + 
            "          <occupationCode/>\n" + 
            "          <occupationType/>\n" + 
            "          <phoneNumber/>\n" + 
            "          <postCode/>\n" + 
            "          <sex>1</sex>\n" + 
            "          <unit/>\n" + 
            "          <weight/>\n" + 
            "        </insured>\n" + 
            "      </insureds>\n" + 
            "      <itemCar>\n" + 
            "        <engineNo/>\n" + 
            "        <frameNo/>\n" + 
            "        <licenseNo/>\n" + 
            "        <seatCount/>\n" + 
            "        <useNatureCode/>\n" + 
            "        <useNatureName/>\n" + 
            "      </itemCar>\n" + 
            "      <mainLoan>\n" + 
            "        <loanAmount/>\n" + 
            "        <loanBankCode/>\n" + 
            "        <loanCertificateNo/>\n" + 
            "        <loanContractNo/>\n" + 
            "        <loanDate/>\n" + 
            "        <loanUsage/>\n" + 
            "        <repaidDate/>\n" + 
            "        <repaidType/>\n" + 
            "      </mainLoan>\n" + 
            "      <mobile/>\n" + 
            "      <nationality/>\n" + 
            "      <occupationCode/>\n" + 
            "      <occupationType/>\n" + 
            "      <phoneNumber/>\n" + 
            "      <postCode/>\n" + 
            "      <rations/>\n" + 
            "      <sex>1</sex>\n" + 
            "      <socialSecurityCardNo/>\n" + 
            "      <travel>\n" + 
            "        <travelCountryCode/>\n" + 
            "        <travelCountryName/>\n" + 
            "        <travelGroupNo/>\n" + 
            "        <travelLine/>\n" + 
            "      </travel>\n" + 
            "      <unit/>\n" + 
            "      <unitType/>\n" + 
            "      <weight/>\n" + 
            "    </applicant>\n" + 
            "    <channel>\n" + 
            "      <channelAreaCode/>\n" + 
            "      <channelAreaName/>\n" + 
            "      <channelCode>310015</channelCode>\n" + 
            "      <channelComCode>310015</channelComCode>\n" + 
            "      <channelComName/>\n" + 
            "      <channelName/>\n" + 
            "      <channelOperateCode/>\n" + 
            "      <channelProductCode>EFX0140005</channelProductCode>\n" + 
            "      <channelRelationNo/>\n" + 
            "      <channelTradeCode>0011</channelTradeCode>\n" + 
            "      <channelTradeDate>2014-04-22 11:27:17</channelTradeDate>\n" + 
            "      <channelTradeSerialNo>1398137237062</channelTradeSerialNo>\n" + 
            "      <insurerCode>ccic</insurerCode>\n" + 
            "      <password/>\n" + 
            "      <sourceType>0</sourceType>\n" + 
            "      <userCode>ningbo</userCode>\n" + 
            "    </channel>\n" + 
            "    <engageList/>\n" + 
            "    <itemKindList>\n" + 
            "      <itemKind>\n" + 
            "        <amount>300000</amount>\n" + 
            "        <clauseCode>11100013</clauseCode>\n" + 
            "        <clauseName>大地通达公共交通工具意外伤害保险条款</clauseName>\n" + 
            "        <currency/>\n" + 
            "        <dayAmount/>\n" + 
            "        <kindCode>100118</kindCode>\n" + 
            "        <kindName>公共交通意外伤害(含烧烫伤)-飞机</kindName>\n" + 
            "        <premium>3</premium>\n" + 
            "        <rate/>\n" + 
            "        <subDay>0</subDay>\n" + 
            "      </itemKind>\n" + 
            "      <itemKind>\n" + 
            "        <amount>50000</amount>\n" + 
            "        <clauseCode>11100013</clauseCode>\n" + 
            "        <clauseName>大地通达公共交通工具意外伤害保险条款</clauseName>\n" + 
            "        <currency/>\n" + 
            "        <dayAmount/>\n" + 
            "        <kindCode>100119</kindCode>\n" + 
            "        <kindName>公共交通意外伤害(含烧烫伤)-轮船</kindName>\n" + 
            "        <premium>2</premium>\n" + 
            "        <rate/>\n" + 
            "        <subDay>0</subDay>\n" + 
            "      </itemKind>\n" + 
            "      <itemKind>\n" + 
            "        <amount>50000</amount>\n" + 
            "        <clauseCode>11100013</clauseCode>\n" + 
            "        <clauseName>大地通达公共交通工具意外伤害保险条款</clauseName>\n" + 
            "        <currency/>\n" + 
            "        <dayAmount/>\n" + 
            "        <kindCode>100120</kindCode>\n" + 
            "        <kindName>公共交通意外伤害(含烧烫伤)-火车（地铁、轻轨）</kindName>\n" + 
            "        <premium>2</premium>\n" + 
            "        <rate/>\n" + 
            "        <subDay>0</subDay>\n" + 
            "      </itemKind>\n" + 
            "      <itemKind>\n" + 
            "        <amount>10000</amount>\n" + 
            "        <clauseCode>11100013</clauseCode>\n" + 
            "        <clauseName>大地通达公共交通工具意外伤害保险条款</clauseName>\n" + 
            "        <currency/>\n" + 
            "        <dayAmount/>\n" + 
            "        <kindCode>100121</kindCode>\n" + 
            "        <kindName>公共交通意外伤害(含烧烫伤)-汽车</kindName>\n" + 
            "        <premium>2</premium>\n" + 
            "        <rate/>\n" + 
            "        <subDay>0</subDay>\n" + 
            "      </itemKind>\n" + 
            "      <itemKind>\n" + 
            "        <amount>800</amount>\n" + 
            "        <clauseCode>21200007</clauseCode>\n" + 
            "        <clauseName>附加航班延误保险条款</clauseName>\n" + 
            "        <currency/>\n" + 
            "        <dayAmount/>\n" + 
            "        <kindCode>100136</kindCode>\n" + 
            "        <kindName>航班延误</kindName>\n" + 
            "        <premium>7</premium>\n" + 
            "        <rate/>\n" + 
            "        <subDay>0</subDay>\n" + 
            "      </itemKind>\n" + 
            "    </itemKindList>\n" + 
            "    <main>\n" + 
            "      <argueSolution/>\n" + 
            "      <autoTransRenewFlag>0</autoTransRenewFlag>\n" + 
            "      <businessType>1</businessType>\n" + 
            "      <endDate>2014-05-08</endDate>\n" + 
            "      <endHour>0</endHour>\n" + 
            "      <inputDate>2014-04-22 11:27:17</inputDate>\n" + 
            "      <insuredCount>1</insuredCount>\n" + 
            "      <payMode>1</payMode>\n" + 
            "      <payTimes>1</payTimes>\n" + 
            "      <printNo/>\n" + 
            "      <riskCode>EFX</riskCode>\n" + 
            "      <startDate>2014-05-01</startDate>\n" + 
            "      <startHour>0</startHour>\n" + 
            "      <sumAmount>410800.00</sumAmount>\n" + 
            "      <sumPremium>16</sumPremium>\n" + 
            "      <sumQuantity>1</sumQuantity>\n" + 
            "    </main>\n" + 
            "  </root>";
;                       
        StringBuffer sb = new StringBuffer();
        sb.append(lifeTest);
        return sb.toString();
    }

    public static void main(String[] args) {
        String xml = getXmlInfo();
        testPost(xml);
    }
}
