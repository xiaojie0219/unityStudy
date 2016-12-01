package com.sinodata.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

public class MySecurity {
	private static String securityKey = "shandongfucaiftpfile2015";
	private Cipher encryptCipher = null;
	private Cipher decryptCipher = null;

	private Key getKey(byte[] arrBTmp) {
		// 创建一个空的8位字节数组（默认值为0）
		byte[] arrB = new byte[8];
		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;

	}

	public MySecurity() throws Exception {
		this(securityKey);
	}

	public MySecurity(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	// 字符串加密
	/**
	 * 将字节数组转换为字符串
	 * 
	 * @param arrB
	 *            字节数组
	 * @return 返回字符串
	 * @throws Exception
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，因此字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	// 字符串解密
	/**
	 * 字符串转换为字节数组
	 * 
	 * @param strIn
	 *            待转换的字符串
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，因此字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	// 文件加密
	/**
	 * 以字节数组的形式返回文件的内容
	 * 
	 * @param file
	 * @return 文件内容的字节数组
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		// 获取文件大小
		long length = file.length();
		// 限制要读取的文件的大小不能超过Integer.MAX_VALUE.
		if (length > Integer.MAX_VALUE) {
			is.close();
			return null;// 文件太大，退出程序
		}
		// 创建字节数组
		byte[] bytes = new byte[(int) length];
		// 将文件信息读入数组
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确认是否将所有信息读入数组
		if (offset < bytes.length) {
			is.close();
			throw new IOException("不能完全读取文件" + file.getName());
		}
		// 关闭流
		is.close();
		return bytes;
	}

	// 将字节数组写入文件
	/**
	 * 将字节数组写入文件
	 * 
	 * @param inByte
	 *            字节数组
	 * @param pathAndNameString
	 *            路径和文件名
	 * @return 返回文件
	 * @throws IOException
	 */
	public static File writeBytesToFile(byte[] inByte, String pathAndNameString)
			throws IOException {
		File file = null;
		try {
			file = new File(pathAndNameString);
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(inByte);
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	// 文件加密
	/**
	 * 文件加密
	 * 
	 * @param srcFile
	 *            要加密的源文件
	 * @param destFile
	 *            目的地文件
	 * @throws Exception
	 */
	public void encryptFile(String srcFile, String destFile) throws Exception {
		File infile = new File(srcFile);// 加密前的文件
		byte[] myfileA = getBytesFromFile(infile);
		writeBytesToFile(encrypt(myfileA), destFile);// 加密后的文件
	}

	// 文件解密
	/**
	 * 文件解密
	 * 
	 * @param srcFile
	 *            要解密的源文件
	 * @param destFile
	 *            目的地文件
	 * @throws Exception
	 */
	public void decryptFile(String srcFile, String destFile) throws Exception {
		File infile = new File(srcFile);// 解密前的文件
		byte[] myfileA = getBytesFromFile(infile);
		writeBytesToFile(decrypt(myfileA), destFile);// 密后的文件
	}

	public static void main(String args[]) {
		try {
			MySecurity des = new MySecurity();
			// 加密文件测试
			des.encryptFile("D:\\2\\未加密文档.txt", "D:\\2\\已加密文档.txt");
			// 解密文件测试
			des.decryptFile("D:\\2\\已加密文档.txt", "D:\\2\\未加密文档1.txt");
			// 字符串加密
//			System.out.println("解密前的String是：" + "test");
//			String enString = des.encrypt("test");
//			System.out.println("加密后的enString是：" + enString);
			// 字符串解密
//			String deString = des.decrypt("46469f4be946eb0d");
//			System.out.println("解密前后的deString是：" + deString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
