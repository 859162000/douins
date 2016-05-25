/**
 * 
 */
package com.douins.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/** 
* @ClassName: DateTimeUtils 
* @Description: 日期、时间格式化工具
* @author G. F. 
* @date 2015年12月31日 下午1:32:02 
*  
*/
public class DateTimeUtils {
    private static final Logger logger = Logger.getLogger(DateTimeUtils.class);
    
    private static final String formate = "yyyyMMdd";
    private static final String formate2 = "HHmmss";
    private static final String dateTimeFormate = "yyyy-MM-dd HH:mm:ss";
    private static final String dateFormate = "yyyy-MM-dd";
    private static final String timeFormate = "HH:mm:ss";
    private static final String dateTimeFormate2 = "yyyyMMddHHmmss";
    
    /**
     * 格式化结果：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formateDateTime(Date date){
        SimpleDateFormat formate = new SimpleDateFormat(dateTimeFormate);
        String result = formate.format(date);
        return result;
    }
    
    /**
     * 格式化结果：yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formateDate(Date date){
        SimpleDateFormat formate = new SimpleDateFormat(dateFormate);
        String result = formate.format(date);
        return result;
    }
    
    /**
     * 格式化结果：yyyyMMdd
     * @param date
     * @return
     */
    public static String formateDate2(Date date){
        SimpleDateFormat form = new SimpleDateFormat(formate);
        String result = form.format(date);
        return result;
    }
    
    /**
     * 格式化结果：HH:mm:ss
     * @param date
     * @return
     */
    public static String formateTime(Date date){
        SimpleDateFormat formate = new SimpleDateFormat(timeFormate);
        String result = formate.format(date);
        return result;
    }
    
    /**
     * 结果：yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formateDate(String date){
        SimpleDateFormat form= new SimpleDateFormat(formate);
        SimpleDateFormat form2 = new SimpleDateFormat(dateFormate);
        String result = null;
        try {
            result = form2.format(form.parse(date));
        } catch (ParseException e) {
           logger.error("convert date error.", e);
        }
        return result;
    }
    
    /**
     * 结果：HH-mm-ss
     * @param date
     * @return
     */
    public static String formateTime(String date){
        SimpleDateFormat form= new SimpleDateFormat(formate2);
        SimpleDateFormat form2 = new SimpleDateFormat(timeFormate);
        String result = null;
        try {
            result = form2.format(form.parse(date));
        } catch (ParseException e) {
            logger.error("convert time error.", e);
        }
        return result;
    }
    
    /**
     * 结果：HHmmss
     * @param date
     * @return
     */
    public static String formateTime2(Date date){
        SimpleDateFormat form= new SimpleDateFormat(formate2);
        String result = form.format(date);
        return result;
    }
    
    /**
     * 格式化结果：yyyyMMddHHmmss
     * @param date
     * @return
     */
    public static String formateDateTime2(Date date){
        SimpleDateFormat formate = new SimpleDateFormat(dateTimeFormate2);
        String result = formate.format(date);
        return result;
    }
    
    /**
     * 格式化结果：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static Date getDateFromString2(String dateString){
        SimpleDateFormat form = new SimpleDateFormat(dateTimeFormate2);
        Date date = null;
        try {
            date = form.parse(dateString);
        } catch (ParseException e) {
            logger.error("convert date error.", e);
        }
        return date;
    }
    //yyyyMMddHHmmss
    public static Date getDateFromString3(String dateString){
        SimpleDateFormat form = new SimpleDateFormat(dateTimeFormate);
        Date date = null;
        try {
            date = form.parse(dateString);
        } catch (ParseException e) {
            logger.error("convert date error.", e);
        }
        return date;
    }
    
    
    /**
     * 从字符串转日期，格式 yyyyMMdd
     * @param dateString
     * @return
     */
    public static Date getDateFromString(String dateString){
        SimpleDateFormat form = new SimpleDateFormat(dateFormate);
        Date date = null;
        try {
            date = form.parse(dateString);
        } catch (ParseException e) {
            logger.error("convert date error.", e);
        }
        return date;
    }
}
