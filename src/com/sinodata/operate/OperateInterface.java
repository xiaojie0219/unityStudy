package com.sinodata.operate;

import java.io.IOException;
import java.util.ArrayList;

import com.sinodata.dataHandle.DataTransferJson;
import com.sinodata.tools.Configuration;
import com.sinodata.tools.FileUtil;
import com.sinodata.tools.HttpRequest;
import com.sinodata.tools.OperateJDBC;
import com.sinodata.tools.ThreeDES;

public class OperateInterface {
	private DataTransferJson dt;
	
	public OperateInterface(){
		dt = new DataTransferJson();
	}
	
	/**
	 * 根据不同的接口名称，获取对应接口参数文件和接口URL地址，发起HTTP请求，并将返回内容插入数据库
	 * @param interfaceType 接口类型（agent,sino,api）
	 * @param interfaceName 接口名称
	 * @throws Exception
	 */
	public void operateInterface(String interfaceType,String interfaceName) throws Exception{
		String secretKey = dt.getSecretKey();
		OperateJDBC oc = new OperateJDBC();
		String url = Configuration.getUrl(interfaceName);
		
		ArrayList<String[]> al = dt.dataTransferJson(interfaceType, interfaceName);
		try {
			int len = al.size();
			for (int i = 0; i < len; i++) {
				String param = al.get(i)[1];
				String response = HttpRequest.sendPost(url, param);
				System.out.println("响应解密前 = " + response);
				
				String data= new String(ThreeDES.decryptMode(ThreeDES.decryptBASE64(response), secretKey),"UTF-8");
				System.out.println("响应解密后 = " + data);
				if(interfaceName.equals("login")){
					FileUtil.writeToFile(data.substring(data.length()-34, data.length()-2),"loginsession.txt");
				}
				//获取HTTP请求的实际返回码
				String actualReturnCode = data.substring(16,22);
				//获取测试用例文件中的预期返回码
				//String expectReturnCode = sTmp[sTmp.length-1];
				String expectReturnCode = al.get(i)[2];
				//判断实际返回码和预期返回码是否一致？
				Boolean successFlag = false;
				if (actualReturnCode.equals(expectReturnCode)) {
					successFlag = true;
				} 
				String description = al.get(i)[0];
				oc.addBatchJDBC(description, param, data, expectReturnCode,
						actualReturnCode, successFlag);
			}
			oc.insertJDBC();
			oc.closeJDBC();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		OperateInterface tif = new OperateInterface();
		try {
			tif.operateInterface("sino", "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
