package com.mango.fortune.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  ReadConfig{
	private static final Logger logger = LoggerFactory.getLogger(ReadConfig.class);
	
	private static Properties config = null;
	static {
		if(config == null)
			config = init("config/config.properties");
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
}
