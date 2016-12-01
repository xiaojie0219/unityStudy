package com.sinodata.dataHandle;


import com.sinodata.tools.PropertiesUtil;
import com.sinodata.tools.ThreeDES;
import com.sinodata.tools.UtilsList;


/**
 * 将手机API接口 按接口定义将数据经过JSON加密后返回
 * @author Administrator
 *
 */
public class ApiDataTransferJson {
	public PropertiesUtil pu = null;
	public String pubSecretKey = null;
	
	public ApiDataTransferJson(){
		pu = new PropertiesUtil();
		pubSecretKey = pu.read("config.properties", "pubSecretKey");
	}
	
	public String register(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String username = str[2];//用户名
			String realname = str[3];//真实姓名
			String idcard = str[4];//身份证号码
			String mobilecode = str[5];//手机号
			String loginpass = str[6];//登录密码
			String tradepass = str[7];//交易密码
			String smscode = str[8];//短信校验码
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"username\":\"").append(username).append("\",\"");
			sbTmp.append("realname\":\"").append(realname).append("\",\"");
			sbTmp.append("idcard\":\"").append(idcard).append("\",\"");
			sbTmp.append("mobilecode\":\"").append(mobilecode).append("\",\"");
			sbTmp.append("loginpass\":\"").append(loginpass).append("\",\"");
			sbTmp.append("tradepass\":\"").append(tradepass).append("\",\"");
			sbTmp.append("smscode\":\"").append(smscode).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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
	
	/**
	 * 登录接口传参组合并加密JSON
	 * @param str 传入参数字符串数组
	 * @return 加密后的HTTP请求数据
	 */
	
	public String login(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID/用户名/身份证号码/手机号码
			String loginpass = str[3];//登录密码
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginpass\":\"").append(loginpass).append("\"}");
			
			String sendTmp = sbTmp.toString();
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
	
	public String teminaLogin(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID/用户名/身份证号码/手机号码
			String loginpass = str[3];//登录密码
			String macaddress = str[4];//自助终端MAC地址
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginpass\":\"").append(loginpass).append("\",\"");
			sbTmp.append("macaddress\":\"").append(macaddress).append("\"}");
			
			String sendTmp = sbTmp.toString();
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
	
	public String bindBank(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String runcode = str[2];//流水号(商家生成，唯一)
			String province = str[3];//省份名称(银行所在地)
			String city = str[4];//地市名称(银行所在地)
			String bankno = str[5];//交易渠道编号
			String userid = str[6];//用户ID
			String loginsession = str[7];//动态sessionid
			String bankcard = str[8];//银行卡号
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("province\":\"").append(province).append("\",\"");
			sbTmp.append("city\":\"").append(city).append("\",\"");
			sbTmp.append("bankno\":\"").append(bankno).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("bankcard\":\"").append(bankcard).append("\"}");
			
			String sendTmp = sbTmp.toString();
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
	
	public String recharge(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String loginsession = str[3];//动态sessionid
			String paychannels = str[4];//支付渠道类型(1-银联,2-通联,3-支付宝)
			String paymoney = str[5];//充值金额
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("paychannels\":\"").append(paychannels).append("\",\"");
			sbTmp.append("paymoney\":\"").append(paymoney).append("\"}");
			
			String sendTmp = sbTmp.toString();
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
	
	public String cash(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String runcode = str[2];//流水号(商家生成，唯一)
			String userid = str[3];//用户ID
			String loginsession = str[4];//动态sessionid
			String bankcard = str[5];//银行卡号
			String tradepass = str[6];//交易密码
			String recmoney = str[7];//提现金额
			String smscode = str[8];//短信校验码
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("bankcard\":\"").append(bankcard).append("\",\"");
			sbTmp.append("tradepass\":\"").append(tradepass).append("\",\"");
			sbTmp.append("recmoney\":\"").append(recmoney).append("\",\"");
			sbTmp.append("smscode\":\"").append(smscode).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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
	
	public String bindBankQuery(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String loginsession = str[3];//动态sessionid
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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
	
	public String unbundlingBank(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String loginsession = str[3];//动态sessionid
			String bankcard = str[4];//银行卡号
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("bankcard\":\"").append(bankcard).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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
	
	public String updateMobile(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String password = str[3];//用户ID
			String mobile = str[4];//用户ID
			String loginsession = str[5];//动态sessionid
			String smscode = str[6];//银行卡号
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("password\":\"").append(password).append("\",\"");
			sbTmp.append("mobile\":\"").append(mobile).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("smscode\":\"").append(smscode).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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
	
	public String betTakeEffect(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号(由投注商生成，唯一)
			String userid = str[3];//用户ID
			String accesstype = str[4];//接入类型(1-网站，2-自助终端，3-手机，4-电话, 5-手机短信)
			String playename = str[5];//玩法英文名
			String selltermcode = str[6];//销售期号（多期不存在期号，填0）
			String loginsession = str[7];//动态sessionid
			String totalmoney = str[8];//总金额
			String drawway = str[9];//投注方式(1-自选，2-机选)
			String lotterytype = str[10];//彩种类型(1-电脑票，2-电子即开票)
			String ticketype = str[11];//类型(1-单票，2-多票)
			String codeinfo = str[12];//注码数据区(由投注商拆票，定义：单票投注流水号1+TAB键+金额1+TAB键+注码1+"@"+单票投注流水号2+TAB键+金额2+TAB键+注码2)，
			
			StringBuffer promolist = new StringBuffer();
			String promoruncode = str[13];//促销活动赠送流水号
			String activityid = str[14];//促销活动ID
			String actionmoney = str[15];//使用的促销金额
			//校验码(思乐公司提供校验函数，输入值：投注流水号、投注商ID、用户ID、注码数据区、总金额、动态sessionid)
			String checkcode = UtilsList.getCheckCode(runcode+agentid+userid+codeinfo+totalmoney+loginsession);
			
			promolist.append("[");
			if(!promoruncode.toString().equals("") && promoruncode.toString() != null){
				promolist.append("{\"promoruncode\":\"").append(promoruncode).append("\",\"");
			}
			if(!activityid.toString().equals("") && activityid.toString() != null){
				promolist.append("{\"activityid\":\"").append(activityid).append("\",\"");
			}
			if(!actionmoney.toString().equals("") && actionmoney.toString() != null){
				promolist.append("{\"actionmoney\":\"").append(actionmoney).append("\"");
			}
			promolist.append("]");
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("totalmoney\":\"").append(totalmoney).append("\",\"");
			sbTmp.append("drawway\":\"").append(drawway).append("\",\"");
			sbTmp.append("lotterytype\":\"").append(lotterytype).append("\",\"");
			sbTmp.append("ticketype\":\"").append(ticketype).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\",\"");
			sbTmp.append("codeinfo\":\"").append(codeinfo).append("\",\"");
			
			sbTmp.append("promolist\":").append(promolist.toString());
			sbTmp.append("}");
			
			String sendTmp = sbTmp.toString();
			
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

	public String betMoreTakeEffect(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String runcode = str[2];//投注订单号(由投注商生成，唯一)
			String userid = str[3];//用户ID
			String accesstype = str[4];//接入类型(1-网站，2-自助终端，3-手机，4-电话, 5-手机短信)
			String playename = str[5];//玩法英文名
			String sumterm = str[6];//总期数
			String stopmoney = str[7];//中奖即停金额(当多期定单累计中奖金额超出彩民设定的此金额时，系统自动停止本次多期投注，下期生效，非多期填0）
			String loginsession = str[8];//动态sessionid
			String totalmoney = str[9];//总金额
			String drawway = str[10];//投注方式(1-自选，2-机选)
			String lotterytype = str[11];//彩种类型(1-电脑票，2-电子即开票)
			String ticketype = str[12];//类型(1-单票，2-多票)
			String codeinfo = str[13];//注码数据区(由投注商拆票，定义：单票投注流水号1+TAB键+金额1+TAB键+注码1+"@"+单票投注流水号2+TAB键+金额2+TAB键+注码2)，
			
			StringBuffer promolist = new StringBuffer();
			String promoruncode = str[14];//促销活动赠送流水号
			String activityid = str[15];//促销活动ID
			String actionmoney = str[16];//使用的促销金额
			//校验码(思乐公司提供校验函数，输入值：投注流水号、投注商ID、用户ID、注码数据区、总金额、动态sessionid)
			String checkcode = UtilsList.getCheckCode(runcode+agentid+userid+codeinfo+totalmoney+loginsession);
			
			promolist.append("[");
			if(!promoruncode.toString().equals("") && promoruncode.toString() != null){
				promolist.append("{\"promoruncode\":\"").append(promoruncode).append("\",\"");
			}
			if(!activityid.toString().equals("") && activityid.toString() != null){
				promolist.append("{\"activityid\":\"").append(activityid).append("\",\"");
			}
			if(!actionmoney.toString().equals("") && actionmoney.toString() != null){
				promolist.append("{\"actionmoney\":\"").append(actionmoney).append("\"");
			}
			promolist.append("]");
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("playename\":\"").append(playename).append("\",\"");
			sbTmp.append("sumterm\":\"").append(sumterm).append("\",\"");
			sbTmp.append("stopmoney\":\"").append(stopmoney).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("totalmoney\":\"").append(totalmoney).append("\",\"");
			sbTmp.append("drawway\":\"").append(drawway).append("\",\"");
			sbTmp.append("lotterytype\":\"").append(lotterytype).append("\",\"");
			sbTmp.append("ticketype\":\"").append(ticketype).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\",\"");
			sbTmp.append("codeinfo\":\"").append(codeinfo).append("\",\"");
			
			sbTmp.append("promolist\":").append(promolist.toString());
			sbTmp.append("}");
			
			String sendTmp = sbTmp.toString();
			
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
	
	public String loginOut(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID/用户名/身份证号码/手机号码
			String loginsession = str[3];//动态sessionid
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			String sendTmp = sbTmp.toString();
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
	
	public String tobuyBet(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String agentidP = str[2];//代销商ID
			String tobuyruncode = str[3];//合买投注流水号(由代销商生成，唯一)
			String userid = str[4];//用户ID(合买发起、发起人参与填发起人userid，合买参与填写参与人userid)
			String accesstype = str[5];//接入类型(1-网站，2-自助终端，3-手机APP，4-电话语音, 5-手机短信)
			String playename = str[6];//玩法英文名(非发起人填0)
			String selltermcode = str[7];//销售期号(非发起人填0)
			String tobuymoney = str[8];//合买总金额(非发起人填0)
			String allshare = str[9];//合买总份数(非发起人填0)
			String actorshare = str[10];//参与人份数(发起人填0)
			String drawway = str[11];//投注方式(1-自选，2-机选)
			String tobuystate = str[12];//合买状态(1-发起人，2-合买中)
			String tobuybookno = str[13];//合买定单号(同一笔合买，定单号相同)
			String tobuydeduct = str[14];//合买中奖提成比例(如：3代表3%，非发起人填0)
			String userguaranteerate = str[15];//发起人保底份数(非发起人填0)
			String agentguaranteerate = str[16];//投注商保底份数(非发起人填-1)
			//注码数据区(由投注商拆票，非发起人填0，定义：单票投注流水号1+TAB键+金额1+TAB键+注码1+"@"+单票投注流水号2+TAB键+金额2+TAB键+注码2)，(非发起人填0)
			String codeinfo = str[17];
			String loginsession = str[18];//动态sessionid
			String lotterytype = str[19];//彩种类型(1-电脑票，2-电子即开票)
			String ticketype = str[20];//类型(1-单票，2-多票)
			
			//备注：如果彩民不选择促销金额，则promolist填空，如："promolist":[]
			StringBuffer promolist = new StringBuffer();
			String promoruncode = str[21];//促销活动赠送流水号
			String activityid = str[22];//促销活动ID
			String actionmoney = str[23];//使用的促销金额

			promolist.append("[");
			if(!promoruncode.toString().equals("") && promoruncode.toString() != null){
				promolist.append("{\"promoruncode\":\"").append(promoruncode).append("\",\"");
			}
			if(!activityid.toString().equals("") && activityid.toString() != null){
				promolist.append("{\"activityid\":\"").append(activityid).append("\",\"");
			}
			if(!actionmoney.toString().equals("") && actionmoney.toString() != null){
				promolist.append("{\"actionmoney\":\"").append(actionmoney).append("\"");
			}
			promolist.append("]");
			
			//校验码(思乐公司提供校验函数，输入值：合买投注流水号、代销商ID、用户ID、注码数据区、合买总金额、合买总份数、参与人份数、合买状态、合买定单号)
			String checkcode = UtilsList.getCheckCode(tobuyruncode+agentidP+userid+codeinfo+tobuymoney+allshare+actorshare+tobuystate+tobuybookno);
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"agentid\":\"").append(agentidP).append("\",\"");
			sbTmp.append("tobuyruncode\":\"").append(tobuyruncode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\",\"");
			sbTmp.append("tobuymoney\":\"").append(tobuymoney).append("\",\"");
			sbTmp.append("allshare\":\"").append(allshare).append("\",\"");
			sbTmp.append("actorshare\":\"").append(actorshare).append("\",\"");
			sbTmp.append("drawway\":\"").append(drawway).append("\",\"");
			sbTmp.append("tobuystate\":\"").append(tobuystate).append("\",\"");
			sbTmp.append("tobuybookno\":\"").append(tobuybookno).append("\",\"");
			sbTmp.append("tobuydeduct\":\"").append(tobuydeduct).append("\",\"");
			sbTmp.append("userguaranteerate\":\"").append(userguaranteerate).append("\",\"");
			sbTmp.append("agentguaranteerate\":\"").append(agentguaranteerate).append("\",\"");
			sbTmp.append("codeinfo\":\"").append(codeinfo).append("\",\"");
			sbTmp.append("lotterytype\":\"").append(lotterytype).append("\",\"");
			sbTmp.append("ticketype\":\"").append(ticketype).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			
			sbTmp.append("promolist\":").append(promolist.toString());
			
			sbTmp.append("}");
			
			String sendTmp = sbTmp.toString();
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

	public String updateUserName(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String username = str[3];//新用户名
			String loginsession = str[4];//动态sessionid
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("username\":\"").append(username).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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

	public String updatePassword(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String oldpassword = str[3];//旧密码
			String newpassword = str[4];//新密码
			String type = str[5];//密码类型(1：交易密码 2：登录密码)
			String loginsession = str[6];//动态sessionid
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("oldpassword\":\"").append(oldpassword).append("\",\"");
			sbTmp.append("newpassword\":\"").append(newpassword).append("\",\"");
			sbTmp.append("type\":\"").append(type).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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

	public String retrievePass(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String idcard = str[2];//身份证号码
			String mobilecode = str[3];//手机号
			String newpass = str[4];//新密码
			String type = str[5];//密码类型(1：交易密码 2：登录密码)
			String smscode = str[6];//短信校验码
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"idcard\":\"").append(idcard).append("\",\"");
			sbTmp.append("mobilecode\":\"").append(mobilecode).append("\",\"");
			sbTmp.append("newpass\":\"").append(newpass).append("\",\"");
			sbTmp.append("type\":\"").append(type).append("\",\"");
			sbTmp.append("smscode\":\"").append(smscode).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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

	public String tobuySponsor(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String tobuyruncode = str[2];//合买投注流水号(由代销商生成，唯一)
			String runcode = str[3];//合买投注流水号2（由代销商生成，唯一。若无发起人首次认购，填0；）
			String userid = str[4];//用户ID(合买发起、发起人参与填发起人userid，合买参与填写参与人userid)
			String inintshare = str[5];//发起人首次认购份数(若无首次认购，填0)
			String accesstype = str[6];//接入类型(1-网站，2-自助终端，3-手机APP，4-电话语音, 5-手机短信)
			String playename = str[7];//玩法英文名(非发起人填0)
			String selltermcode = str[8];//销售期号(非发起人填0)
			String tobuymoney = str[9];//合买总金额(非发起人填0)
			String allshare = str[10];//合买总份数(非发起人填0)
			String drawway = str[11];//投注方式(1-自选，2-机选)
			String tobuybookno = str[12];//合买定单号(同一笔合买，定单号相同)
			String tobuydeduct = str[13];//合买中奖提成比例(如：3代表3%，非发起人填0)
			String userguaranteerate = str[14];//发起人保底份数(非发起人填0)
			String agentguaranteerate = str[15];//投注商保底份数(非发起人填-1)
			//注码数据区(由投注商拆票，非发起人填0，定义：单票投注流水号1+TAB键+金额1+TAB键+注码1+"@"+单票投注流水号2+TAB键+金额2+TAB键+注码2)，(非发起人填0)
			String codeinfo = str[16];
			String loginsession = str[17];//动态sessionid
			String lotterytype = str[18];//彩种类型(1-电脑票，2-电子即开票)
			String ticketype = str[19];//类型(1-单票，2-多票)
			
			//备注：如果彩民不选择促销金额，则promolist填空，如："promolist":[]
			StringBuffer promolist = new StringBuffer();
			String promoruncode = str[20];//促销活动赠送流水号
			String activityid = str[21];//促销活动ID
			String actionmoney = str[22];//使用的促销金额

			promolist.append("[");
			if(!promoruncode.toString().equals("") && promoruncode.toString() != null){
				promolist.append("{\"promoruncode\":\"").append(promoruncode).append("\",\"");
			}
			if(!activityid.toString().equals("") && activityid.toString() != null){
				promolist.append("{\"activityid\":\"").append(activityid).append("\",\"");
			}
			if(!actionmoney.toString().equals("") && actionmoney.toString() != null){
				promolist.append("{\"actionmoney\":\"").append(actionmoney).append("\"");
			}
			promolist.append("]");
			
			//校验码(思乐公司提供校验函数，输入值：合买投注流水号、代销商ID、用户ID、注码数据区、合买总金额、合买总份数、合买定单号)
			String checkcode = UtilsList.getCheckCode(tobuyruncode+agentid+userid+codeinfo+tobuymoney+allshare+tobuybookno);
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"tobuyruncode\":\"").append(tobuyruncode).append("\",\"");
			sbTmp.append("runcode\":\"").append(runcode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("inintshare\":\"").append(inintshare).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("playename\":\"").append(playename).append("\",\"");
			sbTmp.append("selltermcode\":\"").append(selltermcode).append("\",\"");
			sbTmp.append("tobuymoney\":\"").append(tobuymoney).append("\",\"");
			sbTmp.append("allshare\":\"").append(allshare).append("\",\"");
			sbTmp.append("drawway\":\"").append(drawway).append("\",\"");
			sbTmp.append("tobuybookno\":\"").append(tobuybookno).append("\",\"");
			sbTmp.append("tobuydeduct\":\"").append(tobuydeduct).append("\",\"");
			sbTmp.append("userguaranteerate\":\"").append(userguaranteerate).append("\",\"");
			sbTmp.append("agentguaranteerate\":\"").append(agentguaranteerate).append("\",\"");
			sbTmp.append("codeinfo\":\"").append(codeinfo).append("\",\"");
			sbTmp.append("lotterytype\":\"").append(lotterytype).append("\",\"");
			sbTmp.append("ticketype\":\"").append(ticketype).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			
			sbTmp.append("promolist\":").append(promolist.toString());
			
			sbTmp.append("}");
			
			String sendTmp = sbTmp.toString();
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

	public String tobuyJoin(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String tobuyruncode = str[2];//合买投注流水号(由代销商生成，唯一)
			String userid = str[3];//用户ID(合买发起、发起人参与填发起人userid，合买参与填写参与人userid)
			String accesstype = str[4];//接入类型(1-网站，2-自助终端，3-手机APP，4-电话语音, 5-手机短信)
			String actorshare = str[5];//参与人份数(发起人填0)
			String drawway = str[6];//投注方式(1-自选，2-机选)
			String tobuybookno = str[7];//合买定单号(同一笔合买，定单号相同)
			//注码数据区(由投注商拆票，非发起人填0，定义：单票投注流水号1+TAB键+金额1+TAB键+注码1+"@"+单票投注流水号2+TAB键+金额2+TAB键+注码2)，(非发起人填0)
			String loginsession = str[8];//动态sessionid
			String lotterytype = str[9];//彩种类型(1-电脑票，2-电子即开票)
			String ticketype = str[10];//类型(1-单票，2-多票)
			
			//备注：如果彩民不选择促销金额，则promolist填空，如："promolist":[]
			StringBuffer promolist = new StringBuffer();
			String promoruncode = str[11];//促销活动赠送流水号
			String activityid = str[12];//促销活动ID
			String actionmoney = str[13];//使用的促销金额

			promolist.append("[");
			if(!promoruncode.toString().equals("") && promoruncode.toString() != null){
				promolist.append("{\"promoruncode\":\"").append(promoruncode).append("\",\"");
			}
			if(!activityid.toString().equals("") && activityid.toString() != null){
				promolist.append("{\"activityid\":\"").append(activityid).append("\",\"");
			}
			if(!actionmoney.toString().equals("") && actionmoney.toString() != null){
				promolist.append("{\"actionmoney\":\"").append(actionmoney).append("\"");
			}
			promolist.append("]");
			
			//校验码(思乐公司提供校验函数，输入值：合买投注流水号、代销商ID、用户ID、注码数据区、合买总金额、合买总份数、参与人份数、合买状态、合买定单号)
			String checkcode = UtilsList.getCheckCode(tobuyruncode+agentid+userid+actorshare+tobuybookno);
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"tobuyruncode\":\"").append(tobuyruncode).append("\",\"");
			sbTmp.append("userid\":\"").append(userid).append("\",\"");
			sbTmp.append("accesstype\":\"").append(accesstype).append("\",\"");
			sbTmp.append("actorshare\":\"").append(actorshare).append("\",\"");
			sbTmp.append("drawway\":\"").append(drawway).append("\",\"");
			sbTmp.append("tobuybookno\":\"").append(tobuybookno).append("\",\"");
			sbTmp.append("lotterytype\":\"").append(lotterytype).append("\",\"");
			sbTmp.append("ticketype\":\"").append(ticketype).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			
			sbTmp.append("promolist\":").append(promolist.toString());
			
			sbTmp.append("}");
			
			String sendTmp = sbTmp.toString();
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


	public String queryOrder(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID
			String loginsession = str[3];//动态sessionid
			String paychannels = str[4];//支付渠道类型(1-银联,2-通联,3-支付宝)
			String runcode = str[5];//充值订单号(充值返回的思乐唯一的流水号)
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\",\"");
			sbTmp.append("paychannels\":\"").append(paychannels).append("\",\"");
			sbTmp.append("runcode\":\"").append(runcode).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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

	public String teminalCash(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID(自助终端机ID)
			String ticketcode = str[3];//票号
			String agentmoney = str[4];//票号
			String loginsession = str[5];//票号
			//校验码(思乐公司提供校验函数，输入值：用户ID、票号)
			String checkcode = UtilsList.getCheckCode(userid+ticketcode);
			
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("ticketcode\":\"").append(ticketcode).append("\",\"");
			sbTmp.append("checkcode\":\"").append(checkcode).append("\",\"");
			sbTmp.append("agentmoney\":\"").append(agentmoney).append("\",\"");
			sbTmp.append("loginsession\":\"").append(loginsession).append("\"}");
			
			String sendTmp = sbTmp.toString();
			
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

	public String cancelAccount(String[] str){
		try
		{
			String agentid = str[1];//投注商ID
			String userid = str[2];//用户ID/用户名/身份证号码/手机号码
			String tradepass = str[3];//交易密码
			
			String head = "agentid=" + agentid + "&" + "paramcontent=";
			StringBuffer sbTmp = new StringBuffer();
			sbTmp.append("{\"userid\":\"").append(userid).append("\",\"");
			sbTmp.append("tradepass\":\"").append(tradepass).append("\"}");
			
			String sendTmp = sbTmp.toString();
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
	
}
