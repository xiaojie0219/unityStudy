package com.sinodata.dataHandle;

import com.sinodata.tools.PropertiesUtil;
import com.sinodata.tools.ThreeDES;
import com.sinodata.tools.UtilsList;

/**
 * 将投注商接口 按接口定义将数据经过JSON加密后返回
 * @author Administrator
 *
 */
public class SinoDataTransferJson {
	public PropertiesUtil pu = null;
	public String pubSecretKey = null;
	
	public SinoDataTransferJson(){
		pu = new PropertiesUtil();
		pubSecretKey = pu.read("config.properties", "pubSecretKey");
	}
	
	/**
	 * 登录接口传参组合并加密JSON
	 * @param str 传入参数字符串数组
	 * @return 加密后的HTTP请求数据
	 */
	
	public String loginCheck(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//商家ID/投注商ID/投注站号
			String agenttype = str[2];//商家类型（1-投注商、2-推广商）
			String loginpass = str[3];//商家登录密码(MD5加密值)
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "agenttype=" + agenttype + "&" + "paramcontent=";
			sbTmp.append("{\"loginpass\":\"").append(loginpass).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String cashAccBusiness(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//推广商ID\站点机号
			String tradepass = str[2];//商家交易密码(MD5加密值)
			String runcode = str[3]; //流水号(由商家服务平台生成，唯一)
			String useridtype = str[4]; //用户身份类型(1-彩民身份证号码、2-手机号码、3-用户ID、4-用户名)
			String userid = str[5]; //彩民身份证号码\手机号码\用户ID\用户名
			String money = str[6]; //现金金额
			//校验码(思乐公司提供校验函数，输入值：流水号、代销商ID、用户身份类型、用户ID、现金金额)
			String loginsession = str[7] ;//动态sessionid
			String checkcode = UtilsList.getCheckCode(runcode+agentid+useridtype+userid+money); 
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"tradepass\":\"").append(tradepass).append("\",\"");
			sbTmp.append("runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("useridtype\":\"").append(useridtype).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("money\":\"").append(money).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String stationAgentQuery(String[] str){
		try
		{
			String agentid = str[1];//投注站推广商ID/站点机号 
			String head = "agentid=" + agentid;
			return head;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String noticeQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//商家ID/投注商ID/投注站号
			String agenttype = str[2];//商家类型（1-投注商、2-推广商）
			String starttime = str[3]; //查询条件操作时间(2013-06-28 10:00:00)
			String endtime = str[4]; //查询条件操作结束时间(2013-06-28 10:00:00)
			String currentrow = str[5];//当前行，默认为0
			String rowcount = str[6]; //每页行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" +"agenttype=" + agenttype + "&" + "paramcontent=";
			sbTmp.append("{\"starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String uptPass(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//商家ID/投注商ID/投注站号
			String agenttype = str[2];//商家类型（1-投注商、2-推广商）
			String oldpassword = str[3]; //旧密码要输入MD5加密后的值char(32)
			String newpassword = str[4]; //新密码要输入MD5加密后的值char(32)
			String loginsession = str[5];//动态sessionid
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" +"agenttype=" + agenttype + "&" + "paramcontent=";
			sbTmp.append("{\"oldpassword\":\"").append(oldpassword).append("\",\"");
			sbTmp.append("newpassword\":\"").append(newpassword).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String accPageQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//代销商ID/投注商ID/投注站号
			String agenttype = str[2];//代销商类型（1-投注商、2-推广商）char(6)
			String starttime = str[3]; //查询条件操作时间(2013-06-28 10:00:00)
			String endtime = str[4]; //查询条件操作时间(2013-06-28 10:00:00)
			//businesstype交易类型，0:现金预充值,1:返代消费,2:彩民在自助终端充值，3：彩民在自助终端提现,
			//4：代销费提现解冻，5：促销增加预充值,6:代销费提现,7:代消费提现冻结,8促销赠金,9发行账户转账到热线账户,
			//10发行账户转账到热线账户冻结，11转账解冻，12:代为彩民充值的扣款,13热线账户转账到发行账户,
			//14热线账户转账到发行账户冻结
			String businesstype = str[5]; 
			String currentrow = str[6]; //当前行，默认为0
			String rowcount = str[7]; //获取行数，默认为10
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" +"agenttype=" + agenttype + "&" + "paramcontent=";
			sbTmp.append("{\"starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("businesstype\":\"").append(businesstype).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String agentSellQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//推广商ID/投注商ID
			String agenttype = str[2];//商家类型（1-投注商、2-推广商）
			String agentid1 = str[3];//投注商ID（如果agenttype1为投注商，则该字段必须为空）
			String accesstype = str[4];//接入类型(1-网站，2-自助终端，3-手机，4-电话, 5-手机短信)
			String playlist = str[5].replace(" ",","); //玩法英文名列表(all-代表所有玩法，多个玩法以逗号分隔)
			String startterm = str[6]; //开始期号
			String endterm = str[7];//结束期号
			String currentrow = str[8];//当前行，默认为0
			String rowcount = str[9]; //每页行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" +"agenttype=" + agenttype + "&" + "paramcontent=";
			sbTmp.append("{\"agentid\":\"").append(agentid1).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("playlist1\":\"").append(playlist).append("\",\"");
			sbTmp.append("startterm\":\"").append(startterm).append("\",\"");
			sbTmp.append("endterm\":\"").append(endterm).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String queryUserPage(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//推广商ID/投注站号
			String starttime = str[2]; //开始时间(20140808)
			String endtime = str[3];//结束时间(20140808)
			String currentrow = str[4];//当前行，默认为0
			String rowcount = str[5]; //每页行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String cashApply(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//推广商ID/投注商ID
			String agenttype = str[2];//商家类型（1-投注商、2-推广商）
			String tradepass = str[3];//商家交易密码(MD5加密值)
			String accesstype = str[4];//接入类型(1-网站，2-自助终端，3-手机APP，4-电话语音, 5-手机短信)
			String runcode = str[5]; //流水号(由商家服务平台生成，唯一)
			String money = str[6]; //提现金额
			//校验码(思乐公司提供校验函数，输入值：流水号、代销商ID、提现金额)
			String loginsession = str[7];//动态sessionid
			//校验码(思乐公司提供校验函数，输入值：流水号、商家ID、提现金额)
			String checkcode = UtilsList.getCheckCode(runcode+agentid+money); 
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" +"agenttype=" + agenttype + "&" + "paramcontent=";
			sbTmp.append("{\"tradepass\":\"").append(tradepass).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("money\":\"").append(money).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String queryUseInfo(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentId = str[1];//商家ID/投注站号
			String userid = str[2];//彩民身份证号码/手机号/用户ID
			String sendTmp = "";
			
			head = "agentid=" + agentId + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\"}");
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),pubSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String queryAgentList(String[] str){
		try
		{
			String agentId = str[1];//推广商ID 
			String head = "agentid=" + agentId;
			
			return head;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
