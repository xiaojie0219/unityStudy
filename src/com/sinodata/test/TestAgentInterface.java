package com.sinodata.test;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.sinodata.dataHandle.AgentDataTransferJson;
import com.sinodata.tools.Configuration;
import com.sinodata.tools.FileUtil;
import com.sinodata.tools.HttpRequest;
import com.sinodata.tools.OperateJDBC;
import com.sinodata.tools.ThreeDES;

public class TestAgentInterface {
	public AgentDataTransferJson agentData = null;
	public String agentSecretKey = null;
	public String loginSession = null;

	TestAgentInterface(){
		agentData = new AgentDataTransferJson();
		agentSecretKey = agentData.agentSecretKey;
	}
	/**
	 * 将循环调用接口写入方法中
	 * @throws Exception 
	 */
	
	@Test
	public void rechargeWithdrawDetailQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.rechargeWithdrawDetailQueryFilePath;
		String url = Configuration.rechargeWithdrawDetailQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.rechargeWithdrawDetailQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void userAccountQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.userAccountQueryFilePath;
		String url = Configuration.userAccountQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.userAccountQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void cancelMoreBet() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.cancelMoreBetFilePath;
		String url = Configuration.cancelMoreBetUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[6] = this.loginSession;
				}
				String param = agentData.cancelMoreBet(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void cancelMoreBetQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.cancelMoreBetQueryFilePath;
		String url = Configuration.cancelMoreBetQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.cancelMoreBetQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void betTakeEffectQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.betTakeEffectQueryFilePath;
		String url = Configuration.betTakeEffectQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.betTakeEffectQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
				oc.addBatchJDBC(sTmp[0],param,data, actualReturnCode,expectReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void synTime() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.synTimeFilePath;
		String url = Configuration.synTimeUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.synTime(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void userTradeQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.userTradeQueryFilePath;
		String url = Configuration.userTradeQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.userTradeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void currentTermWinCodeQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.currentTermWinCodeQueryFilePath;
		String url = Configuration.currentTermWinCodeQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.currentTermWinCodeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void winCodeQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.winCodeQueryFilePath;
		String url = Configuration.winCodeQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.winCodeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void winNoticeQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.winNoticeQueryFilePath;
		String url = Configuration.winNoticeQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.winNoticeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void mutilNewTermQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.mutilNewTermQueryFilePath;
		String url = Configuration.mutilNewTermQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.mutilNewTermQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void winDetailQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.winDetailQueryFilePath;
		String url = Configuration.winDetailQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.winDetailQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void winSellDetailQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.winSellDetailQueryFilePath;
		String url = Configuration.winSellDetailQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.winSellDetailQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void userPromotionQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.userPromotionQueryFilePath;
		String url = Configuration.userPromotionQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.userPromotionQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void voiceLogin() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.voiceLoginFilePath;
		String url = Configuration.voiceLoginUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.voiceLogin(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void changeMobile() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.changeMobileFilePath;
		String url = Configuration.changeMobileUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[6] = this.loginSession;
				}
				String param = agentData.changeMobile(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void mobileFeeToBet() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.mobileFeeToBetFilePath;
		String url = Configuration.mobileFeeToBetUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[6] = this.loginSession;
				}
				String param = agentData.mobileFeeToBet(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void winToMobileFee() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.winToMobileFeeFilePath;
		String url = Configuration.winToMobileFeeUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[6] = this.loginSession;
				}
				String param = agentData.winToMobileFee(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void userQueryUseInfo() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.userQueryUseInfoFilePath;
		String url = Configuration.userQueryUseInfoUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.userQueryUseInfo(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void userNoticeQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.userNoticeQueryFilePath;
		String url = Configuration.userNoticeQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.userNoticeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void promoQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.promoQueryFilePath;
		String url = Configuration.promoQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.promoQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void promoSend() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.promoSendFilePath;
		String url = Configuration.promoSendUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[6] = this.loginSession;
				}
				String param = agentData.promoSend(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void requestSMS() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.requestSMSFilePath;
		String url = Configuration.requestSMSUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.requestSMS(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void rechargeWithdrawQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.rechargeWithdrawQueryFilePath;
		String url = Configuration.rechargeWithdrawQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.rechargeWithdrawQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void tobuyDataQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.tobuyDataQueryFilePath;
		String url = Configuration.tobuyDataQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.tobuyDataQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void bookCommonQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bookCommonQueryFilePath;
		String url = Configuration.bookCommonQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.bookCommonQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void bookQueryCommon() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bookQueryCommonFilePath;
		String url = Configuration.bookQueryCommonUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.bookQueryCommon(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void bookBetMoreQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bookBetMoreQueryFilePath;
		String url = Configuration.bookBetMoreQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.bookBetMoreQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void bookQueryBetMore() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bookQueryBetMoreFilePath;
		String url = Configuration.bookQueryBetMoreUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.bookQueryBetMore(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void bookTobuyQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bookTobuyQueryFilePath;
		String url = Configuration.bookTobuyQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.bookTobuyQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void bookTobuyBetQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bookTobuyBetQueryFilePath;
		String url = Configuration.bookTobuyBetQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.bookTobuyBetQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void termBetQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.termBetQueryFilePath;
		String url = Configuration.termBetQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.termBetQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void tobuyEncashQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.tobuyEncashQueryFilePath;
		String url = Configuration.tobuyEncashQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.tobuyEncashQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void accountPageQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.accountPageQueryFilePath;
		String url = Configuration.accountPageQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.accountPageQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void winAddressQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.winAddressQueryFilePath;
		String url = Configuration.winAddressQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.winAddressQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void winQueryNotice() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.winQueryNoticeFilePath;
		String url = Configuration.winQueryNoticeUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.winNoticeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void commOrderQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.commOrderQueryFilePath;
		String url = Configuration.commOrderQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.commOrderQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void betMoreQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.betMoreQueryFilePath;
		String url = Configuration.betMoreQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.betMoreQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void tobuyQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.tobuyQueryFilePath;
		String url = Configuration.tobuyQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.tobuyQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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
	public void tobuyRuncodeQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.tobuyRuncodeQueryFilePath;
		String url = Configuration.tobuyRuncodeQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = agentData.tobuyRuncodeQuery(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), agentSecretKey),"UTF-8");
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

