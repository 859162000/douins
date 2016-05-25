package com.douins.agency.service.douins.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class BalanceTest {
	
	private static String path = "/Users/hou/Desktop/"; 
	private static String filenameTemp; 
	
	@Test
	public  void  test(){
		Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        String dateNowStr = sdf.format(d); 
        System.out.println(dateNowStr);
		String name = "CCIC"+dateNowStr+"01";
		creatTxtFile(name);
		String  tem ="345671234567890";
		writeTxtFile(tem);
		readTxtFile(path+name+".txt");
	}
	
	/** 
	* 创建文件 
	* 
	* @throws IOException 
	*/ 
	public static boolean creatTxtFile(String name) { 
	boolean flag = false; 
	filenameTemp = path + name + ".txt"; 
	File filename = new File(filenameTemp); 
	if (!filename.exists()) { 
	try {
		filename.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
	} 
	flag = true; 
	} 
	return flag; 
	} 
	
	/** 
	* 写文件 
	* 
	* @param newStr 
	*            新内容 
	* @throws IOException 
	*/ 
	public static boolean writeTxtFile (String newStr) { 
	// 先读取原有文件内容，然后进行写入操作 
	boolean flag = false; 
	String filein = newStr + "\r\n"; 
	String temp = ""; 

	FileInputStream fis = null; 
	InputStreamReader isr = null; 
	BufferedReader br = null; 

	FileOutputStream fos = null; 
	PrintWriter pw = null; 
	try { 
	// 文件路径 
	File file = new File(filenameTemp); 
	// 将文件读入输入流 
	fis = new FileInputStream(file); 
	isr = new InputStreamReader(fis); 
	br = new BufferedReader(isr); 
	StringBuffer buf = new StringBuffer(); 

	// 保存该文件原有的内容 
	for (int j = 1; (temp = br.readLine()) != null; j++) { 
	buf = buf.append(temp); 
	// System.getProperty("line.separator") 
	// 行与行之间的分隔符 相当于“\n” 
	buf = buf.append(System.getProperty("line.separator")); 
	} 
	buf.append(filein); 

	fos = new FileOutputStream(file); 
	pw = new PrintWriter(fos); 
	pw.write(buf.toString().toCharArray()); 
	pw.flush(); 
	flag = true; 
	} catch (IOException e1) { 
	// TODO 自动生成 catch 块 
	try {
		throw e1;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	} finally { 
	if (pw != null) { 
	pw.close(); 
	} 
	if (fos != null) { 
	try {
		fos.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	} 
	if (br != null) { 
	try {
		br.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	} 
	if (isr != null) { 
	try {
		isr.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	} 
	if (fis != null) { 
	try {
		fis.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	} 
	} 
	return flag; 
	} 
	
	/**
     * 读取txt文件的内容
     */
    public static void readTxtFile(String filePath){
        try {
                String encoding="GBK";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader( new FileInputStream(file),encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        System.out.println(lineTxt);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }

}
