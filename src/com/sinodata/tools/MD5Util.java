package com.sinodata.tools;

import java.security.MessageDigest;  
  
/** 
 * 采用MD5加密解密 
 * @author tfq 
 * @datetime 2011-10-13 
 */  
public class MD5Util {  
  
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
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
        for (int i = 0; i < md5Bytes.length; i++){  
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
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
  
    // 测试主函数  
    public static void main(String args[]) {  
        String s = new String("/3DqoDRXl7MfgSadr+zHUxDkquF67k3KkMaVQHIvrLw8yIyMSGYh1hR31iBtbrxsgDnfqFoj6nuejjLfvObaxmC7+j27Y6iuTHENYkM/rTs96Mpn4A9oUuX2Y0QB93X8xpysaGMGjwY9oH2CrygZfGdnevWnL645ENQMer9U6pipazuYEef/GfVLgyfOuhQ96G829QVoYDQvM/wQOV+4wIn/YhUjerEjqtfqiWnr1+UoB1bgxWobzfEKFglibhTEEEKA1N/27uJSQ0DkklPOZl9CoWPfvonw+enEW0VJtNcgYKfWIQlxsDg3Fbw/fmChE2Qr6hbSFhYBQwFdAXk4DA==d11dc87d82db8174d3d8259e111a8509");  
        System.out.println("原始：" + s);  
        System.out.println("MD5后：" + string2MD5(s));  
//        System.out.println("加密的：" + convertMD5(s));  
        System.out.println("解密的：" + convertMD5(convertMD5(s)));  
  
    }  
}  