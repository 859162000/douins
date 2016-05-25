/**
 * 
 */
package com.douins.common.util;
import java.security.PrivateKey;
import java.util.Collections;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
/** 
* @ClassName: XMLSigner 
* @Description: xml 签名工具类
* @author G. F. 
* @date 2016年1月21日 下午2:11:04 
*  
*/
public class XMLSigner {
    private String defaultNamespacePrefix = "ds";
    private XMLSignatureFactory signFactory;
    private Transform envelopedTransform;
    private DigestMethod sha1DigMethod;
    private CanonicalizationMethod c14nWithCommentMethod;
    private SignatureMethod rsa_sha1SigMethod;
    
    private boolean isInit = false;
    
    /**
     * XML签名工具初始化
     */
    public synchronized void init() {
        if(isInit){
            throw new RuntimeException("XMLSigner already init !");
        }
        try {
            signFactory = XMLSignatureFactory.getInstance();
            //转换方式指定为 ENVELOPED
            envelopedTransform = signFactory.newTransform(Transform.ENVELOPED,(TransformParameterSpec) null);
            //摘要算法指定为 SHA1
            sha1DigMethod = signFactory.newDigestMethod(DigestMethod.SHA1, null);
            //加密规范指定为INCLUSIVE
            c14nWithCommentMethod = 
                   signFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,(C14NMethodParameterSpec) null);
            //签名算法指定为RSA_SHA1
            rsa_sha1SigMethod = signFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        isInit = true;
    }
    
    /**
     * XML签名
     * <p>签名使用内嵌方式，生成在指定节点</p>
     * @param doc XML文档
     * @param privateKey 私钥
     * @param referenceId 需签名的元素标识
     * @param signNode 生成签名的节点
     */
    public void sign(Document doc, PrivateKey privateKey, String referenceId, Node signNode) {
        if(!isInit){
            throw new RuntimeException("XMLSigner is not init !");
        }
        try{
            //创建 <Reference> 元素，引用指定ID的节点，<Signature> 元素不会被计算在内
            Reference refToRootDoc = signFactory.newReference("#" + referenceId,
                    sha1DigMethod, Collections.singletonList(envelopedTransform), null, null);
            //创建 <SignedInfo> 元素
            SignedInfo signedInfo = signFactory.newSignedInfo(c14nWithCommentMethod,
                    rsa_sha1SigMethod, Collections.singletonList(refToRootDoc));
            //创建签名实例
            XMLSignature signature = signFactory.newXMLSignature(signedInfo, null);
            //创建签名上下文，在指定节点生成
            DOMSignContext dsc = new DOMSignContext(privateKey, signNode);
            //设置签名域命名空间前缀
            if (defaultNamespacePrefix != null) {
                dsc.setDefaultNamespacePrefix(defaultNamespacePrefix);
				Element element = (Element)doc.getElementsByTagName(referenceId).item(0);
				dsc.setIdAttributeNS(element, null, "id");
            }
            //签名
            signature.sign(dsc);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
