package com.sinodata.tools;

import java.io.FileWriter;
import java.io.IOException;

public class FTPCryptUtil {

	// ftp文件加密的key
	public static String key = "C3F1E6B25312648DDF122DB8";

	/**
	 * 加密
	 * 
	 * @param content
	 * @return
	 */
	public static String encrypt(byte[] content) {
		String contentStr = null;
		byte[] decryptContent = null;
		try {
			byte[] str = ThreeDES.encryptMode(content, key.getBytes("utf-8"));
			decryptContent = BASE64.encryptBASE64(str).getBytes("utf-8");
			contentStr = new String(decryptContent, "utf-8");
		} catch (Exception e) {
			// logger.error("ftp文件加密",e);
		}
		return contentStr;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 * @return
	 */
	public static String decrypt(byte[] content) {
		String decryptContent = null;
		byte[] decryptC = null;
		try {
			decryptC = BASE64.decryptBASE64(new String(content, "utf-8"));
			byte[] str = ThreeDES.decryptMode(decryptC, key.getBytes("utf-8"));
			decryptContent = new String(str, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptContent;

	}

	public static void writeFile(String path,String str) throws IOException{
		FileWriter fw = new FileWriter(path,true);
		fw.write(str);
		fw.flush();
		fw.close();
	}
	public static void main(String[] args) {

//		String a = "12";
//		String as = FTPCryptUtil.encrypt(a.getBytes());
//		System.out.println(as);
//		String bs = FTPCryptUtil
//				.decrypt("vrksv8Fia0uLtmuyJeTzDcTR9PH8VzARg0iQcWVUw3cB4j+AWObCQe/AB9SxpIQvMDfokj/E4IY="
//						.getBytes());
//		System.out.println(bs);
		
		String strTmp = FTPCryptUtil.encrypt("12010014|60391359127349513372|10000|2016-12-04|05:36:17".getBytes());
		String strTmp1 = FTPCryptUtil.encrypt("12010014|60391359127349513372|10000|2016-12-04|05:36:17".getBytes());
		System.out.println(strTmp);
		try {
			FTPCryptUtil.writeFile("d:\\1\\ftp.txt",strTmp);
			FTPCryptUtil.writeFile("d:\\1\\ftp.txt",strTmp1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
