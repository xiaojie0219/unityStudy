package com.sinodata.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



public class TestFile {

	public static void main(String[] args) {
		
		File f = new File("D:\\1\\windata_K3_0909055.txt");
		try {
			
			FileInputStream fis = new FileInputStream(f);
			int len = fis.available();
			byte[] buffer = new byte[len];
			int rlen = fis.read(buffer);
			System.out.println("rlen:" + rlen);
			System.out.println("blen:" + len);
			
			String content = decrypt(buffer);
			System.out.println("解密后的内容：\n" + content);
			
//			FileOutputStream fos = new FileOutputStream("D:\\1\\test_decode.txt");
//			fos.write(content.getBytes(""));
//			fos.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
			decryptC = new BASE64().decryptBASE64(new String(content, "utf-8"));
			byte[] str = ThreeDES.decryptMode(decryptC, "shandongfucaiftpfile2015".getBytes("utf-8"));
			decryptContent = new String(str, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptContent;

	}
	/**
	 * 加密
	 * 
	 * @param content
	 * @return
	 */
	public static byte[] encrypt(byte[] content) {
		byte[] decryptContent = null;
		try {
			byte[] str = ThreeDES.encryptMode(content, "zhongxinyinhang123456789".getBytes("utf-8"));
			decryptContent = new BASE64().encryptBASE64(str).getBytes("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptContent;
	}

	static class BASE64 {
		public  byte[] decryptBASE64(String key) throws Exception {
			return (new BASE64Decoder()).decodeBuffer(key);
		}

		public  String encryptBASE64(byte[] key) throws Exception {
			return (new BASE64Encoder()).encodeBuffer(key);
		}
	}
}
