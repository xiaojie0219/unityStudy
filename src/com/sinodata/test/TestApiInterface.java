package com.sinodata.test;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.sinodata.dataHandle.ApiDataTransferJson;
import com.sinodata.tools.Configuration;
import com.sinodata.tools.FileUtil;
import com.sinodata.tools.HttpRequest;
import com.sinodata.tools.OperateJDBC;
import com.sinodata.tools.ThreeDES;

public class TestApiInterface {
	public ApiDataTransferJson apiData = null;
	public String pubSecretKey = null;
	public String loginSession = null;

	TestApiInterface(){
		apiData = new ApiDataTransferJson();
		pubSecretKey = apiData.pubSecretKey;
	}
	/**
	 * 将循环调用接口写入方法中
	 * @throws Exception 
	 */
	
	@Test
	public void register() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.registerFilePath;
		String url = Configuration.registerUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = apiData.register(sTmp);
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
	public void login() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.loginFilePath;
		String url = Configuration.loginUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = apiData.login(sTmp);
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
	public void teminaLogin() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.teminaLoginFilePath;
		String url = Configuration.teminaLoginUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = apiData.teminaLogin(sTmp);
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
	public void bindBank() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bindBankFilePath;
		String url = Configuration.bindBankUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[7] = this.loginSession;
				}
				String param = apiData.bindBank(sTmp);
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
	public void recharge() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.rechargeFilePath;
		String url = Configuration.rechargeUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[3] = this.loginSession;
				}
				String param = apiData.recharge(sTmp);
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
	public void cash() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.cashFilePath;
		String url = Configuration.cashUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[4] = this.loginSession;
				}
				String param = apiData.cash(sTmp);
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
	public void bindBankQuery() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.bindBankQueryFilePath;
		String url = Configuration.bindBankQueryUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[3] = this.loginSession;
				}
				String param = apiData.bindBankQuery(sTmp);
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
	public void unbundlingBank() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.unbundlingBankFilePath;
		String url = Configuration.unbundlingBankUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[3] = this.loginSession;
				}
				String param = apiData.unbundlingBank(sTmp);
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
	public void updateMobile() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.updateMobileFilePath;
		String url = Configuration.updateMobileUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[5] = this.loginSession;
				}
				String param = apiData.updateMobile(sTmp);
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
	public void betTakeEffect() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.betTakeEffectFilePath;
		String url = Configuration.betTakeEffectUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[7] = this.loginSession;
				}
				String param = apiData.betTakeEffect(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断预期返回码和实际返回码是否一致？
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
	public void betMoreTakeEffect() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.betMoreTakeEffectFilePath;
		String url = Configuration.betMoreTakeEffectUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[8] = this.loginSession;
				}
				String param = apiData.betMoreTakeEffect(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断预期返回码和实际返回码是否一致？
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
	public void loginOut() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.loginOutFilePath;
		String url = Configuration.loginOutUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[3] = this.loginSession;
				}
				String param = apiData.loginOut(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断预期返回码和实际返回码是否一致？
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
	public void tobuyBet() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.tobuyBetFilePath;
		String url = Configuration.tobuyBetUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[18] = this.loginSession;
				}
				String param = apiData.tobuyBet(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断预期返回码和实际返回码是否一致？
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
	public void updateUserName() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.updateUserNameFilePath;
		String url = Configuration.updateUserNameUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[4] = this.loginSession;
				}
				String param = apiData.updateUserName(sTmp);
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
	public void updatePassword() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.updatePasswordFilePath;
		String url = Configuration.updatePasswordUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[6] = this.loginSession;
				}
				String param = apiData.updatePassword(sTmp);
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
	public void retrievePass() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.retrievePassFilePath;
		String url = Configuration.retrievePassUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = apiData.retrievePass(sTmp);
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
	public void tobuySponsor() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.tobuySponsorFilePath;
		String url = Configuration.tobuySponsorUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[17] = this.loginSession;
				}
				String param = apiData.tobuySponsor(sTmp);
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
	public void tobuyJoin() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.tobuyJoinFilePath;
		String url = Configuration.tobuyJoinUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[8] = this.loginSession;
				}
				String param = apiData.tobuyJoin(sTmp);
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
	public void queryOrder() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.queryOrderFilePath;
		String url = Configuration.queryOrderUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[2] = this.loginSession;
				}
				String param = apiData.queryOrder(sTmp);
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
	public void teminalCash() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.teminalCashFilePath;
		String url = Configuration.teminalCashUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				//如先执行login方法则产生loginSession值，则使用这个值
				if(this.loginSession != null && !this.loginSession.equals("")){
					sTmp[5] = this.loginSession;
				}
				String param = apiData.teminalCash(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = "";//data.substring(16,22);
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
	public void cancelAccount() throws Exception {
		OperateJDBC oc = new OperateJDBC();
		String filepath = Configuration.cancelAccountFilePath;
		String url = Configuration.cancelAccountUrl;
		ArrayList<String> alTmp;
		try {
			alTmp = FileUtil.getFileContent(filepath);
			int len = alTmp.size();
			String[] strArrayTmp = (String[]) alTmp.toArray(new String[len]);
			for (int i = 0; i < len; i++) {
				String[] sTmp = strArrayTmp[i].split(",");
				String param = apiData.cancelAccount(sTmp);
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), pubSecretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				String expectReturnCode = sTmp[sTmp.length-1];
				//判断预期返回码和实际返回码是否一致？
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

