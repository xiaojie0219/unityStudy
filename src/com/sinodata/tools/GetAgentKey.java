/**
 * 文件描述： 
 * 创建时间: 2014-04-23
 * 文件版本: 
 */
package com.sinodata.tools;

/**
 * 功能概述：获取代销商私钥
 * 创建时间:2014-04-23
 *
 */
public class GetAgentKey {
	public static void main(String[] args) {
		String agentId = "00007";
		String insertTime = "20180630114807";
		try {
			String privateKye = MD5Security.md5To24(agentId + insertTime.substring(0, 8) + "sino" + insertTime.substring(8, insertTime.length()));
			System.out.println(privateKye);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
