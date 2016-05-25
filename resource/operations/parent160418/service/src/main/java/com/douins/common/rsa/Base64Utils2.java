package com.douins.common.rsa;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Base64Utils2 {
 
    private static final byte[] BASE64_ALPHABET = { 'A', 'B', 'C', 'D', 'E',
        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
        'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
        's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
        '5', '6', '7', '8', '9', '+', '/', };
 
    private static final byte[] URLSET_ALPHABET = { 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', '-', '_', };
 
    private static final byte BASE64_PAD = '=';
     
    public static final int DEFAULT = 0;
    public static final int NO_PADDING = 1;
    public static final int URL_SAFE = 8;
 
    public static byte[] encode(String input) {
        return encode(input.getBytes());
    }
     
    public static byte[] encode(String input, String charset)
            throws UnsupportedEncodingException {
        return encode(input.getBytes(charset));
    }
 
    public static byte[] encode(byte[] input) {
        return encode(input, DEFAULT);
    }
 
    public static byte[] encode(byte[] input, int flags) {
        if(input == null || input.length == 0)
            throw new IllegalArgumentException("input should not be null or zero length");
         
        boolean doPadding = (flags & NO_PADDING) == 0;
        byte[] alphabet = (flags & URL_SAFE) == 0 ? BASE64_ALPHABET : URLSET_ALPHABET;
         
        int page, padding;
        if(input.length % 3 == 0){
            page = input.length / 3;
            padding = 0;
        }else{
            page = input.length / 3 + 1;
            padding = 3 - input.length % 3;
        }
        byte[] array = new byte[page * 3];
        for (int i = 0; i < array.length; i++) {
            array[i] = i < input.length ? input[i] : 0x00;
        }
         
        byte[] output = new byte[page * 4];
        for (int i = 0; i < page; i++) {
            byte[] temp = new byte[3];
            temp[0] = (byte) array[i * 3];
            temp[1] = (byte) array[i * 3 + 1];
            temp[2] = (byte) array[i * 3 + 2];
             
            output[i * 4] = alphabet[((temp[0] & 0xff) >> 2)];
            output[i * 4 + 1] = alphabet[((temp[0] & 0x03) << 4) ^ ((temp[1] & 0xff) >> 4)];
            output[i * 4 + 2] = alphabet[((temp[1] & 0x0f) << 2) ^ ((temp[2] & 0xff) >> 6)];
            output[i * 4 + 3] = alphabet[temp[2] & 0x3f];
        }
        if (padding > 0) {
            if (doPadding) {
                for (int i = 0; i < padding; i++) {
                    output[output.length - 1 - i] = BASE64_PAD;
                }
            } else {
                return Arrays.copyOfRange(output, 0, output.length - padding);
            }
        }
        return output;
    }
     
    public static byte[] decode(byte[] input) {
        return decode(input, DEFAULT);
    }
 
    public static byte[] decode(byte[] input, int flags) {
        if(input == null || input.length == 0)
            throw new IllegalArgumentException("input should not be null or zero length");
         
        boolean doPadding = (flags & NO_PADDING) == 0;
        String alphabet = new String((flags & URL_SAFE) == 0 ? BASE64_ALPHABET : URLSET_ALPHABET);
         
        int page, padding = 0;
        if(input.length % 4 == 0){
            page = input.length / 4;
        }else{
            if(doPadding)
                throw new IllegalArgumentException("intput should be divisible by four");
            page = input.length / 4 + 1;
        }
        byte[] array = new byte[page * 4];
        for (int i = 0; i < array.length; i++) {
            array[i] = i < input.length ? input[i] : BASE64_PAD;
            if(array[i] == BASE64_PAD){
                array[i] = 0x00;
                padding++;
            }
        }
         
        byte[] output = new byte[page * 3];
        for (int i = 0; i < page; i++) {
            byte[] temp = new byte[4];
            temp[0] = (byte) alphabet.indexOf(array[i * 4]);
            temp[1] = (byte) alphabet.indexOf(array[i * 4 + 1]);
            temp[2] = (byte) alphabet.indexOf(array[i * 4 + 2]);
            temp[3] = (byte) alphabet.indexOf(array[i * 4 + 3]);
             
            output[i * 3] = (byte) ((temp[0] << 2) ^ ((temp[1] & 0x30) >> 4));
            output[i * 3 + 1] = (byte) ((temp[1] << 4) ^ ((temp[2] & 0x3c) >> 2));
            output[i * 3 + 2] = (byte) ((temp[2] << 6) ^ temp[3]);
        }
        if (padding > 0) {
            return Arrays.copyOfRange(output, 0, output.length - padding);
        }
        return output;
    }
 
    public static String encodeToHexString(byte[] input) {
        return HexUtils.encode(encode(input, DEFAULT));
    }
 
    public static byte[] decodeFromHexString(String input) {
        return decode(HexUtils.decodeToByteArray((input)), DEFAULT);
    }
 
    public static void main(String[] args) {
 
        String str = "艘俄开发三地警方is积分";
         
        System.out.println(new String(encode(str.getBytes())));
        System.out.println(new String(decode(encode(str.getBytes()))));
         
        System.out.println(encodeToHexString(str.getBytes()));
        System.out.println(new String(decodeFromHexString(encodeToHexString(str.getBytes()))));
         
        /*
         * 控制台打印：
         * 
         * 6ImY5L+E5byA5Y+R5LiJ5Zyw6K2m5pa5aXPnp6/liIY=
         * 艘俄开发三地警方is积分
         * 36496D59354C2B453562794135592B52354C694A355A7977364B326D357061356158506E70362F6C6949593D
         * 艘俄开发三地警方is积分
         */
    }
 
}