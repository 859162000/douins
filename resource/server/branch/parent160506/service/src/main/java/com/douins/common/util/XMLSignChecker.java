package com.douins.common.util;

import java.security.PublicKey;

import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLSignChecker {
	/**
	 * XML签名验证
	 * @param doc XML文档
	 * @param pubKey 公钥
	 * @return 验证结果
	 */
	public static boolean validate(Document doc, PublicKey pubKey) {
	    XMLSignatureFactory signFactory = XMLSignatureFactory.getInstance("DOM");
		//获取签名节点
		NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
		if (nl.getLength() == 0) {
			throw new RuntimeException("Cannot find Signature element");
		}
		try{
			Node signatureNode = nl.item(0);
			XMLSignature signature = signFactory.unmarshalXMLSignature(new DOMStructure(signatureNode));
			//生成验证上下文
			DOMValidateContext valCtx = new DOMValidateContext(pubKey, signatureNode);
			
			//验证签名
			boolean coreValidity = signature.validate(valCtx);
			return coreValidity;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
