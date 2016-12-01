package com.sinodata.tools;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Administrator
 */
public class ThreeDES{
	public static PropertiesUtil pu = new PropertiesUtil();
	private static final String Algorithm = "DESede"; 
	// DES,DESede,Blowfish
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	static String sDate = sdf.format(new Date());
	// private static final String keybyteStr = sDate+sDate+sDate;
//	public static String keybyteStr = "ADC4EA1CC874C1C3E84C43A7";
	
	public static final String keybyteStr = pu.read("config.properties", "3DES").trim();
	private static final byte[] keybyte = keybyteStr.getBytes();


	public static byte[] encryptMode(byte[] src)
	{
		try
		{
			// System.out.println(" " +ThreeDES.keybyteStr);
			
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			
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

	
	public static byte[] encryptMode(byte[] src, String keyString)
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
	

	public static byte[] decryptMode(byte[] src ,String keybyteStr) throws Exception
	{
		
		SecretKey deskey = new SecretKeySpec(keybyteStr.getBytes(), Algorithm);

	
		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.DECRYPT_MODE, deskey);
		return c1.doFinal(src);
	}
	
	

	public static byte[] decryptMode(byte[] src)
	{
		try
		{

			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
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

	public static void main(String[] args)
	{
		
		Security.addProvider(new com.sun.crypto.provider.SunJCE());

		// final byte[] keyBytes = {0x11, 0x22, 0x4F, 0x58, (byte)0x88, 0x10,
		// 0x40, 0x38
		// , 0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66
		// , 0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2};


		 String szSrc ="{\"tradestatus\":\"100000\",\"randomid\":\"abc123\",\"sellruncode\":\"123451311291213591234\",\"ticketcode\":\"0123456789ABCDEF0123\"}";



		byte[] encoded = encryptMode(szSrc.getBytes());
		
		System.out.println("" + new String(encoded) + "" + encoded.length);
		

		
		try
		{
//			String data = ThreeDES.encryptBASE64(encoded);
//			System.out.println("Base64 " + data);   
//			byte[] byteArray = ThreeDES.decryptBASE64(data);   
////			System.out.println("" + new String(byteArray));
//			byte[] srcBytesEnd = decryptMode(byteArray);
//			System.out.println(" :" + (new String(srcBytesEnd)) + " 芎蟪ざ龋锟� + new String(srcBytesEnd).length());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   

		
		
	}



	/** * BASE64 * @param key * @return * @throws Exception */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/** * BASE64 * @param key * @return * @throws Exception */
	public static String encryptBASE64(byte[] key) throws Exception {
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
	public static byte[] encryptMode(byte[] src, byte[] key) {
		try {
			// System.out.println("密钥：" +ThreeDES.keybyteStr);
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(key, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	/**
	 * 解密
	 * 
	 * @param src
	 * @return
	 */
	public static byte[] decryptMode(byte[] src, byte[] key) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(key, Algorithm);

			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}
	
}
