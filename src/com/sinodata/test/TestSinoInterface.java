package com.sinodata.test;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.sinodata.dataHandle.SinoDataTransferJson;
import com.sinodata.tools.Configuration;
import com.sinodata.tools.FileUtil;
import com.sinodata.tools.HttpRequest;
import com.sinodata.tools.OperateJDBC;
import com.sinodata.tools.ThreeDES;

public class TestSinoInterface {
	public SinoDataTransferJson sinoData = null;
	public String pubSecretKey = null;
	public String loginSession = null;

	TestSinoInterface(){
		sinoData = new SinoDataTransferJson();
		pubSecretKey = sinoData.pubSecretKey;
	}
	
	/**
	 * 将循环调用接口写入方法中
	 * @throws Exception 
	 */
	
	@Test
	public void loginCheck() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.loginCheckFilePath;
		String url = Configuration.loginCheckUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.loginCheck(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				this.loginSession = data.substring(data.length()-34, data.length()-2);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void cashAccBusiness() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.cashAccBusinessFilePath;
		String url = Configuration.cashAccBusinessUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[7] = this.loginSession;
				}
				String param = sinoData.cashAccBusiness(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void stationAgentQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.stationAgentQueryFilePath;
		String url = Configuration.stationAgentQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.stationAgentQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void noticeQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.noticeQueryFilePath;
		String url = Configuration.noticeQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.noticeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void uptPass() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.uptPassFilePath;
		String url = Configuration.uptPassUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[5] = this.loginSession;
				}
				String param = sinoData.uptPass(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void accPageQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.accPageQueryFilePath;
		String url = Configuration.accPageQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.accPageQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void agentSellQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.agentSellQueryFilePath;
		String url = Configuration.agentSellQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.agentSellQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryUserPage() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.queryUserPageFilePath;
		String url = Configuration.queryUserPageUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.queryUserPage(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void cashApply() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.cashApplyFilePath;
		String url = Configuration.cashApplyUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[7] = this.loginSession;
				}
				String param = sinoData.cashApply(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryUseInfo() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.queryUseInfoFilePath;
		String url = Configuration.queryUseInfoUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.queryUseInfo(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryAgentList() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.queryAgentListFilePath;
		String url = Configuration.queryAgentListUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = sinoData.queryAgentList(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				oc.addBatchJDBC(sTmp[0], param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
