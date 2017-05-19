package com.sinodata.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 采用MD5加密解密
 * 
 * @author tfq
 * @datetime 2011-10-13
 */
public class MD5Util {

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	protected static MessageDigest messagedigest = null;
	
    /**
     * MessageDigest初始化
     * 
     * @author 
     */
    static {
            try {
                    messagedigest = MessageDigest.getInstance("MD5");  
            } catch (NoSuchAlgorithmException e) {  
                    System.err.println("MD5FileUtil messagedigest初始化失败");  
                    e.printStackTrace();
            }  
    }
	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	/**
	 * 对文件进行MD5加密
	 * 
	 * @author
	 */
	public static String getFileMD5String(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
				file.length());
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			char c0 = hexDigits[(bytes[l] & 0xf0) >> 4];
			char c1 = hexDigits[bytes[l] & 0xf];
			stringbuffer.append(c0);
			stringbuffer.append(c1);
		}
		return stringbuffer.toString();
	}

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @author
	 */
	public static String getMD5String(String s) {
		return getMD5String(s.getBytes());
	}

	/**
	 * 对byte类型的数组进行MD5加密
	 * 
	 * @author
	 */
	public static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	// 测试主函数
	public static void main(String args[]) {
//		String s = new String(
//				"/3DqoDRXl7MfgSadr+zHUxDkquF67k3KkMaVQHIvrLw8yIyMSGYh1hR31iBtbrxsgDnfqFoj6nuejjLfvObaxmC7+j27Y6iuTHENYkM/rTs96Mpn4A9oUuX2Y0QB93X8xpysaGMGjwY9oH2CrygZfGdnevWnL645ENQMer9U6pipazuYEef/GfVLgyfOuhQ96G829QVoYDQvM/wQOV+4wIn/YhUjerEjqtfqiWnr1+UoB1bgxWobzfEKFglibhTEEEKA1N/27uJSQ0DkklPOZl9CoWPfvonw+enEW0VJtNcgYKfWIQlxsDg3Fbw/fmChE2Qr6hbSFhYBQwFdAXk4DA==d11dc87d82db8174d3d8259e111a8509");
//		System.out.println("原始：" + s);
//		System.out.println("MD5后：" + string2MD5(s));
//		// System.out.println("加密的：" + convertMD5(s));
//		System.out.println("解密的：" + convertMD5(convertMD5(s)));
		try {
//			System.out.println("字符串MD5码加密:" + MD5Util.getMD5String("张三001\r\n002".getBytes("utf-8")));
			System.out.println(MD5Util.getMD5String("湖南快10	1030021	43010089	12345678	2.00	2016-06-27 19:36:00	15918271727	精神生	376361625361626262	湖南天心区	2016-06-28 09:36:01\r\n1287F8EB92797EBD25152C0D".getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}