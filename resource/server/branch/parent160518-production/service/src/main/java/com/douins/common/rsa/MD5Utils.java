package com.douins.common.rsa;

import java.security.MessageDigest;

public class MD5Utils {

	public static String getMd5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			try {
				md.update(plainText.getBytes("utf-8"));
			} catch (Exception e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			//System.out.println("result: " + buf.toString());//32位的加密

			//System.out.println("result: " + buf.toString().substring(8,24));//16位的加密

			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	// 测试主函数  
	public static void main(String args[]) {
		String s = new String("tangfuqiang");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));
	}

	/**
	 * 计算一字符串MD5信息摘要
	 * 
	 * @返回一个信息摘要
	 */
	public static String computeMD5(String inputStr) {

		MessageDigest md5 = null;
		try {
			//创建MD5函数对象
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inputStr.toCharArray(); //将字符串转换为字符数组
		byte[] byteArray = new byte[charArray.length]; //创建字节数组

		for (int i = 0; i < charArray.length; i++)
			//将字符转换为字节
			byteArray[i] = (byte) charArray[i];

		//将得到的字节数组进行MD5运算
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer reMD5Str = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				reMD5Str.append("0");
			reMD5Str.append(Integer.toHexString(val));
		}

		//返回信息摘要结果字符串
		return reMD5Str.toString();
	}
	
	public static String lianlifeMD5(byte[] source) { 
		String s = null; 
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 
                'e', 'f' }; 
        try { 
            java.security.MessageDigest md = java.security.MessageDigest 
                    .getInstance("MD5"); 
            md.update(source); 
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数， 
            // 用字节表示就是 16 个字节 
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符， 
            // 所以表示成 16 进制需要 32 个字符 
            int k = 0; // 表示转换结果中对应的字符位置 
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节 
                // 转换成 16 进制字符的转换 
                byte byte0 = tmp[i]; // 取第 i 个字节 
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, 
                // >>> 为逻辑右移，将符号位一起右移 
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换 
            } 
            s = new String(str); // 换后的结果转换为字符串 
  
        } catch (Throwable e) { 
             throw new RuntimeException(e);
        } 
	    return s; 
	}
}
