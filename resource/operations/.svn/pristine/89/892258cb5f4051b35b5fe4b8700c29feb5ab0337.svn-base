package com.douins.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  ReadConfig{
	private static final Logger logger = LoggerFactory.getLogger(ReadConfig.class);
//	private static final String envName = "env";
//    private static final String env;
//    private static String mac;
//    private static String ip;
//    private static String dyPrivKeyFile;
//    private static String dyPubKeyFile;
//    private static String nyPubKeyFile;
//    private static String nybcUrl;
//    private static String nybcIp;
//    private static String httpStr;
//    private static String dyDevDoamin;
//    private static String dyQaDomain;
//    private static String dyOnlineDomain;
    
	private static Properties config = null;
	static {
		if(config == null)
		    config = init("config/config.properties");
//		env = config.getProperty(envName);
//		initParams();
    }
	
	public ReadConfig(){}
	public ReadConfig(String filePath){
		config = init(filePath);
	}

	public static Properties init(String filePath){
		Properties p = null;
		InputStream inputStream = null;
		try{
			p = new Properties();

			inputStream = ReadConfig.class.getClassLoader().getResourceAsStream(
					filePath);
				p.load(inputStream);
		}
		catch(IOException e){
			logger.error("====================config.properties====================",e);
		}finally{
			try{
				if(inputStream!=null){
					inputStream.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
		}
		return p;
	}
	
	public static String get(String key){
		String value =	config.getProperty(key);
		if(value!=null)
			return value.trim();
		return value;
	}
	
//	读取配置文件
	public static String getValue(String proPath,String key)
	{
		InputStream input = ReadConfig.class.getResourceAsStream(proPath);
		try
		{
			Properties ps = new Properties();
			ps.load(input) ;
			String key_str = ps.getProperty(key) ;
			if(key_str!=null)
			{
				return key_str ;
			}
			return null ;
		}
		catch(Exception e)
		{
			return null ;
		}
		finally
		{
			try
			{
				if(input!=null)
				{
					input.close() ;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace() ;
			}
		}
	}

//    private static void initParams(){
//        switch(env){
//        case "dev":
//            setIp(config.getProperty("ipDev"));
//            setMac(config.getProperty("macDev"));
//            setDyPrivKeyFile(config.getProperty("key_path_dy_test"));
//            setNyPubKeyFile(config.getProperty("key_path_nybc_test"));
//            setNybcUrl(config.getProperty("url_nybc_test"));
//            setNybcIp(config.getProperty("url_ip_test"));
//            setHttpStr("http");
//            setDyPubKeyFile(config.getProperty("key_pub_path_dy_test"));
//            setDyDevDoamin(config.getProperty("dy_url_domain_dev"));
//            break;
//        case "qa":
//            setIp(config.getProperty("ipQa"));
//            setMac(config.getProperty("macQa"));
//            setDyPrivKeyFile(config.getProperty("key_path_dy_test"));
//            setNyPubKeyFile(config.getProperty("key_path_nybc_test"));
//            setNybcUrl(config.getProperty("url_nybc_test"));
//            setNybcIp(config.getProperty("url_ip_test"));
//            setHttpStr("http");
//            setDyQaDomain(config.getProperty("dy_url_domain_qa"));
//            break;
//        case "online":
//            setIp(config.getProperty("ipOnline"));
//            setMac(config.getProperty("macOnline"));
//            setDyPrivKeyFile(config.getProperty("key_path_dy_online"));
//            setNyPubKeyFile(config.getProperty("key_path_nybc_online"));
//            setNybcUrl(config.getProperty("url_nybc_online"));
//            setNybcIp(config.getProperty("url_ip_online"));
//            setHttpStr("https");
//            setDyOnlineDomain(config.getProperty("dy_url_domain_online"));
//            break;
//        default:
//            break;
//        }
//    }
//    
//    public static String getMac() {
//        return mac;
//    }
//    public static void setMac(String mac) {
//        ReadConfig.mac = mac;
//    }
//    public static String getIp() {
//        return ip;
//    }
//    public static void setIp(String ip) {
//        ReadConfig.ip = ip;
//    }
//    public static String getDyPrivKeyFile() {
//        return dyPrivKeyFile;
//    }
//    public static void setDyPrivKeyFile(String dyPrivKeyFile) {
//        ReadConfig.dyPrivKeyFile = dyPrivKeyFile;
//    }
//    public static String getNyPubKeyFile() {
//        return nyPubKeyFile;
//    }
//    public static void setNyPubKeyFile(String nyPubKeyFile) {
//        ReadConfig.nyPubKeyFile = nyPubKeyFile;
//    }
//    public static String getEnvname() {
//        return envName;
//    }
//    public static String getNybcUrl() {
//        return nybcUrl;
//    }
//    public static void setNybcUrl(String nybcUrl) {
//        ReadConfig.nybcUrl = nybcUrl;
//    }
//    public static String getNybcIp() {
//        return nybcIp;
//    }
//    public static void setNybcIp(String nybcIp) {
//        ReadConfig.nybcIp = nybcIp;
//    }
//    public static String getHttpStr() {
//        return httpStr;
//    }
//    public static void setHttpStr(String httpStr) {
//        ReadConfig.httpStr = httpStr;
//    }
//    public static String getDyPubKeyFile() {
//        return dyPubKeyFile;
//    }
//    public static void setDyPubKeyFile(String dyPubKeyFile) {
//        ReadConfig.dyPubKeyFile = dyPubKeyFile;
//    }
//    public static String getDyDevDoamin() {
//        return dyDevDoamin;
//    }
//    public static void setDyDevDoamin(String dyDevDoamin) {
//        ReadConfig.dyDevDoamin = dyDevDoamin;
//    }
//    public static String getDyQaDomain() {
//        return dyQaDomain;
//    }
//    public static void setDyQaDomain(String dyQaDomain) {
//        ReadConfig.dyQaDomain = dyQaDomain;
//    }
//    public static String getDyOnlineDomain() {
//        return dyOnlineDomain;
//    }
//    public static void setDyOnlineDomain(String dyOnlineDomain) {
//        ReadConfig.dyOnlineDomain = dyOnlineDomain;
//    }
}
