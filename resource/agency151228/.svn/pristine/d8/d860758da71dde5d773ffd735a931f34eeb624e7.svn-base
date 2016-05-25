

import java.math.BigDecimal;
import java.util.regex.*;

public class amountConversionTest {

	
	public static void main(String[] args) {
		//利安 核保请求
		String xml="<?xml version='1.0' encoding='UTF-8' standalone='yes'?><PackageList><Package><Header>"+
      "<Asyn>0</Asyn><PartnerIdentifier>dyjf</PartnerIdentifier><Code>10002</Code><Time>2016-04-28 14:54:12</Time>"+
      "<UUID>f45ce398-f2fe-471d-a913-06415621d63b</UUID></Header><Request>"+
      "<UnderwirtingList><UnderwirtingItem><ApplyInfo><AgentNum>8631000007</AgentNum><Applicant><CellPhoneNumber>13701234567</CellPhoneNumber>"+
              "<Email>123@321.com</Email><ID>330702197101164119</ID><IDType>0</IDType>Name>朱加禄</Name>"+
              "<Sex>1</Sex></Applicant><ApplyDate>2016-04-28 14:54:12</ApplyDate><ApplyType>1</ApplyType>"+
            "<ChannelNum>1701</ChannelNum><ChannelReginNum>dyjf</ChannelReginNum><ChannelType>01</ChannelType><EffectiveDate>2016-04-28 14:54:12</EffectiveDate>"+
            "<InsuredInfo><InsurantList> <Insurant><CellPhoneNumber>13701234567</CellPhoneNumber><Email>123@321.com</Email>"+
                  "<ID>330702197101164119</ID><IDType>0</IDType><InsurantApplicantRelation>00</InsurantApplicantRelation><Name>朱加禄</Name>"+
                  "<Sex>1</Sex></Insurant></InsurantList><IsApplicant>1</IsApplicant>"+
            "</InsuredInfo><ProductList><ProductInfo><AfterDayEffective>1</AfterDayEffective><CalculationRule>3</CalculationRule>"+
                "<CoverPeriodType>Y</CoverPeriodType><EffectiveDate>2016-04-28 14:54:12</EffectiveDate><InsuranceCategory>Y</InsuranceCategory>"+
                "<InsuranceCoverage>1000</InsuranceCoverage><InsuranceNum>1</InsuranceNum><InsurancePeriod>1</InsurancePeriod>"+
               "<IsPackage>1</IsPackage><PeriodPremium>1000</PeriodPremium><PremPeriodType>Y</PremPeriodType>"+
                "<ProductCode>TXDY0001</ProductCode></ProductInfo></ProductList>"+
            "<TotalPremium>1000</TotalPremium></ApplyInfo><OrderInfo><OrderId>00160428145412189286</OrderId>"+
          "</OrderInfo><OtherInfo><ConIndProxyNO>dyjf001</ConIndProxyNO><InsureNO>1</InsureNO><Plat>YL</Plat></OtherInfo></UnderwirtingItem>"+
      "</UnderwirtingList></Request></Package></PackageList>";
		
		// 承保响应信息
		String insureXML="<PackageList><Package><Header><Cost/><Time>2016-04-28 18:13:22</Time><UUID>aa51b04d-ffce-4ff9-8bef-1ae9fa14d8ec</UUID><ResponseCode>0</ResponseCode><Code>20002</Code><AckUUID>03b412ea-d68a-49ce-89bf-4b762f812b24</AckUUID></Header><Response><PolicyInfoList><PolicyInfoItem><IsSuccess>1</IsSuccess><OrderID>1300000999999985ok06</OrderID><PolicyNo>931500000102039A</PolicyNo><PolicyNoList><PolicyNoItem><ApplyNo>1320160000114779A</ApplyNo><SinglPolicyNo>931500000102039A</SinglPolicyNo></PolicyNoItem></PolicyNoList><IDNO>120000197207182454</IDNO><TotalPremium>100000000</TotalPremium><AccountDate>2012-02-28 10:44:07.0</AccountDate><ApplicantName/></PolicyInfoItem></PolicyInfoList></Response></Package></PackageList>";
		
		//String resultXml=amountConversion(xml,new String[]{"InsuranceCoverage","PeriodPremium","TotalPremium"},1);
		String	 reString=amountConversion(insureXML,new String[]{"TotalPremium"},2);
		
		//System.out.println("resultXML-----:"+resultXml);
		System.out.println("resultXML-----:"+reString);
	
	}

	private static String amountConversion(String xml, String[] match, int type) {

		
		String result = "";
		String resultXml = xml;
		String orgain = "";

		for (int i = 0; i < match.length; i++) {
			String partner=match[i];
			
			String regex = "<" + partner + ">([^<]+)</" + partner + ">";
			Matcher m = Pattern.compile(regex).matcher(resultXml);
			if (m.find()) {
				String price = m.group(1);
				orgain="<"+partner+">"+price+"</"+partner+">";
				BigDecimal b = new BigDecimal(price);// 待转换的金额

				// 元转分 multiply
				if (type == 1) {
					result = "<" + partner + ">" + b.multiply(new BigDecimal("100")).toString() + "</" + partner+ ">";
					// 分转元 divide 保留两位小数
				} else if (type == 2) {
					result = "<" + partner + ">"+ b.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "</"+ partner + ">";
				}
				resultXml = resultXml.replace(orgain, result);
			}
		}
		return resultXml;
	}
private static String amountConversion(String xml,String match,int type){
		
		String price="";
		String result="";
		String orgain="";
		String regex="<"+match+">([^<]+)</"+match+">";
		
		Matcher m = Pattern.compile(regex).matcher(xml);
		
		if (m.find()){
			price = m.group(1);
			orgain="<"+match+">"+price+"</"+match+">";
		}
		
		BigDecimal b=new BigDecimal(price);//待转换的金额
		
		//元转分 multiply
		if(type==1){
			result="<"+match+">"+b.multiply(new BigDecimal("100")).toString()+"</"+match+">";
			
		//分转元	divide 保留两位小数
		}else if(type==2){
			result="<"+match+">"+b.divide(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP).toString()+"</"+match+">";
		}
		
		String resultXml=xml.replace(orgain, result);
		
		
		return resultXml;
	}
	
}
