package com.douins.common.util;

import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.w3c.dom.Document;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlUtil {
	private static Logger logger = Logger.getLogger(XmlUtil.class);
	
	public static String xmlSerialize(Object object, Class<?> clazz) {
		XStream xs = new XStream();
		xs.processAnnotations(clazz);
		xs.autodetectAnnotations(true);
		String xml = xs.toXML(object);
		return xml;
	}

	public static <T> T xmlDeserialize(String xml, Class<T> clazz) {
		        T t = null;
		        try {
		            if(xml != null && !StringUtils.isBlank(xml)){
		                XStream xStream = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		                xStream.autodetectAnnotations(true);
		                xStream.processAnnotations(clazz);      // 处理别名映射
		                t= (T)xStream.fromXML(xml);
		            }
		        } catch (Exception e) {
		          logger.error("convert from xml error.", e);
		        }
		        
		        return t;
	}
	
	
	/**
	 * 
	 * @param xml，priKeyPath
	 * @param password
	 * @return 签名后的字符串
	 * @throws Exception 
	 */
	public static String xmlSignature(String xml, String signNode,String expandParam, String priKeyPath, String password) throws Exception{
		StringReader sr = new StringReader(xml);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();

		XMLSigner signer = new XMLSigner();
        signer.init();
      //字符集指定为UTF-8
//      String charset = "UTF-8";
        PrivateKey privateKey = getPrivateKey(priKeyPath,password);
		//读取XML文档
		XMLDocumentHelper docHelper = new XMLDocumentHelper();
		
		Document doc = builder.parse(is);		
		String id = doc.getElementsByTagName(expandParam).item(0).getAttributes().getNamedItem("id").getNodeValue();
		//在Message节点生成签名
		signer.sign(doc, privateKey, id, doc.getElementsByTagName(signNode).item(0));
		
		String xmlstr = docHelper.transform2String(doc);
		
		return xmlstr;
	}
	
    /**
	 * 获取密钥
	 * @param path 路径
	 * @param password 密钥密码
	 * @return 密钥
	 * @throws Throwable
	 */
	public static  PrivateKey getPrivateKey(String path, String password) throws Exception {
		KeyReader reader = new KeyReader();
		return reader.readPrivateKeyfromPKCS12StoredFile(path, password);
	}
	
	
	/**
	 * 读取公钥
	 * @param path 路径
	 * @return
	 * @throws Throwable
	 */
	public static PublicKey getPublicKey(String path) throws Exception{
		KeyReader reader = new KeyReader();
		return (PublicKey) reader.fromCerStoredFile(path);
	}
	
	/**
	 * 签名
	 * @param data 原始数据
	 * @param privateKey 密钥
	 * @return 签名后的数据
	 * @throws Exception
	 */
	public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
		Signature signature = Signature.getInstance("SHA1withRSA");
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}
	
	
	
}
