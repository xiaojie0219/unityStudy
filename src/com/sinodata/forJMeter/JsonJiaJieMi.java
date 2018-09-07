package com.sinodata.forJMeter;

import java.io.UnsupportedEncodingException;

public class JsonJiaJieMi {
	private String secretKey;
	private ThreeDES td;

	public JsonJiaJieMi(String secretKey) {
		this(secretKey,"zhongxinyinhang123456789");
	}
	
	public JsonJiaJieMi(String secretKey, String DES3) {
		this.secretKey = secretKey;
		td = new ThreeDES(DES3);
	}

	public String jiaMi(String str) throws UnsupportedEncodingException,Exception {
		String sendTmp = td.encryptBASE64(td.encryptMode(str.getBytes("UTF-8"),	secretKey));
		return sendTmp;
	}

	public String jieMi(String str) throws UnsupportedEncodingException,Exception {
		String data = new String(td.decryptMode(td.decryptBASE64(str),	secretKey), "UTF-8");
		return data;
	}
	
	public byte[] jiaMiNoBASE64(String str) throws UnsupportedEncodingException,Exception {
		byte[] b = td.encryptMode(str.getBytes("UTF-8"),secretKey);
		return b;
	}
	
	public String jieMiNoBASE64(String str) throws UnsupportedEncodingException,Exception {
		String data = new String(td.decryptMode(str.getBytes(),secretKey),"UTF-8");
		return data;
	}
	
	public static void main(String args[]) throws UnsupportedEncodingException, Exception{
		JsonJiaJieMi jjj = new JsonJiaJieMi("5BC78A05708643763E756635","zhongxinyinhang123456789");
		String str = new String(jjj.jiaMi("hello world"));
//		System.out.println("加密后：" + str);
		
		System.out.println("解密后：" + jjj.jieMi("EHbQZPLS8RybKnTYonWDALB/GUfwzdBa6XMT3QooFFUCWVMoTxKisW8ShzVGeziscEGKenO0Nws="));
	}
}