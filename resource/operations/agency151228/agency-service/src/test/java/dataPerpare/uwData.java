package dataPerpare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.Static;

import freemarker.log.Logger;
import sun.tools.tree.ThisExpression;

public class uwData {
	private static String  uwpath = "/Users/hou/Desktop/uwData.txt";
	private static String uwElement = "OrderId";
	/**
	 * 读数据
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String  read(String path) throws Exception{
		String result = "";
		File file = new File(path);
		if(file.exists() && file.isFile()){
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"UTF-8");
				BufferedReader bufferedReader = new BufferedReader(reader);
				String data = null;
				while((data =bufferedReader.readLine())!=null){
					result +=data;
				}
				System.out.println(result);
				reader.close();
		}
		return result;
	}
	/**
	 * 更改数据
	 * @param data
	 * @return
	 */
	public static String changUw(String data){
		String result = "";
		String orderid = randomData()+"";
		String old = findPoint(data, uwElement).text();
		result = data.replaceAll(old, orderid);
		return result;
	}
	/**
	 * 写数据
	 * @param data
	 * @param path
	 */
	public static void write(String data,String path){
		File file =new File(path);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true),"UTF-8"));
			writer.append(data);
			writer.newLine();
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static Elements  findPoint(String data,String element){
		Document doc = Jsoup.parse(data, "", Parser.xmlParser());
		Elements  elements = doc.select(element);
		return elements;
	}
	public static long  randomData(){
		Random rand = new Random();
		long i = System.currentTimeMillis();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}
	public static void main(String argv[]){
		try {
			String data = read(uwpath);		
			for(int i=0;i<10;i++){
				write(changUw(data), uwpath);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
