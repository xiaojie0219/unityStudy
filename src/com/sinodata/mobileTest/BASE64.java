package com.sinodata.mobileTest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** * BASE64加密解密 */
public class BASE64
{
	
	/**
	 * * BASE64解码 *
	 * @param beDecryptString 待解码字符串
	 * @return 解码后的字节数组
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String beDecryptString) throws Exception
	{
		return (new BASE64Decoder()).decodeBuffer(beDecryptString);
	}

	/**
	 * * BASE64转码 *
	 * @param beEncryptByteArray 待转码字节数组 
	 * @return 转码后的字符串
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] beEncryptByteArray) throws Exception
	{
		return (new BASE64Encoder()).encodeBuffer(beEncryptByteArray);
	}
}