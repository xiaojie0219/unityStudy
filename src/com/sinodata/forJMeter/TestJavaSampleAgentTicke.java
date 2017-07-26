package com.sinodata.forJMeter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class TestJavaSampleAgentTicke extends AbstractJavaSamplerClient{

	/** Holds the result data (shown as Response Data in the Tree display). */
	private String resultData;

	// 这个方法是用来自定义java方法入参的。
	// params.addArgument("GameId","B001");表示入参名字叫GameId，默认值为B001。
	// 设置可用参数以及它们的默认值；
	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("ipAndPort", "10.10.43.149:8188");
		params.addArgument("agentSecretKey", "0FD2672D2A5A5C4DA5200003");
		params.addArgument("DES3", "zhongxinyinhang123456789");
		
		params.addArgument("PartnerId", "00003");
		params.addArgument("TimeStamp", "2016-04-27 10:39:10");
		params.addArgument("SerialNum", "2016040001113");
		params.addArgument("Version", "1.0.0.0");
		params.addArgument("Token", "92EA48927E3BFA1755A64FBC16B6B901");
		
		params.addArgument("TicketCode", "");
		return params;
	}

	// 每个线程测试前执行一次，做一些初始化工作；
	public void setupTest(JavaSamplerContext arg0) {
	}

	// 开始测试
	public SampleResult runTest(JavaSamplerContext arg0) {
		SampleResult sr = new SampleResult();
		sr.setSampleLabel("Java请求(彩票查询)");//察看结果树的标题显示
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> it = arg0.getParameterNamesIterator();
		while (it.hasNext()){
			String key = (String) it.next();
			String value = arg0.getParameter(key);
			map.put(key, value);
		}
		try {
			sr.sampleStart();// jmeter 开始统计响应时间标记，类似于LR的事务开始点
			//调用HttpRequest原始请求方法
			HttpRequest hr = new HttpRequest(map.get("agentSecretKey"),
					map.get("DES3"), map.get("ipAndPort"),map);
			
			// 通过下面的操作可以将"测试身份验证"输出到Jmeter的察看结果树中的请求里。
			sr.setRequestHeaders("测试彩票查询");
			
			// 通过下面的操作可以将被测方法的响应输出到Jmeter的察看结果树中的响应数据里。
			resultData = String.valueOf(hr.getResponseData4AgentTicket());
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
			sr.sampleEnd();// jmeter 结束统计响应时间标记，类似于LR的事务结束点
		}
		return sr;
	}

	// 测试结束时调用；
	public void teardownTest(JavaSamplerContext arg0) {
		// System.out.println(end);
		// System.out.println("The cost is"+(end-start)/1000);
	}

	// main只是为了调试用，最后打jar包的时候注释掉。
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arguments params = new Arguments();
		params.addArgument("ipAndPort", "10.10.35.146:8188");// 设置参数，并赋予默认值
		params.addArgument("interfaceName", "auth");
		params.addArgument("agentSecretKey", "E433B7E634E78EBB9A467OPU");
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
*/
}
