package com.sinodata.forJMeter;

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
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}
	
	public static void main(String[] args){
		System.out.println("字符串转为十六进制:" + HexStringTool.str2HexStr("20161205152314065123#10000#1000#888666888666888666##SSQ_306#20161205152314049#1#130603195303077835#13311113333#1#0#0#0#2#01,02,03,04,14,28:11^09,11,15,16,18,29:07#4#XXXX#XXXX#20161205101010#"));
		System.out.println("十六进制转为字符串:" + HexStringTool.hexStr2Str("323031363132303531373336303931323334353623313030303023533030302331323234303023"));
	}
}
