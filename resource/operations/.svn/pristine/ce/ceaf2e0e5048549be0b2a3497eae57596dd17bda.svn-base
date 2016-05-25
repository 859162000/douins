package com.douins.common.util;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

public class KeyReader {
	/**
	 * 从PKCS12标准存储格式中读取私钥钥，后缀为.pfx文件，该文件中包含私钥
	 * 
	 * @param resourceName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
    public PrivateKey readPrivateKeyfromPKCS12StoredFile(String resourceName,
		String password) throws Exception {
//		InputStream istream = new FileInputStream(resourceName);
	    InputStream istream = KeyReader.class.getClassLoader().getResourceAsStream(resourceName);
		// 使用默认的keyprovider，可能会有问题。
		KeyStore keystore = KeyStore.getInstance("PKCS12");
		char[] a = password.toCharArray() ;
		keystore.load(istream, a);
		Enumeration enumeration = keystore.aliases();
		PrivateKey key = null;
		for (int i = 0; enumeration.hasMoreElements(); i++) {
			if (i >= 1) {
				System.out.println("此文件中含有多个证书!");
			}
			key = (PrivateKey) keystore.getKey(enumeration.nextElement().toString(), password.toCharArray());
			if(key != null)
				return key;
		}
		return key;
	}
	
	/**
	 * Base64编码X.509格式证书文件中读取公钥
	 * 
	 * @param resourceName
	 * @return
	 * @throws Exception
	 */
	public Key fromCerStoredFile(String resourceName) throws Exception {
//		FileInputStream inputStream = new FileInputStream(resourceName);
	    InputStream inputStream = KeyReader.class.getClassLoader().getResourceAsStream(resourceName);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate certificate = cf.generateCertificate(inputStream);
		return (Key) (certificate != null ? certificate.getPublicKey() : null);
	}
}
