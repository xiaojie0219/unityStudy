package com.sinodata.mobileTest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Administrator
 */
public class ThreeDES {
	private final String ALGORITHM = "DESede";
	private Cipher cipher;
	private String keyString;
	private SecretKey desKey;
	
	public ThreeDES(String keyString) throws Exception{
		this.setKeyString(keyString);
		desKey = new SecretKeySpec(keyString.getBytes("UTF-8"),ALGORITHM);
		cipher = Cipher.getInstance(ALGORITHM);
	}

	/**
	 * 3DES加密
	 * @param byteArray 加密前的源字节数组
	 * @return 
	 * @throws Exception
	 */
	public byte[] encryptMode(byte[] byteArray) throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE, desKey);
		return cipher.doFinal(byteArray);
	}

	/**
	 * 3DES解密
	 * @param byteArray 解密前的源字节数组
	 * @return
	 * @throws Exception
	 */
	public byte[] decryptMode(byte[] byteArray) throws Exception {
		cipher.init(Cipher.DECRYPT_MODE, desKey);
		return cipher.doFinal(byteArray);
	}

	public String getKeyString() {
		return keyString;
	}

	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}
}
