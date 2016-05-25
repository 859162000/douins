/**
 * 
 */
package com.douins.agency.service.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/** 
* @ClassName: SqeNoUtils 
* @Description: 序列号生成器
* @author G. F. 
* @date 2015年12月31日 上午10:10:24 
*  
*/
public class SeqNoUtils {
    private static final SimpleDateFormat dateFromat = new SimpleDateFormat("yyMMddHHmmss");
    
    /**
     * 生成订单号
     * @param channel      交易双方的通道代码
     * @param transType     交易类型
     * @return
     */
    public static String geneTransNo(String channel, String transType){
        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());
        tmp = tmp % (999999 - 100000 + 1) + 100000;
        StringBuilder strB = new StringBuilder();
        strB.append(channel);
        strB.append(transType);
        strB.append(dateFromat.format(new Date()));
        strB.append(tmp);
        
        return strB.toString();
    }
    
    /**
     * 生成产品编号
     * @param insCode   保险公司代码（两位纯数字）
     * @param insType   险种代码（三位纯数字）
     * @param index       编号（三位纯数字）
     * @return
     */
    public static String geneProductId(String insCode, String insType, int index){
        StringBuilder id = new StringBuilder();
        id.append("DY");
        id.append(insCode);
        id.append(insType);
        id.append(index);
        
        return id.toString();
    }
}
