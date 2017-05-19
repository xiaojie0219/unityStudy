package com.sinodata.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

import com.csvreader.CsvReader;


public class FileUtil {
	/**
	 * 获取文件内容
	 * @param filepath 文件完整路径
	 * @return 返回文件内容的ArrayList
	 * @throws IOException 
	 */
	public static ArrayList<String> getFileContent(String filePath) throws IOException{
		ArrayList<String> al = new ArrayList<String>();
		InputStreamReader in = new InputStreamReader(new FileInputStream(filePath),"UTF-8");
		BufferedReader br = new BufferedReader(in);
		String s;
		while((s = br.readLine()) != null){
			al.add(s);
		}
		br.close();
		Iterator<String> it = al.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		return al;
	}
	
	 /**
	  * 读取CSV文件并返回文件中参数的ArrayList
	  * @param filePath CSV文件路径
	  * @return 文件中参数的ArrayList<String[]>
	  */
     public static ArrayList<String[]> readeCsv(String filePath) throws IOException{
         try {    
             ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据
             CsvReader reader = new CsvReader(filePath,',',Charset.forName("UTF-8"));    
             // 跳过表头   如果需要表头的话，不要写这句。
             //reader.readHeaders(); 
             
             while(reader.readRecord()){ //逐行读入数据    
                 csvList.add(reader.getValues());
             }            
             reader.close();
             return csvList;
         }catch(Exception e){
             System.out.println(e);
             return null;
         }
     }
    /**
     * 将指定内容写入文件
     * @param str 写入文件内容
     * @param path 写入文件路径
     */
    public static void writeToFile(String str,String path){
    	try{
    		File file =new File(path);
    		if(!file.exists()){
    			file.createNewFile();
    	   }
    	FileWriter fileWritter = new FileWriter(file.getName());
    	BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	bufferWritter.write(str);
    	bufferWritter.close();
    	}catch(IOException e){
    		e.printStackTrace();
    	}
	}
    /**
     * 读取指定文件中的内容
     * @param path
     * @return
     * @throws IOException
     */
    public static String readFileToString(String path) throws IOException{
    	InputStreamReader in = new InputStreamReader(new FileInputStream(path),"UTF-8");
		BufferedReader br = new BufferedReader(in);
		String s;
		StringBuffer sb = new StringBuffer();
		while((s = br.readLine()) != null){
			sb.append(s);
		}
		br.close();
		System.out.println(sb.toString());
		return sb.toString();
    }
    
	public static void main(String[] args) throws IOException {
//		FileUtil.writeToFile("loginsession	char(50)	动态sessionid","loginsession.txt");
//		try {
//			FileUtil.readFileToString("loginsession.txt");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		FileInputStream  fis= new FileInputStream("D:\\1\\request_data.bin");
		byte[] b = new byte[1024];
		int hasRead = 0;
		while((hasRead = fis.read(b))!= -1){
			System.out.println(new String(b,0,hasRead));
		}
	}
	
	
}
