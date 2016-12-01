package com.sinodata.tools;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
	//API接口
	public static String registerFilePath = "E:\\test_interface\\手机客户端\\注册.txt";
	public static String registerUrl = "/access/api/agent/register.action?";
	public static String singleSellFilePath = "E:\\test_interface\\手机客户端\\自购投注.csv";
	public static String singleSellUrl = "/access/api/agent/betTakeEffect.action?";
	//public static String loginFilePath = "E:\\test_interface\\手机客户端\\登录.csv";
	public static String loginFilePath = "E:\\test_interface\\手机客户端\\登录.txt";
	public static String loginUrl = "/access/api/agent/login.action?";
	public static String teminaLoginFilePath = "E:\\test_interface\\手机客户端\\登录.txt";
	public static String teminaLoginUrl = "/access/api/agent/login.action?";
	public static String loginOutFilePath = "E:\\test_interface\\手机客户端\\退出登录.txt";
	public static String loginOutUrl = "/access/api/agent/loginOut.action?";
	public static String betTakeEffectFilePath = "E:\\test_interface\\手机客户端\\投注.txt";
	public static String betTakeEffectUrl = "/access/api/agent/betTakeEffect.action?";
	public static String betMoreTakeEffectFilePath = "E:\\test_interface\\手机客户端\\多期投注.txt";
	public static String betMoreTakeEffectUrl = "/access/api/agent/betMoreTakeEffect.action?";
	public static String bindBankFilePath = "E:\\test_interface\\手机客户端\\绑定银行卡.txt";
	public static String bindBankUrl = "/access/api/agent/bindBank.action?";
	public static String unbundlingBankFilePath = "E:\\test_interface\\手机客户端\\解绑银行卡.txt";
	public static String unbundlingBankUrl = "/access/api/agent/unbundlingBank.action?";
	public static String bindBankQueryFilePath = "E:\\test_interface\\手机客户端\\查询绑定银行卡.txt";
	public static String bindBankQueryUrl = "/access/api/agent/bindBankQuery.action?";
	public static String rechargeFilePath = "E:\\test_interface\\手机客户端\\充值.txt";
	public static String rechargeUrl = "/access/api/agent/recharge.action?";
	public static String cashFilePath = "E:\\test_interface\\手机客户端\\提现.txt";
	public static String cashUrl = "/access/api/agent/cash.action?";
	public static String updateMobileFilePath = "E:\\test_interface\\手机客户端\\更改手机号码.txt";
	public static String updateMobileUrl = "/access/api/agent/updateMobile.action?";
	public static String tobuyBetFilePath = "E:\\test_interface\\手机客户端\\合买投注.txt";
	public static String tobuyBetUrl = "/access/api/agent/tobuyBet.action?";
	public static String updateUserNameFilePath = "E:\\test_interface\\手机客户端\\更改用户名.txt";
	public static String updateUserNameUrl = "/access/api/agent/updateUserName.action?";
	public static String updatePasswordFilePath = "E:\\test_interface\\手机客户端\\更改密码（交易密码和登录密码）.txt";
	public static String updatePasswordUrl = "/access/api/agent/updatePassword.action?";
	public static String retrievePassFilePath = "E:\\test_interface\\手机客户端\\找回密码.txt";
	public static String retrievePassUrl = "/access/api/agent/retrievePass.action?";
	public static String tobuySponsorFilePath = "E:\\test_interface\\手机客户端\\合买投注（发起）.txt";
	public static String tobuySponsorUrl = "/access/api/agent/tobuySponsor.action?";
	public static String tobuyJoinFilePath = "E:\\test_interface\\手机客户端\\合买投注（参与）.txt";
	public static String tobuyJoinUrl = "/access/api/agent/tobuyJoin.action?";
	public static String queryOrderFilePath = "E:\\test_interface\\手机客户端\\充值订单查询（银行）.txt";
	public static String queryOrderUrl = "/access/api/agent/queryOrder.action?";
	public static String teminalCashFilePath = "E:\\test_interface\\手机客户端\\自助终端兑奖（东港自助终端机）.txt";
	public static String teminalCashUrl = "/access/api/agent/teminalCash.action?";
	public static String cancelAccountFilePath = "E:\\test_interface\\手机客户端\\注销（投注网站使用）.txt";
	public static String cancelAccountUrl = "/access/api/agent/cancelAccount.action?";
	
	//商家服务平台接口
	public static String loginCheckFilePath = "E:\\test_interface\\商家服务平台接口\\平台登录验证.txt";
	public static String loginCheckUrl = "/access/sino/agent/loginCheck.action?";
	public static String cashAccBusinessFilePath = "E:\\test_interface\\商家服务平台接口\\投注站代彩民现金充值.txt";
	public static String cashAccBusinessUrl = "/access/sino/agent/cashAccBusiness.action?";
	public static String stationAgentQueryFilePath = "E:\\test_interface\\商家服务平台接口\\投注站信息查询.txt";
	public static String stationAgentQueryUrl = "/access/sino/agent/stationAgentQuery.action?";
	public static String noticeQueryFilePath = "E:\\test_interface\\商家服务平台接口\\通知信息查询.txt";
	public static String noticeQueryUrl = "/access/sino/agent/noticeQuery.action?";
	public static String uptPassFilePath = "E:\\test_interface\\商家服务平台接口\\商家密码修改.txt";
	public static String uptPassUrl = "/access/sino/agent/uptPass.action?";
	public static String accPageQueryFilePath = "E:\\test_interface\\商家服务平台接口\\账户明细分页查询.txt";
	public static String accPageQueryUrl = "/access/sino/agent/accPageQuery.action?";
	public static String agentSellQueryFilePath = "E:\\test_interface\\商家服务平台接口\\销量分页查询.txt";
	public static String agentSellQueryUrl = "/access/sino/agent/agentSellQuery.action?";
	public static String queryUserPageFilePath = "E:\\test_interface\\商家服务平台接口\\注册彩民分页查询（批量）.txt";
	public static String queryUserPageUrl = "/access/sino/agent/queryUserPage.action?";
	public static String cashApplyFilePath = "E:\\test_interface\\商家服务平台接口\\商家提现申请.txt";
	public static String cashApplyUrl = "/access/sino/agent/cashApply.action?";
	public static String queryUseInfoFilePath = "E:\\test_interface\\商家服务平台接口\\注册彩民信息查询（单个）.txt";
	public static String queryUseInfoUrl = "/access/sino/agent/queryUseInfo.action?";
	public static String queryAgentListFilePath = "E:\\test_interface\\商家服务平台接口\\绑定的投注商列表查询.txt";
	public static String queryAgentListUrl = "/access/sino/agent/queryAgentList.action?";
	
	//投注商接口
	public static String rechargeWithdrawDetailQueryFilePath = "E:\\test_interface\\投注商接口\\充值提现明细分页查询.txt";
	public static String rechargeWithdrawDetailQueryUrl = "/access/comm/agent/rechargeWithdrawDetailQuery.action?";
	public static String userAccountQueryFilePath = "E:\\test_interface\\投注商接口\\彩民账户明细查询.txt";
	public static String userAccountQueryUrl = "/access/comm/agent/userAccountQuery.action?";
	public static String cancelMoreBetFilePath = "E:\\test_interface\\投注商接口\\多期撤销.txt";
	public static String cancelMoreBetUrl = "/access/comm/agent/cancelMoreBet.action?";
	public static String cancelMoreBetQueryFilePath = "E:\\test_interface\\投注商接口\\多期撤销查询.txt";
	public static String cancelMoreBetQueryUrl = "/access/comm/agent/cancelMoreBetQuery.action?";
	public static String betTakeEffectQueryFilePath = "E:\\test_interface\\投注商接口\\投注查询.txt";
	public static String betTakeEffectQueryUrl = "/access/comm/agent/betTakeEffectQuery.action?";
	public static String synTimeFilePath = "E:\\test_interface\\投注商接口\\时间同步.txt";
	public static String synTimeUrl = "/access/comm/agent/synTime.action?";
	public static String userTradeQueryFilePath = "E:\\test_interface\\投注商接口\\彩民账户交易明细分页查询.txt";
	public static String userTradeQueryUrl = "/access/comm/agent/userTradeQuery.action?";
	public static String currentTermWinCodeQueryFilePath = "E:\\test_interface\\投注商接口\\当期开奖号码查询.txt";
	public static String currentTermWinCodeQueryUrl = "/access/comm/agent/currentTermWinCodeQuery.action?";
	public static String winCodeQueryFilePath = "E:\\test_interface\\投注商接口\\开奖号码分页查询.txt";
	public static String winCodeQueryUrl = "/access/comm/agent/winCodeQuery.action?";
	public static String winNoticeQueryFilePath = "E:\\test_interface\\投注商接口\\开奖公告查询.txt";
	public static String winNoticeQueryUrl = "/access/comm/agent/winNoticeQuery.action?";
	public static String mutilNewTermQueryFilePath = "E:\\test_interface\\投注商接口\\多玩法新期参数查询.txt";
	public static String mutilNewTermQueryUrl = "/access/comm/agent/mutilNewTermQuery.action?";
	public static String winDetailQueryFilePath = "E:\\test_interface\\投注商接口\\中奖明细分页查询.txt";
	public static String winDetailQueryUrl = "/access/comm/agent/winDetailQuery.action?";
	public static String winSellDetailQueryFilePath = "E:\\test_interface\\投注商接口\\中奖明细的投注详情.txt";
	public static String winSellDetailQueryUrl = "/access/comm/agent/winSellDetailQuery.action?";
	public static String userPromotionQueryFilePath = "E:\\test_interface\\投注商接口\\彩民促销账户明细查询.txt";
	public static String userPromotionQueryUrl = "/access/comm/agent/userPromotionQuery.action?";
	public static String voiceLoginFilePath = "E:\\test_interface\\投注商接口\\彩民登录（电话语音）.txt";
	public static String voiceLoginUrl = "/access/comm/agent/voiceLogin.action?";
	public static String changeMobileFilePath = "E:\\test_interface\\投注商接口\\彩民更换手机号（电话语音、短信）.txt";
	public static String changeMobileUrl = "/access/comm/agent/changeMobile.action?";
	public static String mobileFeeToBetFilePath = "E:\\test_interface\\投注商接口\\话费转投注金（电话语音、短信）.txt";
	public static String mobileFeeToBetUrl = "/access/comm/agent/mobileFeeToBet.action?";
	public static String winToMobileFeeFilePath = "E:\\test_interface\\投注商接口\\奖金转话费（电话语音、短信）.txt";
	public static String winToMobileFeeUrl = "/access/comm/agent/winToMobileFee.action?";
	public static String userQueryUseInfoFilePath = "E:\\test_interface\\投注商接口\\彩民基本信息查询.txt";
	public static String userQueryUseInfoUrl = "/access/comm/agent/userQueryUseInfo.action?";
	public static String userNoticeQueryFilePath = "E:\\test_interface\\投注商接口\\彩民通知信息分页查询.txt";
	public static String userNoticeQueryUrl = "/access/comm/agent/userNoticeQuery.action?";
	public static String promoQueryFilePath = "E:\\test_interface\\投注商接口\\促销赠金分页查询.txt";
	public static String promoQueryUrl = "/access/comm/agent/promoQuery.action?";
	public static String promoSendFilePath = "E:\\test_interface\\投注商接口\\促销赠金发放.txt";
	public static String promoSendUrl = "/access/comm/agent/promoSend.action?";
	public static String requestSMSFilePath = "E:\\test_interface\\投注商接口\\请求发送短信验证码.txt";
	public static String requestSMSUrl = "/access/comm/agent/requestSMS.action?";
	public static String rechargeWithdrawQueryFilePath = "E:\\test_interface\\投注商接口\\订单号查询充值提现.txt";
	public static String rechargeWithdrawQueryUrl = "/access/comm/agent/rechargeWithdrawQuery.action?";
	public static String tobuyDataQueryFilePath = "E:\\test_interface\\投注商接口\\合买用户参与查询.txt";
	public static String tobuyDataQueryUrl = "/access/comm/agent/tobuyDataQuery.action?";
	//public static String bookCommonQueryFilePath = "E:\\test_interface\\投注商接口\\按订单号查询自购投注订单信息.csv";
	public static String bookCommonQueryFilePath = "E:\\test_interface\\投注商接口\\按订单号查询自购投注订单信息.txt";
	public static String bookCommonQueryUrl = "/access/comm/agent/bookCommonQuery.action?";
	public static String bookQueryCommonFilePath = "E:\\test_interface\\投注商接口\\按订单号查询自购投注明细.txt";
	public static String bookQueryCommonUrl = "/access/comm/agent/bookQueryCommon.action?";
	public static String bookBetMoreQueryFilePath = "E:\\test_interface\\投注商接口\\按订单号查询多期投注订单信息.txt";
	public static String bookBetMoreQueryUrl = "/access/comm/agent/bookBetMoreQuery.action?";
	public static String bookQueryBetMoreFilePath = "E:\\test_interface\\投注商接口\\按订单号查询多期投注明细.txt";
	public static String bookQueryBetMoreUrl = "/access/comm/agent/bookQueryBetMore.action?";
	public static String bookTobuyQueryFilePath = "E:\\test_interface\\投注商接口\\按订单号查询合买投注订单信息.txt";
	public static String bookTobuyQueryUrl = "/access/comm/agent/bookTobuyQuery.action?";
	public static String bookTobuyBetQueryFilePath = "E:\\test_interface\\投注商接口\\按订单号查询合买投注明细.txt";
	public static String bookTobuyBetQueryUrl = "/access/comm/agent/bookTobuyBetQuery.action?";
	public static String termBetQueryFilePath = "E:\\test_interface\\投注商接口\\按投注序号段分页查询投注明细(自购、多期和合买).txt";
	public static String termBetQueryUrl = "/access/comm/agent/termBetQuery.action?";
	public static String tobuyEncashQueryFilePath = "E:\\test_interface\\投注商接口\\合买奖金分配明细查询.txt";
	public static String tobuyEncashQueryUrl = "/access/comm/agent/tobuyEncashQuery.action?";
	public static String accountPageQueryFilePath = "E:\\test_interface\\投注商接口\\账户查询（自助终端）.txt";
	public static String accountPageQueryUrl = "/access/comm/agent/accountPageQuery.action?";
	public static String winAddressQueryFilePath = "E:\\test_interface\\投注商接口\\中奖归属地查询.txt";
	public static String winAddressQueryUrl = "/access/comm/agent/winAddressQuery.action?";
	public static String winQueryNoticeFilePath = "E:\\test_interface\\投注商接口\\开奖公告查询.txt";
	public static String winQueryNoticeUrl = "/access/comm/agent/winQueryNotice.action?";
	public static String commOrderQueryFilePath = "E:\\test_interface\\投注商接口\\彩民自购投注订单号分页查询.txt";
	public static String commOrderQueryUrl = "/access/comm/agent/commOrderQuery.action?";
	public static String betMoreQueryFilePath = "E:\\test_interface\\投注商接口\\彩民多期投注订单号分页查询.txt";
	public static String betMoreQueryUrl = "/access/comm/agent/betMoreQuery.action?";
	public static String tobuyQueryFilePath = "E:\\test_interface\\投注商接口\\彩民合买投注订单号分页查询.txt";
	public static String tobuyQueryUrl = "/access/comm/agent/tobuyQuery.action?";
	public static String tobuyRuncodeQueryFilePath = "E:\\test_interface\\投注商接口\\按流水号号查询合买投注订单信息.txt";
	public static String tobuyRuncodeQueryUrl = "/access/comm/agent/tobuyRuncodeQuery.action?";
	
	/**
	 * 将Configuration类中的所有属性放入Map中
	 * @return 包含所有属性名和属性值的Map
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String, Object> getMap() throws IllegalArgumentException, IllegalAccessException{
		 Configuration configuration = new Configuration();
		 Field fields[] = configuration.getClass().getDeclaredFields();//获得对象所有属性
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 for (int i = 0; i < fields.length; i++) {
			 resultMap.put(fields[i].getName(), fields[i].get(configuration));
		 }
		 return resultMap;
  	 }
	
	/**
	 * 根据接口名称返回对应的参数文件路径
	 * @param interfaceName 接口名称
	 * @return 返回接口对应的参数文件路径（filePath）
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getFilePath(String interfaceName) throws IllegalArgumentException, IllegalAccessException{
		Map<String, Object> resultMap = Configuration.getMap();
		String str = interfaceName + "FilePath";
		String path = (String) resultMap.get(str);
		return path;
	}
	
	/**
	 * 根据接口名称返回对应的请求URL
	 * @param interfaceName
	 * @return 返回接口对应的请求URL
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getUrl(String interfaceName) throws IllegalArgumentException, IllegalAccessException{
		Map<String, Object> resultMap = Configuration.getMap();
		String str = interfaceName + "Url";
		String url = (String) resultMap.get(str);
		return url;
	}
	
	public static void main(String args[]) throws IllegalArgumentException, IllegalAccessException{
		String str = getFilePath("login");
		System.out.println(str);
	}
	
}

