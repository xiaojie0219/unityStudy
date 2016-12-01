package com.sinodata.forJMeter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Administrator
 */
public class ThreeDES{
	private String Algorithm = "DESede"; 
	private byte[] keybyte;
	
	public ThreeDES(String keybyteStr){
		this.keybyte = keybyteStr.getBytes();
	}
	
//	public byte[] encryptMode(byte[] src)
//	{
//		try
//		{
//			// System.out.println(" " +ThreeDES.keybyteStr);
//			
//			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
//
//			
//			Cipher c1 = Cipher.getInstance(Algorithm);
//			c1.init(Cipher.ENCRYPT_MODE, deskey);
//			return c1.doFinal(src);
//		}
//		catch (java.security.NoSuchAlgorithmException e1)
//		{
//			e1.printStackTrace();
//		}
//		catch (javax.crypto.NoSuchPaddingException e2)
//		{
//			e2.printStackTrace();
//		}
//		catch (java.lang.Exception e3)
//		{
//			e3.printStackTrace();
//		}
//		return null;
//	}

	
	public byte[] encryptMode(byte[] src, String keyString)
	{
		try
		{
			// System.out.println(" " +ThreeDES.keybyteStr);
			
			SecretKey deskey = new SecretKeySpec(keyString.getBytes("UTF-8"), Algorithm);

			
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		}
		catch (java.security.NoSuchAlgorithmException e1)
		{
			e1.printStackTrace();
		}
		catch (javax.crypto.NoSuchPaddingException e2)
		{
			e2.printStackTrace();
		}
		catch (java.lang.Exception e3)
		{
			e3.printStackTrace();
		}
		return null;
	}
	

	public byte[] decryptMode(byte[] src ,String keybyteStr) throws Exception
	{
		
		SecretKey deskey = new SecretKeySpec(keybyteStr.getBytes(), Algorithm);

	
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		return c1.doFinal(src);
	}
	
	

//	public byte[] decryptMode(byte[] src)
//	{
//		try
//		{
//
//			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
//
//			Cipher c1 = Cipher.getInstance(Algorithm);
//			c1.init(Cipher.DECRYPT_MODE, deskey);
//			return c1.doFinal(src);
//		}
//		catch (java.security.NoSuchAlgorithmException e1)
//		{
//			e1.printStackTrace();
//		}
//		catch (javax.crypto.NoSuchPaddingException e2)
//		{
//			e2.printStackTrace();
//		}
//		catch (java.lang.Exception e3)
//		{
//			e3.printStackTrace();
//		}
//		return null;
//	}

	// 转锟斤拷锟斤拷十锟斤拷锟斤拷锟斤拷址锟�
	public static String byte2hex(byte[] b)
	{
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++)
		{
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	
	/** * BASE64 * @param key * @return * @throws Exception */
	public byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/** * BASE64 * @param key * @return * @throws Exception */
	public String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	// keybyte为加密密钥，长度为24字节
	// src为被加密的数据缓冲区（源）
	/**
	 * 加密
	 * 
	 * @param src
	 * @return
	 */
//	public byte[] encryptMode(byte[] src, byte[] key) {
//		try {
//			// System.out.println("密钥：" +ThreeDES.keybyteStr);
//			// 生成密钥
//			SecretKey deskey = new SecretKeySpec(key, Algorithm);
//			// 加密
//			Cipher c1 = Cipher.getInstance(Algorithm);
//			c1.init(Cipher.ENCRYPT_MODE, deskey);
//			return c1.doFinal(src);
//		} catch (java.security.NoSuchAlgorithmException e1) {
//			e1.printStackTrace();
//		} catch (javax.crypto.NoSuchPaddingException e2) {
//			e2.printStackTrace();
//		} catch (java.lang.Exception e3) {
//			e3.printStackTrace();
//		}
//		return null;
//	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	/**
	 * 解密
	 * 
	 * @param src
	 * @return
	 */
//	public byte[] decryptMode(byte[] src, byte[] key) {
//		try {
//			// 生成密钥
//			SecretKey deskey = new SecretKeySpec(key, Algorithm);
//
//			// 解密
//			Cipher c1 = Cipher.getInstance(Algorithm);
//			c1.init(Cipher.DECRYPT_MODE, deskey);
//			return c1.doFinal(src);
//		} catch (java.security.NoSuchAlgorithmException e1) {
//			e1.printStackTrace();
//		} catch (javax.crypto.NoSuchPaddingException e2) {
//			e2.printStackTrace();
//		} catch (java.lang.Exception e3) {
//			e3.printStackTrace();
//		}
//		return null;
//	}
	
}
