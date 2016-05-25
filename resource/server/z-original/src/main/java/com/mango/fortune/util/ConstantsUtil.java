package com.mango.fortune.util;

import java.text.ParseException;
import java.util.Date;



import org.apache.commons.lang3.StringUtils;

import com.mango.core.common.util.DateUtils;

public class ConstantsUtil {
	public static String SYSUSERID = "0000000000000000000000000000J919";
	public static String LASTMONTHFLAG_LAST = "1";
	public static String LASTMONTHFLAG_NOTLAST = "0";
	public static String AUTOUSERNAME = "AUTORUNNER";
	
	public static boolean isDebug(){
		String debugStr = ReadConfig.get("debug");
		if("true".equals(debugStr)){
			return true;
		}
		return false;
	}
	
	public static Date getLongDate(){
		Date longDate = null;;
		try {
			longDate = DateUtils.stringToDate("9999-01-01", "yyyy-MM-dd");
		} catch (ParseException e) {
			longDate = null;
		}
		return longDate;
	}
	
	public static boolean isTunnelOrLocal(){
		String islocal = ReadConfig.get("tunnel.islocal");
		if(StringUtils.isNotBlank(islocal) 
				&& "false".equals(islocal)){
			return true;//调用tunnel
		}else{
			return false;
		}
	}
}
