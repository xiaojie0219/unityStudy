package com.sinodata.tools;

import java.io.UnsupportedEncodingException;

public class JsonJiaJieMi {
	private String secretKey;
	
	public JsonJiaJieMi(String secretKey){
		this.secretKey = secretKey;
	}
	public String jiaMi(String str)
			throws UnsupportedEncodingException, Exception {
		String sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(
				str.getBytes("UTF-8"), secretKey));
		return sendTmp;
	}

	public String jieMi(String str)
			throws UnsupportedEncodingException, Exception {
		String data = new String(ThreeDES.decryptMode(
				ThreeDES.decryptBASE64(str), secretKey), "UTF-8");
		return data;
	}

	public static void main(String[] args) {
		JsonJiaJieMi jjj = new JsonJiaJieMi("7D77B8FC08350383857C5305");
//		String str1 = "{\"AreaCode\":\"1200\",\"DeviceId\":\"358686070238443\",\"DeviceType\":\"1\",\"RamdomStr\":\"qecpuv7p574jptty5wz6etsesnhs5cmi\",\"ReqContent\":{\"MsgType\":\"101\",\"MobileCode\":\"13611112222\"},\"SessionId\":\"ff691ba5b65644a59afb27645a63386f\",\"Version\":\"P12V1.0.0-004\"}";
		String str1 = "{'querytype': '1', 'userid': '3760100000169', 'enddate': '20161129', 'currentrow': '0', 'startdate': '20161111', 'rowcount': '10'}";
		String str2 = "/3DqoDRXl7MfgSadr+zHUxDkquF67k3KkMaVQHIvrLw8yIyMSGYh1hR31iBtbrxsgDnfqFoj6nuejjLfvObaxmC7+j27Y6iuAo7m0MbRaMzRoc7d97WcS7JYzLPG5ILdnpwPgFVZgI3QOkMQl6fZiWdnevWnL645ENQMer9U6pipazuYEef/GfVLgyfOuhQ96G829QVoYDQPOdzhdOdIMgqiKYhZZKPaKybBK5LSkVXUvF0bgcdXOvEKFglibhTEo5WDA1Yep7En/W4s/5UA0xqah5a76a0Nma5XsUOrXhvJNR8EZWxJGzg3Fbw/fmChE2Qr6hbSFhYBQwFdAXk4DA==";
		String str3 = "to99am+/7E4IjZ8I5TtIUQ==";
		
		try {
			System.out.println("发送加密前 = " + str1);
			System.out.println("发送加密后 = " + jjj.jiaMi(str1));
//			System.out.println("响应解密前 = " + str2);
//			System.out.println("响应解密后 = " + jjj.jieMi(str2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
