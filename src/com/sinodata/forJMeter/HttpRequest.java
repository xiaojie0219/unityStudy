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
	
	public HttpRequest(String agentSecretKey, String des3, String ipAndPort, Map<String,String> map){
		HttpRequest.agentSecretKey = agentSecretKey;
		threeDES = new ThreeDES(des3);
		this.ipAndPort = ipAndPort;
		mapData = map;
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
	public byte[] sendPostAuth() throws UnsupportedEncodingException{
		byte[] data = requestDataAuth();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=auth&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	public byte[] sendPostQueryPrize() throws UnsupportedEncodingException{
		byte[] data = requestDataQueryPrize();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=queryPrize&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	public byte[] sendPostQueryPrizeCode() throws UnsupportedEncodingException{
		byte[] data = requestDataQueryPrizeCode();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=queryPrizeCode&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	public byte[] sendPostQueryCode() throws UnsupportedEncodingException{
		byte[] data = requestDataQueryCode();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=queryCode&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	public byte[] sendPostQueryTerm() throws UnsupportedEncodingException{
		byte[] data = requestDataQueryTerm();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=queryTerm&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	public byte[] sendPostQueryTicketEx() throws UnsupportedEncodingException{
		byte[] data = requestDataQueryTicketEx();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=queryTicketEx&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	public byte[] sendPostAgentTicketInfo() throws UnsupportedEncodingException{
		byte[] data = requestDataAgentTicketInfo();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=agentTicketInfo&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	public byte[] sendPostOrderCreate() throws UnsupportedEncodingException{
		byte[] data = requestDataOrderCreate();
		String url = "http://" + ipAndPort
				+ "/api/access/pay?cmd=barcodeordercreate&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	public byte[] sendPostOrderQuery() throws UnsupportedEncodingException{
		byte[] data = requestDataOrderQuery();
		String url = "http://" + ipAndPort
				+ "/api/access/pay?cmd=orderquery&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	public byte[] sendPostCTicketOrder() throws UnsupportedEncodingException{
		byte[] data = requestDataCTicketOrder();
		String url = "http://" + ipAndPort
				+ "/access/CTicketOrder?partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	private byte[] sendPostAgentTicketData() throws UnsupportedEncodingException{
		byte[] data = requestDataAgentTicketData();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=agentTicketData&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}

	
	public byte[] sendPostImageTicket() throws UnsupportedEncodingException{
		byte[] data = requestDataImageTicket();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=imageTicket&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	private byte[] sendPostAgentTicket() throws UnsupportedEncodingException {
		byte[] data = requestDataAgentTicket();
		String url = "http://" + ipAndPort
				+ "/api/access/do?cmd=agentTicket&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	private byte[] sendPostEncashCheckJkp() throws UnsupportedEncodingException {
		byte[] data = requestDataEncashCheckJkp();
		String url = "http://" + ipAndPort
				+ "/access/comm/agent/encashCheckJkp?partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}

	private byte[] sendPostEncashJkp() throws UnsupportedEncodingException {
		byte[] data = requestDataEncashJkp();
		String url = "http://" + ipAndPort
				+ "/access/comm/agent/encashJkp?partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	private byte[] sendPostLottMoney() throws UnsupportedEncodingException {
		byte[] data = requestDataLottMoney();
		String url = "http://" + ipAndPort
				+ "/access/lottMoney?partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}

	private byte[] sendPostTicketOrder() throws UnsupportedEncodingException {
		byte[] data = requestDataTicketOrder();
		String url = "http://" + ipAndPort
				+ "/api/access/notice?cmd=ticketorder&partnerId="
				+ mapData.get("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(data);

		return createUrl(data,url);
	}
	
	private byte[] requestDataEncashJkp() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("RunCode", mapData.get("RunCode"));
		rc.put("CardId", mapData.get("CardId"));
		rc.put("IMEI", mapData.get("IMEI"));
		rc.put("MobileCode", mapData.get("MobileCode"));
		rc.put("DataArea", mapData.get("DataArea"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	private byte[] requestDataEncashCheckJkp() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("RunCode", mapData.get("RunCode"));
		rc.put("CardId", mapData.get("CardId"));
		rc.put("IMEI", mapData.get("IMEI"));
		rc.put("MobileCode", mapData.get("MobileCode"));
		rc.put("DataArea", mapData.get("DataArea"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	public byte[] requestDataAuth() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("Sign", mapData.get("Sign"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	public byte[] requestDataQueryPrize() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("GameId", mapData.get("GameId"));
		rc.put("TermCode", mapData.get("TermCode"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	public byte[] requestDataQueryPrizeCode() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("GameId", mapData.get("GameId"));
		rc.put("TermCode", mapData.get("TermCode"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	public byte[] requestDataQueryCode() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("GameId", mapData.get("GameId"));
		rc.put("TermCode", mapData.get("TermCode"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	public byte[] requestDataQueryTerm() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("GameId", mapData.get("GameId"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	public byte[] requestDataQueryTicketEx() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("TicketCode", mapData.get("TicketCode"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public byte[] requestDataOrderCreate() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("StationCode", mapData.get("StationCode"));
		rc.put("Barcode", mapData.get("Barcode"));
		rc.put("OutOrderId", mapData.get("OutOrderId"));
		rc.put("OrderMoney", mapData.get("OrderMoney"));
		rc.put("OpreratorId", mapData.get("OpreratorId"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("AreaCode", mapData.get("AreaCode"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public byte[] requestDataOrderQuery() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("StationCode", mapData.get("StationCode"));
		rc.put("OrderId", mapData.get("OrderId"));
		rc.put("OutOrderId", mapData.get("OutOrderId"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("AreaCode", mapData.get("AreaCode"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public byte[] requestDataCTicketOrder() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("OrderNo", mapData.get("OrderNo"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public byte[] requestDataTicketOrder() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("OrderNo", mapData.get("OrderNo"));
		rc.put("OrderType", mapData.get("OrderType"));
		rc.put("GameId", mapData.get("GameId"));
		rc.put("TermCode", mapData.get("TermCode"));
		rc.put("Money", mapData.get("Money"));
		rc.put("PhoneCode", mapData.get("PhoneCode"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("AreaCode", mapData.get("AreaCode"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public byte[] requestDataAgentTicketInfo() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("TicketInfo", mapData.get("TicketInfo"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public byte[] requestDataAgentTicketData() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("TicketInfo", mapData.get("TicketInfo"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public byte[] requestDataImageTicket() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("ImageUrl", mapData.get("ImageUrl"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	private byte[] requestDataAgentTicket() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("TicketCode", mapData.get("TicketCode"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	private byte[] requestDataLottMoney() throws UnsupportedEncodingException{
		byte[] b = null;
		
		JSONObject rc = new JSONObject();
		rc.put("RunCode", mapData.get("RunCode"));
		rc.put("StationId", mapData.get("StationId"));
		rc.put("PaymentAmount", mapData.get("PaymentAmount"));
		rc.put("PaymentDate", mapData.get("PaymentDate"));
		rc.put("PaymentTime", mapData.get("PaymentTime"));
		rc.put("CheckCode", mapData.get("CheckCode"));
		
		JSONObject bd = new JSONObject();
		bd.put("PartnerId", mapData.get("PartnerId"));
		bd.put("TimeStamp", mapData.get("TimeStamp"));
		bd.put("SerialNum", mapData.get("SerialNum"));
		bd.put("Version", mapData.get("Version"));
		bd.put("Token", mapData.get("Token"));
		
		bd.put("ReqContent",rc.toString());
		
		b = threeDES.encryptMode(bd.toString().getBytes("UTF-8"), agentSecretKey);
		return b;
	}
	
	public String getResponseData4Auth() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostAuth(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryPrize() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostQueryPrize(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryPrizeCode() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostQueryPrizeCode(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryCode() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostQueryCode(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryTicketEx() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostQueryTicketEx(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4QueryTerm() throws UnsupportedEncodingException, Exception {
		return new String(threeDES.decryptMode(sendPostQueryTerm(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4ImageTicket() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostImageTicket(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4AgentTicket() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostAgentTicket(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4EncashCheckJkp() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostEncashCheckJkp(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4EncashJkp() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostEncashJkp(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4LottMoney() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostLottMoney(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4AgentTicketInfo()  throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostAgentTicketInfo(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4AgentTicketData() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostAgentTicketData(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4OrderCreate() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostOrderCreate(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4OrderQuery() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostOrderQuery(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4TicketOrder() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostTicketOrder(), agentSecretKey),"UTF-8");
	}
	public String getResponseData4CTicketOrder() throws  UnsupportedEncodingException, Exception{
		return new String(threeDES.decryptMode(sendPostCTicketOrder(), agentSecretKey),"UTF-8");
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