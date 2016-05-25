package com.douins.agency.service.common.util;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

@Component
public class AESUtils {
    private Logger logger = LoggerFactory.getLogger(AESUtils.class);
   
    
 /*   public String encrypt(String content) {
        try {
            SecretKeySpec key = getSecretKeySpec();
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            logger.error("aes 加密失败", e);
        }
        return "";
    }
    
    public String decrypt(String content) {
        try {
            SecretKeySpec key = getSecretKeySpec();
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, Charsets.UTF_8); // 解密
        } catch(Exception e) {
            logger.error("aes 解密失败", e);
        }
        return "";
    }

    private SecretKeySpec getSecretKeySpec() throws NoSuchAlgorithmException {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        return new SecretKeySpec(enCodeFormat, "AES");
    }*/
    private String key = "3/4SFI27F1YwW2iITYG12g==";

    private SecretKeySpec secretKeySpec;

	    public AESUtils() {
	    	init();
	    }
	    public AESUtils(String key) {
	        this.key = key;
	        init();
	    }

    @PostConstruct
    void init() {
        try {
            secretKeySpec = getSecretKeySpec(key);
        } catch (Exception e) {
            logger.error("cannot find ", e);
        }

    }

    public String encrypt(String content) {
        try {
            logger.info("key is{}", key);
            SecretKeySpec key = secretKeySpec;
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            logger.error("aes 加密失败", e);
        }
        return "";
    }

	    public String decrypt(String content) {
	        try {
	            logger.info("key is{}", key);
	            SecretKeySpec key = secretKeySpec;
	            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
	
	        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
	        return new String(result, Charsets.UTF_8); // 加密
	    } catch(Exception e) {
	        logger.error("aes 解密失败", e);
	    }
	    return "";
	}
	
	private static String getEncodeKey(String key) throws NoSuchAlgorithmException{
	    KeyGenerator kgen = KeyGenerator.getInstance("AES");
	    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	    random.setSeed(key.getBytes());
	    kgen.init(128, random);
	    SecretKey secretKey = kgen.generateKey();
	    byte[] enCodeFormat = secretKey.getEncoded();
	    return Base64.encodeBase64String(enCodeFormat);
	}
	
	private static SecretKeySpec getSecretKeySpec(String enCodeKey) throws NoSuchAlgorithmException {
	    return new SecretKeySpec(Base64.decodeBase64(enCodeKey), "AES");
	}
}
