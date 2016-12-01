package com.sinodata.test;

public class TestMoreThread implements Runnable{
	private String name;
	private static TestInterface ti = new TestInterface();;
	
	public TestMoreThread(String name){
		this.name = name;
	}
	public static void init(){
		ti.login();
	}
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(this.name + "开始销售===============");
			ti.singleSell();
		}
	}
	
	public static void main(String[] args) {
		init();
		TestMoreThread mt1 = new TestMoreThread("线程A");
		TestMoreThread mt2 = new TestMoreThread("线程B");
		TestMoreThread mt3 = new TestMoreThread("线程C");
		Thread t1 = new Thread(mt1,mt1.name);
		Thread t2 = new Thread(mt2,mt2.name);
		Thread t3 = new Thread(mt3,mt3.name);
		System.out.println(t1.getName() + "开始运行********");
		t1.start();
		System.out.println(t2.getName() + "开始运行********");
		t2.start();
		System.out.println(t3.getName() + "开始运行********");
		t3.start();
	}

}
