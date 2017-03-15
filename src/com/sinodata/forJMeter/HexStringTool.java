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
		System.out.println("十六进制转为字符串:" + HexStringTool.hexStr2Str("32303137303330313136323434343539303933302353445f41424323533030302331323334353637383930313131313138363023d7d3c1fa235353515f333036236a617931373033303131363038343539393639233123323130383131313935393039303139333734233138393132363636363636233123302330233123312330312c30342c30382c30392c31342c32383a313623322336313230302330233135332331353323"));
	}
}
