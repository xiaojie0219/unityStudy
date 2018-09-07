package com.sinodata.mobileTest;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import net.sf.json.JSONObject;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class JavaSampleFilter extends AbstractJavaSamplerClient {

	/** Holds the result data (shown as Response Data in the Tree display). */
	String resultData;
	ThreeDES threeDes;
	String url;//完整的请求URL
	String requestData;//加密前的请求数据
	byte[] byteArrayBodyData;//加密后的请求数据
	
	/**
	 * 这个方法是用来自定义java方法入参的。
	 * params.addArgument("GameId","B001");表示入参名字叫GameId，默认值为B001。
	 * 设置可用参数以及它们的默认值；
	 */
	public Arguments getDefaultParameters() {
		Arguments arguments = new Arguments();
		arguments.addArgument("ipAndPort", "${ipAndPort}");
		arguments.addArgument("uri","${uri}");
		arguments.addArgument("agentSecretKey", "${agentSecretKey}");
		
		arguments.addArgument("PartnerId", "${PartnerId}");
		arguments.addArgument("TimeStamp", "${TimeStamp}");
		arguments.addArgument("SerialNum", "${SerialNum}");
		arguments.addArgument("Version", "${Version}");
		arguments.addArgument("Token", "${Token}");
		
		arguments.addArgument("GameId", "null");
		arguments.addArgument("TermCode", "null");
		arguments.addArgument("TicketCode", "null");
		arguments.addArgument("TicketInfo", "null");
		arguments.addArgument("RunCode", "null");
		arguments.addArgument("CardId", "null");
		arguments.addArgument("IMEI", "null");
		arguments.addArgument("MobileCode", "null");
		arguments.addArgument("DataArea", "null");
		arguments.addArgument("Channel", "null");
		arguments.addArgument("DataArea", "null");
		
		return arguments;
	}

	/**
	 * 每个线程测试前执行一次，做一些初始化工作。
	 * 初始化3DES，组装基本参数、业务参数和完整的请求url地址
	 * 
	 */
	public void setupTest(JavaSamplerContext arg0) {
		try {
			threeDes = new ThreeDES(arg0.getParameter("agentSecretKey"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//请求数据
		requestData = getBasicContent(arg0);
		//加密请求数据
		try {
			byteArrayBodyData = threeDes.encryptMode(requestData.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setUrl(arg0);//组装请求URL
	}

	/**
	 * 开始测试
	 */
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult sr = new SampleResult();
		sr.setSampleLabel(url);//察看结果树的标题显示
		try {
			// 通过下面的操作可以将"开奖公告查询"输出到Jmeter的察看结果树中的请求里。
			sr.setRequestHeaders(requestData);
			
			sr.sampleStart();// jmeter 开始统计响应时间标记
			//发起http POST请求并获取未解密的响应内容
			byte[] byteArrayResponseData =HttpRequest.sendPost(url, byteArrayBodyData);
			//解密响应内容
			// 通过下面的操作可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里。
			resultData = new String(threeDes.decryptMode(byteArrayResponseData),"UTF-8");
			if (resultData != null && resultData.length() > 0) {
				sr.setResponseData(resultData, null);
				sr.setDataType(SampleResult.TEXT);
				sr.setSuccessful(true);
				
				/**
				if (resultData.contains("SUCCESS")){
					sr.setSuccessful(true);//设置测试结果标记为成功，则在察看结果树中显示为绿色。如标记为失败，则显示为红色
				}else{
					sr.setSuccessful(false);
				}
				*/
			}
			
//			 System.out.println("响应解密后：" + resultData);
		} catch (Throwable e) {
			sr.setSuccessful(false);
			e.printStackTrace();
		} finally {
			sr.sampleEnd();// jmeter 结束统计响应时间标记
		}
		return sr;
	}

	// 测试结束时调用；
	public void teardownTest(JavaSamplerContext arg0) {
		// System.out.println(end);
		// System.out.println("The cost is"+(end-start)/1000);
	}
	
	/**
	 * 组装获取请求参数basicContent（包含业务参数reqContent）
	 * @param arg0
	 * @return 请求参数basicContent字符串（包含业务参数reqContent）
	 */
	public String getBasicContent(JavaSamplerContext arg0){
		JSONObject basicContent = new JSONObject();
		//业务参数
		JSONObject reqContent = new JSONObject();
		Iterator<String> it = arg0.getParameterNamesIterator();
		while (it.hasNext()){
			String key = (String) it.next();
			if ("PartnerId".equals(key) || "TimeStamp".equals(key)
					|| "SerialNum".equals(key) || "Version".equals(key)
					|| "Token".equals(key) || "AreaCode".equals(key)) {
				String value = arg0.getParameter(key);
				basicContent.put(key, value);
			}else if(!"ipAndPort".equals(key) && !"agentSecretKey".equals(key)
					&& !"uri".equals(key) && !"TestElement.name".equals(key)){
				String value = arg0.getParameter(key);
				if (!"null".equals(value)) {
					reqContent.put(key, value);
				}
			}
		}
		basicContent.put("ReqContent", reqContent.toString());
		return basicContent.toString();
	}
	
	/**
	 * 组装完整的请求URL地址
	 * @param arg0
	 * @return
	 */
	public void setUrl(JavaSamplerContext arg0) {
		url = "http://" + arg0.getParameter("ipAndPort")
				+ arg0.getParameter("uri") + "partnerId="
				+ arg0.getParameter("PartnerId") + "&hashType=md5&hash="
				+ MD5Security.getMD5String(byteArrayBodyData);
	}
	
	public String getUrl(){
		return url;
	}
}
