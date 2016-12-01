package com.sinodata.test;

import org.testng.annotations.Test;

import com.sinodata.operate.OperateInterface;

public class TestInterface {
	private OperateInterface oi;
	TestInterface(){
		oi = new OperateInterface();
	}
	
	public void login(){
		try {
			oi.operateInterface("api", "login");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void singleSell(){
		try {
			oi.operateInterface("api", "singleSell");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
