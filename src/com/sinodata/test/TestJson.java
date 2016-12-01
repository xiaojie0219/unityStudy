package com.sinodata.test;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJson {
	private String agentid;
	private JSONObject paramcontent;
	
	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public void setParam(JSONObject jsonObject){
		this.paramcontent = jsonObject;
	}
	
	public JSONObject getParam(){
		return paramcontent;
	}
	
	public String toString(){
		return "agentid=" + this.agentid + "&" + "paramcontent" + "=" + this.paramcontent ;
	}
	
	public static void main(String[] args){
		JSONObject jo = new JSONObject();
		HashMap<String,String> hm = new HashMap<String, String>();
		hm.put("promoruncode", "abc123456");
		hm.put("activityid", "00001");
		hm.put("actionmoney", "10");
		
		JSONArray ja = new JSONArray();
		ja.add(JSONObject.fromObject(hm));
				
		jo.put("userid", "3760100000006");
		jo.put("loginpass", "123456");
		jo.put("macaddress", "");
		jo.put("promolist",ja);
		
		TestJson tj = new TestJson();
		tj.setAgentid("00001");
		tj.setParam(jo);
		System.out.println(tj.toString());
	}
}

