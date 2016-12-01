package com.sinodata.forJMeter;

import java.util.HashMap;
import java.util.Map;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class TestJavaSampleAuth extends AbstractJavaSamplerClient {

	/** Holds the result data (shown as Response Data in the Tree display). */
	private String resultData;

	// 这个方法是用来自定义java方法入参的。
	// params.addArgument("GameId","B001");表示入参名字叫GameId，默认值为B001。
	// 设置可用参数以及它们的默认值；
	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("ipAndPort", "10.10.35.146:8188");
		params.addArgument("agentSecretKey", "F734B7E634E78EBB9A467B0E");
		params.addArgument("DES3", "zhongxinyinhang123456789");
		params.addArgument("Sign", "SIKnEgpRix0lXhfXheMqGyZpHqEkojI8V8zTTHfwVl6hklyb2tK99En4ZHlH/hz2UFbV8KR5vrA+pd6XTi5Ujd2ilkbjgSRATCvUg3WcNoQaAsyXYnsFVqbljBV0EAmgRSHGGp3yZBew8t/lM2Hf92VjjvoKpIURdbCICctVK8I=");
		params.addArgument("PartnerId", "00003");
		params.addArgument("TimeStamp", "2016-10-24 15:10:10");
		params.addArgument("SerialNum", "1234567");
		params.addArgument("Version", "1.0.0.0");
		
		return params;
	}

	// 每个线程测试前执行一次，做一些初始化工作；
	public void setupTest(JavaSamplerContext arg0) {
	}

	// 开始测试
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult sr = new SampleResult();
		sr.setSampleLabel("Java请求_调用jar包中的HTTP请求方法(查询开奖公告)");//察看结果树的标题显示
		Map<String, String> map = new HashMap<String, String>();
		map.put("ipAndPort", arg0.getParameter("ipAndPort"));
		map.put("agentSecretKey", arg0.getParameter("agentSecretKey"));
		map.put("DES3", arg0.getParameter("DES3"));
		map.put("Sign", arg0.getParameter("Sign"));
		map.put("PartnerId", arg0.getParameter("PartnerId"));
		map.put("TimeStamp", arg0.getParameter("TimeStamp"));
		map.put("SerialNum", arg0.getParameter("SerialNum"));
		map.put("Version", arg0.getParameter("Version"));
		try {
			sr.sampleStart();// jmeter 开始统计响应时间标记
			HttpRequest hr = new HttpRequest(map.get("agentSecretKey"),
					map.get("DES3"), map.get("ipAndPort"), map);
			
			// 通过下面的操作可以将"请求sendPost4QueryPrize测试开奖公告查询"输出到Jmeter的察看结果树中的请求里。
			sr.setRequestHeaders("请求requestDataAuth测试身份验证");
			
			// 通过下面的操作可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里。
			resultData = String.valueOf(hr.getResponseDataAuth());
			if (resultData != null && resultData.length() > 0) {
				sr.setResponseData("结果是：" + resultData, null);
				sr.setDataType(SampleResult.TEXT);
			}
			 System.out.println("响应解密后：" + resultData);
			sr.setSuccessful(true);
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

	// main只是为了调试用，最后打jar包的时候注释掉。

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arguments params = new Arguments();
		params.addArgument("ipAndPort", "10.10.35.146:8188");// 设置参数，并赋予默认值
		params.addArgument("agentSecretKey", "F734B7E634E78EBB9A467B0E");
		params.addArgument("DES3", "zhongxinyinhang123456789");
		params.addArgument(
				"Sign",
				"SIKnEgpRix0lXhfXheMqGyZpHqEkojI8V8zTTHfwVl6hklyb2tK99En4ZHlH/hz2UFbV8KR5vrA+pd6XTi5Ujd2ilkbjgSRATCvUg3WcNoQaAsyXYnsFVqbljBV0EAmgRSHGGp3yZBew8t/lM2Hf92VjjvoKpIURdbCICctVK8I=");
		params.addArgument("PartnerId", "00003");
		params.addArgument("TimeStamp", "2016-10-24 15:10:10");
		params.addArgument("SerialNum", "1234567");
		params.addArgument("Version", "1.0.0.0");

		JavaSamplerContext arg0 = new JavaSamplerContext(params);

		TestJavaSampleAuth tjs = new TestJavaSampleAuth();
		tjs.setupTest(arg0);
		tjs.runTest(arg0);
		tjs.teardownTest(arg0);
	}

}
