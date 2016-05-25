/**
 * 
 */
package com.douins.agency.service.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** 
* @ClassName: FileLoader 
* @Description: 文本文件加载器
* @author G. F. 
* @date 2015年12月30日 下午6:39:01 
*  
*/
public class FileLoader {
    /**
     * 从指定路径加载文件
     * @param filepath
     * @return
     */
    public static String readerFile(String filepath){
        StringBuffer sb=new StringBuffer();
        String tempstr=null;

        try
        {   
            FileInputStream fis = new FileInputStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while((tempstr=br.readLine())!=null)
                sb.append(tempstr);
            
            br.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getStackTrace());
        }
        
        return sb.toString();
    }
}
