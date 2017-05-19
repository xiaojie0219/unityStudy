package com.sinodata.forJMeter;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class HexStringTool {
	public static String str2HexStr(String str){
		StringBuffer sb = new StringBuffer();
		byte[] b = str.getBytes();
		if(b == null || b.length <= 0){
			return null;
		}
		for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append(0);
            }
            sb.append(hv);
        }
        return sb.toString();
	}
	
	public static String hexStr2Str(String hexStr) {
//		String str = "0123456789ABCDEF";//注意字母大写与下一行字母小写的区别
		String str = "0123456789abcdef";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		try {
			return new String(bytes,"GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException{
		System.out.println("字符串转为十六进制:" + HexStringTool.str2HexStr("123#56789#"));
		System.out.println("十六进制转为字符串:" + HexStringTool.hexStr2Str("31323323353637383923"));
	}
}
