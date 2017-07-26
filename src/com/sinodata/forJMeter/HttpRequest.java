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
	
	public HttpRequest(String agentSecretKey, String des3, String ipAndPort, Map<String,String> mapdata){
		HttpRequest.agentSecretKey = agentSecretKey;
		threeDES = new ThreeDES(des3);
		this.ipAndPort = ipAndPort;
		mapData = mapdata;
		for (String key : mapData.keySet()) {
			String value = mapData.get(key);
			if (!"ipAndPort".equals(key) && !"agentSecretKey".equals(key)
					&& !"DES3".equals(key) && !"TestElement.name".equals(key)) {
				if ("PartnerId".equals(key) || "TimeStamp".equals(key)
						|| "SerialNum".equals(key) || "Version".equals(key)
						|| "Token".equals(key)) {
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
			uc.setReadTimeout(10000);
			uc.setConnectTimeout(10000);
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
	
	public String getResponseData4Auth() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=auth&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryPrize() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=queryPrize&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryPrizeCode() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=queryPrizeCode&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryCode() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=queryCode&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryTicketEx() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=queryTicketEx&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryTerm() throws UnsupportedEncodingException, Exception {
		String uri = "/api/access/do?cmd=queryTerm&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4ImageTicket() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=imageTicket&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4AgentTicket() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=agentTicket&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4EncashCheckJkp() throws  UnsupportedEncodingException, Exception{
		String uri = "/access/comm/agent/encashCheckJkp?";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4EncashJkp() throws  UnsupportedEncodingException, Exception{
		String uri = "/access/comm/agent/encashJkp?";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4LottMoney() throws  UnsupportedEncodingException, Exception{
		String uri = "/access/lottMoney?";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4AgentTicketInfo()  throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=agentTicketInfo&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4AgentTicketData() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=agentTicketData&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4OrderCreate() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/pay?cmd=barcodeordercreate&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4OrderQuery() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/pay?cmd=orderquery&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4TicketOrder() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/notice?cmd=ticketorder&";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4CTicketOrder() throws  UnsupportedEncodingException, Exception{
		String uri = "/access/CTicketOrder?";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4LottMoneyCash() throws  UnsupportedEncodingException, Exception{
		String uri = "/access/lottMoneyCash?";
		return new String(threeDES.decryptMode(sendPost(uri), agentSecretKey),"UTF-8");
	}
	public String getResponseData4SendSms() throws  UnsupportedEncodingException, Exception{
		String uri = "/api/access/do?cmd=sendSms&";
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