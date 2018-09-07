package com.sinodata.mobileTest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	/**
	 * 发起POST请求，返回字节数组响应数据
	 * @param url 完整的请求url地址，例：http://10.10.37.149:8189/api/access/agentTicketData?partnerId={0}&hashType=md5&hash={1}
	 * @param bodyData POST请求body体字节数组数据
	 * @return 字节数组响应数据
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] sendPost(String url,byte[] bodyData) throws UnsupportedEncodingException{
		try{
			URL urls = new URL(url);
			HttpURLConnection uc = (HttpURLConnection) urls.openConnection();
			uc.setRequestMethod("POST");
			uc.setRequestProperty("content-type", "multipart/form-data");
			uc.setRequestProperty("charset", "UTF-8");
			uc.setDoOutput(true);
			uc.setDoInput(true);
			
			uc.setUseCaches(false);
			uc.setReadTimeout(30000);
			uc.setConnectTimeout(30000);
			OutputStream os = uc.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.write(bodyData);
			dos.flush();
			os.close();
			
			InputStream inStrm = uc.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(inStrm);
			ByteArrayOutputStream bAOutputStream = new ByteArrayOutputStream();
			int ch;
			while ((ch = bis.read()) != -1) {
				bAOutputStream.write(ch);
			}
			 byte[] ret = bAOutputStream.toByteArray();
			 return ret;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}