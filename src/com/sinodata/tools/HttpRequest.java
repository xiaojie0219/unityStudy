package com.sinodata.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	public static PropertiesUtil pu = new PropertiesUtil();
	
	/**
	 * 发送HTTP POST请求并返回响应内容
	 * @param url 接口后半段URL地址，例如：/access/api/login.action?
	 * @param param 经过JSON加密后的字符串
	 * @return 请求响应内容
	 */
	public static String sendPost(String url,String param){
		try{
			String accessIpPort = pu.read("config.properties", "accessIpPort");
			URL urls = new URL("http://" + accessIpPort + url);
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type", "multipart/form-data");
			uc.setRequestProperty("charset", "UTF-8");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			
			uc.setUseCaches(false);
			uc.setReadTimeout(10000);
			uc.setConnectTimeout(10000);
			OutputStream os = uc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.write((param).getBytes("utf-8"));
			dos.flush();
			os.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
			String readLine = "";
			StringBuffer sb = new StringBuffer();
			while ((readLine = in.readLine()) != null)
			{
				sb.append(readLine);
			}
			in.close();
			return sb.toString();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args){
		HttpRequest.sendPost(Configuration.loginUrl, "param=");
	}
}