package com.sinodata.forJMeter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class TestJavaSampleLottMoney extends AbstractJavaSamplerClient {

	/** Holds the result data (shown as Response Data in the Tree display). */
	private String resultData;

	// 这个方法是用来自定义java方法入参的。
	// params.addArgument("GameId","B001");表示入参名字叫GameId，默认值为B001。
	// 设置可用参数以及它们的默认值；
	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("ipAndPort", "10.10.35.146:8188");
		params.addArgument("agentSecretKey", "0FD2672D2A5A5C4DA5200003");
		params.addArgument("DES3", "zhongxinyinhang123456789");
		
		params.addArgument("PartnerId", "00003");
		params.addArgument("TimeStamp", "2016-04-27 10:39:10");
		params.addArgument("SerialNum", "2016040001113");
		params.addArgument("Version", "1.0.0.0");
		params.addArgument("Token", "92EA48927E3BFA1755A64FBC16B6B901");
		
		params.addArgument("RunCode", "");
		params.addArgument("StationId", "");
		params.addArgument("PaymentAmount", "");
		params.addArgument("PaymentDate", "");
		params.addArgument("PaymentTime", "");
		params.addArgument("CheckCode", "default");
		return params;
	}

	// 每个线程测试前执行一次，做一些初始化工作；
	public void setupTest(JavaSamplerContext arg0) {
	}

	// 开始测试
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult sr = new SampleResult();
		sr.setSampleLabel("Java请求(站点缴款)");//察看结果树的标题显示
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> it = arg0.getParameterNamesIterator();
		while (it.hasNext()){
			String key = (String) it.next();
			String value = arg0.getParameter(key);
			if ("CheckCode".equals(key)){
				if("default".equals(value)){
					UtilsList ul = new UtilsList();
					StringBuffer sb = new StringBuffer();
					sb.append(arg0.getParameter("RunCode")).append("|");
					sb.append(arg0.getParameter("StationId")).append("|");
					sb.append(arg0.getParameter("PaymentAmount")).append("|");
					sb.append(arg0.getParameter("PaymentDate")).append("|");
					sb.append(arg0.getParameter("PaymentTime")).append("|");
					map.put(key, ul.getCheckCode(sb.toString()));
				}else{
					map.put(key, value);
				}
			}else{
				map.put(key, value);
			}
		}
		
		try {
			sr.sampleStart();// jmeter 开始统计响应时间标记
			HttpRequest hr = new HttpRequest(map.get("agentSecretKey"),
					map.get("DES3"), map.get("ipAndPort"),map);
			
			// 通过下面的操作可以将"测试查询彩票信息"输出到Jmeter的察看结果树中的请求里。
			sr.setRequestHeaders("测试站点缴款");
			
			// 通过下面的操作可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里。
			String uri = "/access/lottMoney?";
			resultData = String.valueOf(hr.getResponseData(uri));
			if (resultData != null && resultData.length() > 0 ) {
				sr.setResponseData(resultData, null);
				sr.setDataType(SampleResult.TEXT);
				if (resultData.contains("SUCCESS")){
					sr.setSuccessful(true);
				}else{
					sr.setSuccessful(false);
				}
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

}
