package com.douins.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * XML文档辅助器
 * @author Gao.Jingxin
 * @version 2015-4-4
 */
public class XMLDocumentHelper {
	
	private DocumentBuilderFactory docBuilderFactory;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	
	public XMLDocumentHelper() {
		try {
			docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(true);
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取XML文档对象
	 * @param file XML文件
	 * @return XML文档对象
	 */
	public Document getDocument(File file){
		try{
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			return docBuilder.parse(file);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取XML文档对象
	 * @param data XML数据
	 * @return XML文档对象
	 */
	public Document getDocument(byte[] data){
		try{
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			return docBuilder.parse(new InputSource(new ByteArrayInputStream(data)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取XML文档对象
	 * @param in XML输入流
	 * @return XML文档对象
	 */
	public Document getDocument(InputStream in){
		try{
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			return docBuilder.parse(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 转换XML文档到输出流
	 * @param doc XML文档对象
	 * @param out 输出流
	 */
	public void transform(Document doc, OutputStream out) {
		try{
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, new StreamResult(out));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 转换XML文档为字节数组
	 * @param doc XML文档对象
	 * @return XML数据
	 */
	public byte[] transformToByteArray(Document doc) {
		try{
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			transform(doc, bo);
			return bo.toByteArray();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 转换XML文档到文件
	 * @param doc XML文档对象
	 * @param file XML文件
	 */
	public void transformToFile(Document doc, File file) {
		try{
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, new StreamResult(file));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 转成字符串形式
	 * @param doc
	 * @return
	 */
	public String transform2String(Document doc){
	    StringWriter sw = new StringWriter();  
	    try {
	        DOMSource source = new DOMSource(doc);
	        StreamResult xmlResult = new StreamResult(sw);  
	  
	        transformer.transform(source, xmlResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	    return sw.toString();
	}
}
