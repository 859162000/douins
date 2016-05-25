/**
 * 
 */
package com.douins.insurance.service.iml;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.junit.Test;

import com.douins.AbstractTest;
import com.douins.common.util.Const;
import com.douins.policy.domain.model.PolicyMaster;
import com.douins.policy.service.iml.PolicyMasterServiceImpl;
import com.mango.core.common.util.MD5Util;

/** 
* @ClassName: PfLifeServiceImplTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author G. F. 
* @date 2015年12月15日 下午5:14:05 
*  
*/
public class PfLifeServiceImplTest extends AbstractTest {
    @Inject
    private PolicyMasterServiceImpl service;
    @Inject
    private PfLifeServiceImpl pfService;
    
    @Test
    public void test() {
        PolicyMaster policyMaster = service.findByKey("120");
        //pfService.doUW(policyMaster);
        //pfService.doInsure(policyMaster);

//        String test2 = readerFile("/Users/gaofu/Desktop/www.txt");
//        
//        String test3 = "pflifedouins" + test2;
//        byte[] datas;
//        try {
//            datas = test3.getBytes("GBK");
//            System.out.println("length = " + datas.length);
//            String md5 = pfService.getMD5(datas);
//            System.out.println(md5);
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
//        File file=new File("/Users/gaofu/Desktop/www.txt");
//        String value;
//        try {
//            byte[] datas2 = ("pflifedouins" + file).getBytes("GBK");
//            value = pfService.getMD5(datas2);
//            System.out.println(value);
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        String content = readerFile("/Users/gaofu/Desktop/01.txt");
        System.out.println(content);
    }

   private String readerFile(String filepath){
       StringBuffer sb=new StringBuffer();
       String tempstr=null;
       try
       {   
           FileInputStream fis=new FileInputStream(filepath);
           BufferedReader br=new BufferedReader(new InputStreamReader(fis));
           while((tempstr=br.readLine())!=null)
               sb.append(tempstr);
       }
       catch(Exception ex)
       {
           System.out.println(ex.getStackTrace());
       }
       
       return sb.toString();
   }
   
   
  
}
