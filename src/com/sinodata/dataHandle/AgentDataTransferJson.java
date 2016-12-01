package com.sinodata.dataHandle;

import com.sinodata.tools.PropertiesUtil;
import com.sinodata.tools.ThreeDES;
import com.sinodata.tools.UtilsList;

public class AgentDataTransferJson {
	public PropertiesUtil pu = null;
	public String agentSecretKey = null;
	
	public AgentDataTransferJson(){
		pu = new PropertiesUtil();
		agentSecretKey = pu.read("config.properties", "agentSecretKey");
	}
	
	/**
	 * 登录接口传参组合并加密JSON
	 * @param str 传入参数字符串数组
	 * @return 加密后的HTTP请求数据
	 */
	public String rechargeWithdrawDetailQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String querytype = str[3];//查询类型(1-充值，2-提现)
			String startdate = str[4];//充值或提现查询结束日期(20131115)
			String enddate = str[5];//充值或提现查询结束日期(20131115)
			String currentrow = str[6];//当前行，默认为0
			String rowcount = str[7];//获取行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("querytype\":\"").append(querytype).append("\",\"");
			sbTmp.append("startdate\":\"").append(startdate).append("\",\"");
			sbTmp.append("enddate\":\"").append(enddate).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String userAccountQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String cancelMoreBet(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String cancelruncode = str[2];//多期撤销流水号(由投注商生成，唯一)账户类流水号
			String runcode = str[3];//投注订单号(由投注商生成，唯一)
			String userid = str[4];//用户ID/用户名/身份证号码/手机号码
			String accesstype = str[5];//接入类型(1-网站，2-自助终端，3-手机APP，4-电话语音, 5-手机短信)
			String loginsession = str[6];//动态sessionid
			String canceltime = str[7];//撤销时间(20130628101112)
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"cancelruncode\":\"").append(cancelruncode).append("\",\"");
			sbTmp.append("runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("canceltime\":\"").append(canceltime).append("\"}");
			
			String sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String cancelMoreBetQuery(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String cancelruncode = str[2];//多期撤销流水号
			String userid = str[3];//用户ID
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"cancelruncode\":\"").append(cancelruncode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\"}");
			
			String sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String betTakeEffectQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注流水号(由投注商生成，唯一)
			String userid = str[3];//用户ID
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String synTime(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			
			String head = "agentid=" + agentid ;
			
			return head;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String userTradeQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			//操作类型（0:投注,1:兑奖,2:彩民线上充值,3:线上提现,4:销售冻结,5:销售解冻,
			//6:提现冻结,7:提现解冻,8:促销投注赠送,9:促销回收,10:充值赠送,11：注册赠送,
			//12：彩民现金充值,13：代彩民现金充值,14:彩民现金提现15:提现冻结扣款,
			//16提现解冻返款，17提现解冻扣款， 99-全部）
			String operationtype = str[3];
			String starttime = str[4];//开始时间(2014-08-08 10:00:00)
			String endtime = str[5];//结束时间(2014-08-08 10:00:00)
			String currentrow = str[6];//当前行，默认为0
			String rowcount = str[7];//获取行数，默认为10
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("operationtype\":\"").append(operationtype).append("\",\"");
			sbTmp.append("starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String currentTermWinCodeQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String playlist = str[2].replace(" ", ",");//玩法英文名，不能为空，多个玩法逗号分隔
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"playlist\":\"").append(playlist).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String winCodeQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String playename = str[2];//玩法英文名
			String startterm = str[3];//开始期号
			String currentrow = str[4];//当前行，默认为0
			String rowcount = str[5];//获取行数，默认为10
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"playename\":\"").append(playename).append("\",\"");
			sbTmp.append("startterm\":\"").append(startterm).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String winNoticeQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String playename = str[2];//玩法英文名
			String selltermcode = str[3];//销售期号
			String status = str[4];//地区标识（0：查本省,1：查全国）
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\",\"");
			sbTmp.append("status\":\"").append(status).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String mutilNewTermQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String playlist = str[2].replace(" ", ",");//玩法英文名，不能为空，多个玩法逗号分隔
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"playlist\":\"").append(playlist).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String winDetailQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String isallagent = str[2];//是否查询所有投注商信息(0-否，1-是)
			String playlist = str[3];//玩法列表
			String userid = str[4];//用户ID(all-代表此投注商所有用户中奖数据)
			String validtermcode = str[5];//期号(all-代表所有期)
			String currentrow = str[6];//当前行，默认为0
			String rowcount = str[7];//每页行数，默认为10
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"isallagent\":\"").append(isallagent).append("\",\"");
			sbTmp.append("playlist\":\"").append(playlist).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("validtermcode\":\"").append(validtermcode).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String winSellDetailQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String ticketcode = str[3];//票号
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("ticketcode\":\"").append(ticketcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String userPromotionQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String voiceLogin(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String password = str[3];//登录密码
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("password\":\"").append(password).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String changeMobile(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String password = str[3];//交易密码
			String mobile = str[4];//手机号
			String accesstype = str[5];//接入类型(4-电话语音，5-短信)
			String loginsession = str[6];//动态sessionid(短信接入方式填0)
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("password\":\"").append(password).append("\",\"");
			sbTmp.append("mobile\":\"").append(mobile).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String mobileFeeToBet(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//流水号(由代销商生成，唯一)
			String userid = str[3];//用户ID
			String money = str[4];//金额
			String accesstype = str[5];//接入类型(4-电话语音，5-短信)
			String loginsession = str[6];//动态sessionid(短信接入方式填0)
			//校验码(思乐公司提供校验函数，输入值：流水号、代销商ID、用户ID、金额)
			String checkcode = UtilsList.getCheckCode(runcode+agentid+userid+money);
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("money\":\"").append(money).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String winToMobileFee(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//流水号(由代销商生成，唯一)
			String userid = str[3];//用户ID
			String money = str[4];//金额
			String accesstype = str[5];//接入类型(4-电话语音，5-短信)
			String loginsession = str[6];//动态sessionid(短信接入方式填0)
			String checkcode = str[7];//校验码(思乐公司提供校验函数，输入值：流水号、代销商ID、用户ID、金额)
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("money\":\"").append(money).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String userQueryUseInfo(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//彩民身份证号码/手机号/用户ID
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String userNoticeQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//商家ID/投注商ID/投注站号
			String userid = str[2];//彩民ID
			String starttime = str[3];//开始时间(2014-08-08 10:00:00) 
			String endtime = str[4];//结束时间(2014-08-08 10:00:00) 
			String currentrow = str[5];//当前行，默认为0
			String rowcount = str[6];//每页行数，默认为10
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String promoQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//商家ID/投注商ID/投注站号
			String userid = str[2];//彩民ID
			String activityid = str[3];//促销活动ID
			String currentrow = str[4];//当前行，默认为0
			String rowcount = str[5];//每页行数，默认为10
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("activityid\":\"").append(activityid).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String promoSend(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//代销商ID/投注商ID/投注站号
			String userid = str[2];//彩民ID
			String runcode = str[3];//促销活动赠送流水号，保证在该赠送活动的中是唯一的(投注商生成)
			String activityid = str[4];//促销活动ID
			String actionmoney = str[5];//赠送金额
			String expireddate = str[6];//失效日期
			String loginsession = str[7];//动态sessionid
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("activityid\":\"").append(activityid).append("\",\"");
			sbTmp.append("actionmoney\":\"").append(actionmoney).append("\",\"");
			sbTmp.append("expireddate\":\"").append(expireddate).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String requestSMS(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//代销商ID/投注商ID/投注站号
			String mobile = str[2];//手机号
			String requesttype = str[3];//短信验证码类型（1:注册,2:修改手机号码,3:忘记密码,4:提现）
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"mobile\":\"").append(mobile).append("\",\"");
			sbTmp.append("requesttype\":\"").append(requesttype).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String rechargeWithdrawQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String querytype = str[3];//查询类型(1-充值，2-提现)
			String bookruncode = str[4];//充值或提现订单号
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("querytype\":\"").append(querytype).append("\",\"");
			sbTmp.append("bookruncode\":\"").append(bookruncode).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String filenameQuery(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String playename = str[2];//玩法英文名
			String filetype = str[3];//文件类型(1-开奖号码、2-开奖公告、3-中奖数据文件、4-派奖文件、5-销售订单文件、6-彩票明细文件、7-合买参与文件)
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"playename\":\"").append(playename).append("\",\"");
			sbTmp.append("filetype\":\"").append(filetype).append("\"}");
			String sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String tobuyDataQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String tobuybookno = str[2];//合买订单号(同一笔合买，定单号相同)
			String tobuyruncode = str[3];//合买投注流水号(all-查询所有用户)
			String userid = str[4];//用户ID(all-所有用户，单个用户填写真实用户id)
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"tobuybookno\":\"").append(tobuybookno).append("\",\"");
			sbTmp.append("tobuyruncode\":\"").append(tobuyruncode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String bookCommonQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String bookQueryCommon(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String bookBetMoreQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String bookQueryBetMore(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号(由投注商生成，唯一)
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String bookTobuyQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String bookTobuyBetQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String termBetQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号
			String beginorder = str[3];//开始序号(非多期填1)
			String endorder = str[4];//结束序号(非多期填1)
			String currentrow = str[5];//当前行，默认为0
			String rowcount = str[6];//每页行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("beginorder\":\"").append(beginorder).append("\",\"");
			sbTmp.append("endorder\":\"").append(endorder).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	public String tobuyEncashQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String tobuybookno = str[2];//合买订单号(同一笔合买，定单号相同)
			String tobuyruncode = str[3];//合买投注流水号(all-查询所有用户)
			String userid = str[4];//用户ID(all-所有用户，单个用户填写真实用户id)
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"tobuybookno\":\"").append(tobuybookno).append("\",\"");
			sbTmp.append("tobuyruncode\":\"").append(tobuyruncode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String accountPageQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String currentrow = str[2];//当前行
			String rowcount = str[3];//每页行数
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"currentrow\":\"").append(currentrow).append("\",");
			sbTmp.append("\"rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
		

	public String winAddressQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String playename = str[2];//玩法英文名
			String selltermcode = str[3];//销售期号
			String sendTmp = "";
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String commOrderQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String playename = str[3];//玩法英文名(all-所有玩法)
			String selltermcode = str[4];//销售期号(all-所有期号)
			String starttime = str[5];//开始时间(20150628)
			String endtime = str[6];//结束时间
			String currentrow = str[7];//当前行，默认为0
			String rowcount = str[8];//每页行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\",\"");
			sbTmp.append("starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	public String betMoreQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String playename = str[3];//玩法英文名(all-所有玩法)
			String selltermcode = str[4];//销售期号(all-所有期号)
			String starttime = str[5];//开始时间(20150628)
			String endtime = str[6];//结束时间
			String currentrow = str[7];//当前行，默认为0
			String rowcount = str[8];//每页行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\",\"");
			sbTmp.append("starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

	public String tobuyQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String playename = str[3];//玩法英文名(all-所有玩法)
			String selltermcode = str[4];//销售期号(all-所有期号)
			String starttime = str[5];//开始时间(20150628)
			String endtime = str[6];//结束时间
			String currentrow = str[7];//当前行，默认为0
			String rowcount = str[8];//每页行数，默认为10
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\",\"");
			sbTmp.append("starttime\":\"").append(starttime).append("\",\"");
			sbTmp.append("endtime\":\"").append(endtime).append("\",\"");
			sbTmp.append("currentrow\":\"").append(currentrow).append("\",\"");
			sbTmp.append("rowcount\":\"").append(rowcount).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String tobuyRuncodeQuery(String[] str){
		try
		{
			StringBuffer sbTmp = new StringBuffer();
			String head = "";
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注流水号
			String sendTmp = "";
			
			
			head = "agentid=" + agentid + "&" + "paramcontent=";
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\"}");
			
			sendTmp = sbTmp.toString();
			
			System.out.println("发送加密前 = " + head + sendTmp);
			sendTmp = ThreeDES.encryptBASE64(ThreeDES.encryptMode(sbTmp.toString().getBytes("UTF-8"),agentSecretKey));
			System.out.println("发送加密后 = " + sendTmp);
			String sTmp = head + sendTmp;
			return sTmp;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
