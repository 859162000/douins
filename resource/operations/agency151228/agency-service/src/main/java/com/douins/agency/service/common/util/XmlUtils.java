/**
 * 
 */
package com.douins.agency.service.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;

/** 
* @ClassName: XmlUtils 
* @Description: xml 通用处理工具
* @author G. F. 
* @date 2015年12月30日 下午6:28:41 
*  
*/
public class XmlUtils {
    private static Logger logger = Logger.getLogger(XmlUtils.class);
    
    /**
     * 将 xml 结构映射到 java 对象
     * @param xml   xml 结构的数据，字符串形式
     * @param clazz 目标对象的类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T xml2Object(String xml, Class<T> clazz){
        T t = null;
        try {
            if(xml != null && !StringUtils.isBlank(xml)){
                XStream xStream = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
                xStream.processAnnotations(clazz);      // 处理别名映射
                //xStream.autodetectAnnotations(true);
                t= (T)xStream.fromXML(xml);
            }
        } catch (Exception e) {
            logger.error("convert from xml error.", e);
        }
        
        return t;
    }
    
    public static String xmlSerialize(Object object, Class<?> clazz) {
		XStream xs = new XStream();
		xs.processAnnotations(clazz);
		xs.autodetectAnnotations(true);
		String xml = xs.toXML(object);
		return xml;
	}
}
