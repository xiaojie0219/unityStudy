package com.sinodata.forJMeter;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.sinodata.forJMeter.ThreeDES;

import net.sf.json.JSONObject;

public class HttpRequest {
	private static String agentSecretKey = null;
	private static ThreeDES threeDES = null;
	private String ipAndPort = null;
	private Map<String,String> mapData = null;
	private JSONObject basicParam = new JSONObject();
	private JSONObject reqContent = new JSONObject();
	private final String DES3 = "zhongxinyinhang123456789";
	
	public HttpRequest(String agentSecretKey,String ipAndPort, Map<String,String> mapdata){
		HttpRequest.agentSecretKey = agentSecretKey;
		threeDES = new ThreeDES(DES3);
		this.ipAndPort = ipAndPort;
		mapData = mapdata;
		for (String key : mapData.keySet()) {
			String value = mapData.get(key);
			if (!"ipAndPort".equals(key) && !"agentSecretKey".equals(key)
					&& !"DES3".equals(key) && !"TestElement.name".equals(key)) {
				if ("PartnerId".equals(key) || "TimeStamp".equals(key)
						|| "SerialNum".equals(key) || "Version".equals(key)
						|| "Token".equals(key) ||"AreaCode".equals(key)){
					basicParam.put(key, value);
				} else {
					reqContent.put(key, value);
				}
			}
		}
		basicParam.put("ReqContent", reqContent.toString());
		System.out.println("============reqContent========="+reqContent.toString());
		System.out.println("============basicParam========="+basicParam.toString());
	}
	private byte[] createUrl(byte[] data, String url){
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
			dos.write(data);
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
	public byte[] sendPost(String uri) throws UnsupportedEncodingException{
		byte[] data = requestData();
		String url = "http://" + ipAndPort + uri + "partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data, url);
	}

	private byte[] requestData() throws UnsupportedEncodingException{
		byte[] b = null;
		b = threeDES.encryptMode(basicParam.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public String getResponseData(String uri) throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	
	/*
	public static void main(String[] args) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("GameId", "B001");
		map.put("TermCode", "");
		map.put("PartnerId", "20001");
		map.put("TimeStamp", "2016-04-27 10:39:10");
		map.put("SerialNum", "2016040001113");
		map.put("Version", "1.0.0.0");
		map.put("Token", "92EA48927E3BFA1755A64FBC16B6B901");
		
		HttpRequest hr = new HttpRequest("F734B7E634E78EBB9A467B0E","zhongxinyinhang123456789","10.10.35.146:8188",map);
		
		System.out.println("响应解密后：" + hr.getResponseDataByte2String());
	}
*/
}