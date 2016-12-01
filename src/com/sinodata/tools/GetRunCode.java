package com.sinodata.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GetRunCode {
	/**
	 * 运用时间戳和随机数生成流水号
	 * @return 时间戳和随机数生成的流水号
	 */
	public static String getRunCode(){
		String tmp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+new Random().nextInt(1000);
		return tmp;
	}
	public static void main(String[] args) {
		System.out.println(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+new Random().nextInt(1000)); 
	}

}
