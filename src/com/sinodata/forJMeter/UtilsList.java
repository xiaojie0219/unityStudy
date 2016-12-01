package com.sinodata.forJMeter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 
 */


/**
 * @author Administrator
 *
 */
public class UtilsList
{

	/**
	 * @param args
	 */
	public  String getRunCode(){
		String tmp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+new Random().nextInt(1000);
		return tmp;
	}
	public  String create16Runcode()
	{
		String head = "1";
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
		String tmpDate = sdf.format(new Date());
		String tail = createRandomCode();
		return head+tmpDate+tail;
	}
	
	// 随机码
	public  String createRandomCode()
	{
		String returnStr = "";
		for (int i = 0; i < 5; i++)
		{
			returnStr = returnStr + (int)(Math.random()*10);
		}			
		return returnStr;
	}
	
	// 随机字符串
	public  String createRandomId()
	{
		String returnStr = "";
		for (int i = 0; i < 6; i++)
		{
			returnStr = returnStr + (int)(Math.random()*10);
		}			
		return returnStr;
	}
	
	/**
	 * 
	 * @param inString 输入字符串()
	 * 1)将字符串每一位取整后相加
	 * 2)将总和对10万求余
	 * 3)求余后如果不满5位则在前面补0补成5位
	 * @return
	 */
	public  String getCheckCode(String inString)
	{
		String outString;
		long ret = 0;
		for (int i = 0; i < inString.length(); i++)
		{
			ret += (int) inString.charAt(i);
		}

		ret = ret % 100000;

		outString = String.valueOf(ret);

		// 补0，或可不补
		for (int i = 0; i < 5 - outString.length(); i++)
		{
			outString = "0" + outString;
		}

		return outString;
	}
	
	/**
	 * 传输数据包加解密
	 * @param str
	 * @param flag
	 * @return
	 */
	public  byte[] deOrEncrypt2Bytes(	String str,
											String flag)
	{
		byte key;
		byte[] result = new byte[str.length()];

		for (int i = 0; i < str.length(); i++)
		{
			key = (byte) str.charAt(i);
			if (flag.equals("-"))
			{
				key = (byte) (((key >> 4) & 0x0f) | ((key << 4) & 0xf0));
				key = (byte) (key ^ 0x9f);
			}
			else
			{
				key = (byte) (key ^ 0x9f);
				key = (byte) (((key >> 4) & 0x0f) | ((key << 4) & 0xf0));
			}
			result[i] = key;
		}
		return result;
	}
	
	
	/**
	 * 加，解密
	 * @param b
	 * @param flag
	 * @return
	 */
	public  byte[] deOrEncrypt2Byte(	byte b[],
											String flag)
	{
		byte key;
		byte returnByte[] = b;
		int num = 0;
		for (int i = 0; i < b.length; i++)
		{
			key = b[i];
			if (flag.equals("-"))
			{
				key = (byte) (((key >> 4) & 0x0f) | ((key << 4) & 0xf0));
				key = (byte) (key ^ 0x9f);
			}
			else
			{
				key = (byte) (key ^ 0x9f);
				key = (byte) (((key >> 4) & 0x0f) | ((key << 4) & 0xf0));
			}
			returnByte[i] = key;
			if (key != 0)
			{
				num++;
			}
		}
		byte reByte[] = new byte[num];
		for (int i = 0; i < reByte.length; i++)
		{
			reByte[i] = returnByte[i];
		}
		return reByte;
	}
	
	

}
