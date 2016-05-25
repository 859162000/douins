package dataPerpare;

import java.util.logging.Logger;

import com.douins.agency.service.common.util.HttpClientUtils;
import com.douins.agency.service.common.util.MD5Utils;
import com.douins.agency.service.douins.service.impl.QunarfnLianlifeAdaper;

import sun.tools.tree.ThisExpression;

public class lifeUw {
	public static String url="http://cgw.lianlife.com/UAT/CGW/";
	public static String path ="/Users/hou/Desktop/lifeUw.txt";
	
	
	public static void main(String[] args){
		uwData data = new uwData();
		try {
			String uwxml = data.read(data.uwpath);
			for(int i=0;i<1;i++){
				//核保
				uwxml = data.changUw(uwxml);	
				String fURL = new lifeUw().getFullURL(url, uwxml);
				long time1 = System.currentTimeMillis();
				String responseXml = HttpClientUtils.Post(fURL, uwxml, "utf-8");
				System.out.println("responseXml==="+responseXml);
				long time2 = System.currentTimeMillis();
				String uwTime = (time2-time1)+"";
				String ApplyNo = data.findPoint(responseXml, data.uwPolicyElement).text();
				System.out.println(ApplyNo);
				//承保
				String insureXml = data.read(data.insurePath);
				insureXml = data.changInsure(insureXml, ApplyNo);
				fURL = new lifeUw().getFullURL(url,insureXml);
				long time3 = System.currentTimeMillis();
				String insureResponseXml = HttpClientUtils.Post(fURL, insureXml, "utf-8");
				System.out.println("insureResponseXml==="+insureResponseXml);
				long time4 = System.currentTimeMillis();
				String insureTime = time4-time3+"";
				String policyNo = data.findPoint(insureResponseXml, data.policyNOElement).text();
				//退保
				
				data.write(uwTime+"||"+ApplyNo+"||"+insureTime+"||"+policyNo, path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String getFullURL(String url2,String xml) {
		String orgchkcode_tmp = "12345";
		String partnerIdentify_tmp = "dyjf";
		byte source[]= (orgchkcode_tmp+xml).getBytes();
		url2 = "http://app.lianlife.com/PROXY/CGW_DEV/";
		String xmlMd5 = MD5Utils.lianlifeMD5(source);
		String fUrl =  "http://app.lianlife.com/PROXY/CGW_DEV/"+"Transaction?PARTNER_IDENTIFIER="+"dyjf"+"&CHARSET=UTF-8&TRACE_STATE=Y&sign="+ xmlMd5;
		return fUrl;
	}

}
