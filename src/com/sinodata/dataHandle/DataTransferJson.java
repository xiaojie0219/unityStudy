package com.sinodata.dataHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.sinodata.tools.Configuration;
import com.sinodata.tools.FileUtil;
import com.sinodata.tools.GetRunCode;
import com.sinodata.tools.PropertiesUtil;
import com.sinodata.tools.ThreeDES;
import com.sinodata.tools.UtilsList;

public class DataTransferJson {
	private PropertiesUtil pu;
	private String secretKey;
	
	public DataTransferJson(){
		pu = new PropertiesUtil();
	}
	
	/**
	 * 根据接口类型和接口名称，获取接口的参数文件路径并读取文件内容，并组装参数再进行JSON加密处理
	 * @param interfaceType 接口类型（agent,sino,api）
	 * @param interfaceName 接口名称
	 * @return 返回ArrayList<String[]>(description、经过json加密后的字符串、expectReturnCode)
	 * @throws Exception
	 */
	public ArrayList<String[]> dataTransferJson(String interfaceType,String interfaceName) throws Exception{
		setSecretKey(interfaceType);
		String filePath = Configuration.getFilePath(interfaceName);
		ArrayList<String[]> al = new ArrayList<String[]>();
		try {
			ArrayList<String[]> alfp = FileUtil.readeCsv(filePath);
			String[] str = new String[3];
			//获取文件中第一行的各参数字段
			String[] param = alfp.get(0);
			
			
			//对文件中除第一行外的数据进行组装加密返回
			for(int i=1;i<alfp.size();i++){
				String[] paramValue = alfp.get(i);
				str[0] = paramValue[0];//每行的第1列为description
				str[2] = paramValue[paramValue.length-1];//每行的最后1列为预期返回码，expectReturnCode
				StringBuffer sbTmp = new StringBuffer();
				String head = "";
				//将需要传入agenttype的这部分接口分开处理
				if(interfaceName.equals("loginCheck")||interfaceName.equals("noticeQuery")
						||interfaceName.equals("uptPass")||interfaceName.equals("accPageQuery")
						||interfaceName.equals("agentSellQuery")||interfaceName.equals("cashApply")){
					str[2] = paramValue[param.length -1];
					head = param[1] + "=" + paramValue[1] + "&" + param[2] + "=" + paramValue[2] + "&" + "paramcontent=";
					sbTmp.append("{");
					for(int j=3;j<param.length;j++){
						sbTmp.append("\"").append(param[j]).append("\":\"").append(paramValue[j]).append("\",");
					}
					sbTmp.deleteCharAt(sbTmp.length()-1);//删除最后一个多余的逗号
					sbTmp.append("}");
					
					String sendTmp = sbTmp.toString();
					System.out.println("发送加密前 = " + head + sendTmp);
					sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),secretKey));
					System.out.println("发送加密后 = " + sendTmp);
					str[1] = head + sendTmp;//为经过JSON加密后的HTTP请求参数数据
					al.add(str);
				}else if(interfaceName.equals("singleSell")){
					head = param[1] + "=" + paramValue[1] + "&" + "paramcontent=";
					sbTmp.append("{");
					for(int j=2;j<param.length;j++){
						if(param[j].equals("runcode")){
							paramValue[j] = GetRunCode.getRunCode();
						}
						if(param[j].equals("loginsession")){
							paramValue[j] = FileUtil.readFileToString("loginsession.txt");
						}
						if(param[j].equals("codeinfo")){
							StringBuffer temp = new StringBuffer();
							temp.append(GetRunCode.getRunCode()).append("\t");
							temp.append(paramValue[j]);
							paramValue[j] = temp.toString();
						}
						sbTmp.append("\"").append(param[j]).append("\":\"").append(paramValue[j]).append("\",");
					}
					sbTmp.append("\"").append("checkcode").append("\":\"");
					sbTmp.append(UtilsList.getCheckCode(paramValue[1]+paramValue[2]+paramValue[3]+paramValue[12]+paramValue[8]+paramValue[7])).append("\",");
					sbTmp.append("\"").append("promolist").append("\":").append("[]");
					sbTmp.append("}");
					
					String sendTmp = sbTmp.toString();
					System.out.println("发送加密前 = " + head + sendTmp);
					sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),secretKey));
					System.out.println("发送加密后 = " + sendTmp);
					str[1] = head + sendTmp;//为经过JSON加密后的HTTP请求参数数据
					al.add(str);
				}else{
					head = param[1] + "=" + paramValue[1] + "&" + "paramcontent=";
					sbTmp.append("{");
					for(int j=2;j<param.length;j++){
						sbTmp.append("\"").append(param[j]).append("\":\"").append(paramValue[j]).append("\",");
					}
					sbTmp.deleteCharAt(sbTmp.length()-1);//删除最后一个多余的逗号
					sbTmp.append("}");
					
					String sendTmp = sbTmp.toString();
					System.out.println("发送加密前 = " + head + sendTmp);
					sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),secretKey));
					System.out.println("发送加密后 = " + sendTmp);
					str[1] = head + sendTmp;//为经过JSON加密后的HTTP请求参数数据
					al.add(str);
				}
			}
			return al;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void setSecretKey(String interfaceType){
		if(interfaceType.equals("agent")){
			secretKey = pu.read("config.properties", "agentSecretKey");
		}else{
			secretKey = pu.read("config.properties", "pubSecretKey");
		}
	}
	
	public String getSecretKey(){
		return this.secretKey;
	}
	public static void main(String[] args){
		DataTransferJson dt = new DataTransferJson();
		try {
			ArrayList<String[]> al = dt.dataTransferJson("api", "singleSell");
			Iterator<String[]> it = al.iterator();
			while(it.hasNext()){
				String[] str = it.next();
				for(int i =0;i<str.length;i++){
					System.out.print("第" + i + "个元素是：" + str[i]);
				}
				System.out.println();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

