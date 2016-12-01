package com.sinodata.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;





import com.sinodata.forJMeter.ThreeDES;

import net.sf.json.JSONObject;

public class HttpRequest {
	/**
	 * 发送HTTP POST请求并返回响应内容
	 * @param url 接口后半段URL地址，例如：/access/api/login.action?
	 * @param param 经过JSON加密后的字符串
	 * @return 请求响应内容
	 */
	public static byte[] sendPost(String url,byte[] data){
		try{
			URL urls = new URL(url);
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
			dos.write(data);
			dos.flush();
			os.close();
//			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
//			String readLine = "";
//			StringBuffer sb = new StringBuffer();
//			while ((readLine = in.readLine()) != null)
//			{
//				sb.append(readLine);
//			}
//			in.close();
//			return sb.toString();
			
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

	public static void main(String[] args) throws Exception {
		String agentSecretKey = "F734B7E634E78EBB9A467B0E";
		String DES3 = "zhongxinyinhang123456789";
		ThreeDES td = new ThreeDES(DES3);
		
		JSONObject rc = new JSONObject();
		rc.put("GameId","B001");
		rc.put("TermCode","");
//		rc.put("TicketCode","B9D985E6704FFA16C619");

		JSONObject bd = new JSONObject();
		bd.put("PartnerId","20001");
		bd.put("TimeStamp","2016-04-27 10:39:10");
		bd.put("SerialNum","2016040001113");
		bd.put("Version","1.0.0.0");
		bd.put("Token","92EA48927E3BFA1755A64FBC16B6B901");
		bd.put("ReqContent",rc.toString());
		
		String msg = bd.toString();
		System.out.println("msg=" + msg);
		
		byte[] data = td.encryptMode(msg.getBytes("UTF-8"), agentSecretKey);
		
		FileOutputStream fos = new FileOutputStream("D:\\1\\request.data");
		fos.write(data);
		FileInputStream fis = new FileInputStream("D:\\1\\request.data");
		int dataLength = data.length;
		byte[] b = new byte[dataLength];
		int hasRead = 0;
		while((hasRead = fis.read(b))> 0){
			StringBuffer sb = new StringBuffer();
			sb.append(hasRead);
			System.out.println("=========================" + sb.toString());
		}
				
		String hash = MD5Security.getMD5String(data);
		System.out.println("hash：" + hash);
		
		String url = "http://10.10.35.146:8188/api/access/do?cmd=queryPrize&partnerId=20001&hashType=md5&hash="
				+ hash;

		byte[] response = HttpRequest.sendPost(url, data);

		System.out.println("响应解密前：" + response);
		System.out.println("响应解密后："
						+ new String(td.decryptMode(response, agentSecretKey),
								"UTF-8"));
	}
}