package com.sinodata.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class EncryptBankFile {

	private String encryptStr(String uploadStr) {
		String str2 = null;
		try {
			byte[] str = ThreeDES.encryptMode(uploadStr.getBytes("UTF-8"));
			str2 = BASE64.encryptBASE64(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str2;
	}

	private String encryptStr(byte[] content) {
		String str2 = null;
		try {
			byte[] str = ThreeDES.encryptMode(content);
			str2 = BASE64.encryptBASE64(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str2;
	}

	private void wirteFile(String path, String str) {
		try {

			FileWriter fw = new FileWriter(path);
			fw.write(str);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] readFileByBytes(File f) {
		// File f = null;
		InputStream in = null;
		byte b[] = null;
		try {
			// f = new File(path);
			b = new byte[(int) f.length()];
			in = new FileInputStream(f);
			in.read(b, 0, b.length);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : " + e);
		} catch (IOException ioe) {
			System.out.println("IOException : " + ioe);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				System.out.println("IOException : " + e);
			}
		}
		return b;
	}

	public static void main(String[] args) {
		EncryptBankFile encrypt = new EncryptBankFile();
		String path = "E:\\银行充值提现文件";
		String encryptFileName = null;
		File f = new File(path);
		File[] fs = f.listFiles();
		for (File file : fs) {
			if (file.getName().endsWith("show.txt")) {
				encryptFileName = path
						+ "//"
						+ file.getName().substring(0,
								file.getName().length() - 8);
				byte[] tmp = encrypt.readFileByBytes(file);
				encrypt.wirteFile(encryptFileName, encrypt.encryptStr(tmp));

			}
		}

	}

}
