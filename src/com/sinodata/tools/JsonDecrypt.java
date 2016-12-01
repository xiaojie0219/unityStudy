package com.sinodata.tools;

import java.io.UnsupportedEncodingException;

/**
 * 用于解密经过加密后的JSON数据
 * @author Administrator
 *
 */
public class JsonDecrypt {
	public PropertiesUtil pu = null;
	public String pubSecretKey = null;
	public String agentSecretKey = null;

	public JsonDecrypt(){
		pu = new PropertiesUtil();
		pubSecretKey = pu.read("config.properties", "pubSecretKey");
		agentSecretKey = pu.read("config.properties", "agentSecretKey");
	}

	public void decryptAgent(String str) throws UnsupportedEncodingException, Exception{
		String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(str), agentSecretKey),"UTF-8");
		System.out.println(data);
	}
	
	public void decryptApi(String str) throws UnsupportedEncodingException, Exception{
		String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(str), pubSecretKey),"UTF-8");
		System.out.println(data);
	}
	
	public static void main(String[] args) {
		String str = "rsX8k8kRAqyEtV1m5rmY3rqKgyg4k8UGlEKT0kuu1DpzuKOzQfqbu0jHXUCNPtJNMS0FMXqUIIKYTjSrVcFz4tiSRqAg1takuMtgAjlhAEusBpUuqWe7mcUhmFnZVjI2I4rM7cpAEJFErU45M+jHwl7pBy+YoI1qwP+++NdXyI8=";
		JsonDecrypt jd = new JsonDecrypt();
		try {
			jd.decryptAgent(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
