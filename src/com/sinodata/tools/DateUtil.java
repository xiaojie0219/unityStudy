package com.sinodata.tools;




import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;



public class DateUtil
{
	
	/**
	 * 获取数据库前一天的日期
	 * @return
	 */
//	public static String getYestodayDateByDb()
//	{
//		String dbDate = "";
//		String pSql = "select convert(char,dateadd(DD,-1,getdate()),112)";
//		Connection conn = AnalysisDao.getInstance().getConnection();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		try
//		{
//			pstm = conn.prepareStatement(pSql);
//			rs = pstm.executeQuery();
//			if (rs.next())
//			{
//				dbDate = rs.getString(1);
//			}
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			AnalysisDao.close(conn, pstm, rs);
//		}
//		return dbDate.trim();	
//	}
	
	/**
	 * 获取数据库后一天的日期
	 * @return
	 */
//	public static String getTomorrowDateByDb()
//	{
//		String dbDate = "";
//		String pSql = "select convert(char,dateadd(DD,+1,getdate()),112)";
//		Connection conn = AnalysisDao.getInstance().getConnection();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		try
//		{
//			pstm = conn.prepareStatement(pSql);
//			rs = pstm.executeQuery();
//			if (rs.next())
//			{
//				dbDate = rs.getString(1);
//			}
//		}
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		finally
//		{
//			AnalysisDao.close(conn, pstm, rs);
//		}
//		return dbDate.trim();	
//	}

	
	
	/**
	 * 将时间进行格式转换
	 * @param dateStr
	 * @return
	 */
	public static String changeDate(String dateStr)
	{
		SimpleDateFormat sdf = null;
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dateStr);
	}

	/**
	 * 获取字符串中日编号
	 * @param dateStr
	 * @return
	 */
	public static String getDateDD(String dateStr)
	{
		return dateStr.substring(8, 10);
	}

	/**
	 * 获取字符串中年和月
	 * @param dateStr
	 * @return
	 */
	public static String getDateYYYYMM(String dateStr)
	{
		StringBuffer returnStr = new StringBuffer(dateStr.subSequence(0, 4));
		returnStr.append(dateStr.subSequence(5, 7));
		return returnStr.toString();
	}

	/**
	 * 获取时间差
	 * @param startDate
	 * @param endDate
	 * @return
	 */
//	public static long calcSeconds(	String startDate,
//									String endDate)
//	{
//		Date sDate, eDate;
//		long ls = 0l, le = 0l;
//		String format = "yyyy-MM-dd HH:mm:ss";
//		SimpleDateFormat sf = new SimpleDateFormat(format);
//		try
//		{
//			sDate = sf.parse(startDate);
//			eDate = sf.parse(endDate);
//			Calendar c = Calendar.getInstance();
//			c.setTime(sDate);
//			ls = c.getTimeInMillis();
//			c.setTime(eDate);
//			le = c.getTimeInMillis();
//		}
//		catch (ParseException e)
//		{
//			e.printStackTrace();
//		}
//		return (le - ls);
//	}

	/**
	 * 获得明天日期
	 * @param specifiedDay
	 * @return
	 */
	public static String getTomorrowDate()
	{
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		String tomorrow = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return tomorrow;
	}

	/**
	 * 获得昨天日期
	 * @param specifiedDay 2013-03-29
	 * @return
	 */
	public static String getYestoday10Date()
	{
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		String yes = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		String tmpYes1 = yes.substring(0, 4);
		String tmpYes2 = yes.substring(4, 6);
		String tmpYes3 = yes.substring(6, 8);
		yes = tmpYes1 + "-" + tmpYes2 + "-" + tmpYes3;
		return yes;
	}
	
	/**
	 * 获得指定日期的后一天
	 * @param specifiedDay "yyyy-MM-dd"
	 * @return
	 */
//	public static String getSpecifiedDayAfter(String specifiedDay)
//	{
//		Calendar c = Calendar.getInstance();
//		Date date = null;
//		try
//		{
//			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
//		}
//		catch (ParseException e)
//		{
//			e.printStackTrace();
//		}
//		c.setTime(date);
//		int day = c.get(Calendar.DATE);
//		c.set(Calendar.DATE, day + 1);
//
//		String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
//		return dayAfter;
//	}
	
	
	/**
	 * 取得当前时间（格式为：yyyy-MM-dd HH:mm:ss）
	 * @return String
	 */
	public static String getDateTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate = sdf.format(new Date());
		return sDate;
	}

//	/**
//	 * 取得当前时间（格式为：yyyyMMddHHmmssSSS）保留毫秒
//	 * @return String
//	 */
//	public static String getDateTimeByMillisecond()
//	{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//		String sDate = sdf.format(new Date());
//		return sDate;
//	}
	
	
	/**
	 * 取得当前时间（格式为：yyyyMMddHHmm）保留毫秒
	 * @return String
	 */
	public static String getDateTimeByMinute()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String sDate = sdf.format(new Date());
		return sDate;
	}
	
	/**
	 * 取得当前时间（格式为：yyMMddHHmm）保留毫秒
	 * @return String
	 */
	public static String getDateTimeByMinute1()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
		String sDate = sdf.format(new Date());
		return sDate;
	}

	/**
	 * 3位随机数
	 * @return
	 */
//	public static String getSellGlideCode()
//	{
//		String reslut = "";
//		for (int i = 0; i < 3; i++)
//		{
//			String sumStr = "0123456789";
//			int index = (int) (Math.random() * 10);
//			String buffStr = String.valueOf(sumStr.charAt(index));
//			reslut = reslut + buffStr;
//		}
//		return reslut;
//	}

	/**
	 * 取得当前时间（格式为：yyyyMMdd）
	 * @return String
	 */
	public static String getDateBy8bit()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String sDate = sdf.format(new Date());
		return sDate;
	}
	

	
	
	

//	public static String getDbDate()
//	{
//
//		String dbDate = "";
//		String pSql = "select getDate()";
//		Connection conn = AnalysisDao.getInstance().getConnection();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		try
//		{
//			pstm = conn.prepareStatement(pSql);
//			rs = pstm.executeQuery();
//			if (rs.next())
//			{
//				dbDate = rs.getString(1);
//				dbDate = dbDate.substring(0, 19);
//			}
//		}
//		catch (SQLException e)
//		{
//
//		}
//		finally
//		{
//			AnalysisDao.close(conn, pstm, rs);
//		}
//		// dbDate= dbDate.replaceAll("2012-06-15", "2012-07-04");
//		return dbDate;
//	}
	
	/**
	 * 2012-12-21 09:11:03
	 * 取得当前时间（格式为：yyyy-MM-dd HH:mm:ss）
	 * @return String
	 */
	public static String getLocalDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate = sdf.format(new Date());
		return sDate;
	}
	

	// 201212211101
//	public static long getDbDate2Long()
//	{
//		long dbDateLong = 0;
//		String dbDate = "";
//		String pSql = "select getDate()";
//		Connection conn = AnalysisDao.getInstance().getConnection();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		try
//		{
//			pstm = conn.prepareStatement(pSql);
//			rs = pstm.executeQuery();
//			if (rs.next())
//			{
//				dbDate = rs.getString(1);
//				dbDate = dbDate.substring(0, 16);
//			}
//			dbDate = dbDate.replaceAll("-", "");
//			dbDate = dbDate.replaceAll(":", "");
//			dbDate = dbDate.replaceAll(" ", "");
//			// dbDate = dbDate.replaceAll(".", "");
//			dbDateLong = Long.parseLong(dbDate);
//		}
//		catch (SQLException e)
//		{
//
//		}
//		finally
//		{
//			AnalysisDao.close(conn, pstm, rs);
//		}
//		// dbDate= dbDate.replaceAll("2012-06-15", "2012-07-04");
//		return dbDateLong;
//	}

	//201212211101
	public static long getLocalDate2Long()
	{
		long dateLong = Long.valueOf(getDateTimeByMinute());		
		return dateLong;
	}
	
	

	/**
	 * 获取当前年份 2012
	 * @return
	 */
//	public static String getDbDateBy4bit()
//	{
//		String dbDate = getDbDate();
//		dbDate = dbDate.replace("-", "");
//		dbDate = dbDate.substring(0, 4);
//		return dbDate;
//	}
	
	
	/**
	 * 获取当前年份 2012
	 * @return
	 */
	public static String getDateBy4bit()
	{
		String dbDate = getLocalDate();
		dbDate = dbDate.replace("-", "");
		dbDate = dbDate.substring(0, 4);
		return dbDate;
	}

	/**
	 * 获取当前年月日 20120308
	 * @return
	 */
//	public static String getDbDateBy8bit()
//	{
//		String dbDate = getDbDate();
//		dbDate = dbDate.replace("-", "");
//		dbDate = dbDate.substring(0, 8);
//		return dbDate;
//	}
	


	/**
	 * 获取当前年月日 2012-04-12
	 * @return
	 */
//	public static String getDbDateBy10bit()
//	{
//		String dbDate = getDbDate();
//		dbDate = dbDate.substring(0, 10).toString();
//		return dbDate;
//	}

	/**
	 * 获取当前年月日 2012-04-12
	 * @return
	 */
	public static String getDateBy10bit()
	{
		String dbDate = getLocalDate();
		dbDate = dbDate.substring(0, 10).toString();
		return dbDate;
	}
	
	
	
//	public static String getMySqlDbDate()
//	{
//
//		String dbDate = "";
//		String pSql = "select SYSDATE()";
//		Connection conn = SmsDao.getInstance().getConnection();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		try
//		{
//			pstm = conn.prepareStatement(pSql);
//			rs = pstm.executeQuery();
//			if (rs.next())
//			{
//				dbDate = rs.getString(1);
//				dbDate = dbDate.substring(0, 19);
//			}
//		}
//		catch (SQLException e)
//		{
//			e.getMessage();
//		}
//		finally
//		{
//			SmsDao.close(conn, pstm, rs);
//		}
//		return dbDate;
//	}

	/**
	 * 获取当前年月日 20120809
	 * @return
	 */
	public static String getDbDateBy8bitByTime(String tmpTime)
	{
		tmpTime = tmpTime.replace("-", "");
		tmpTime = tmpTime.substring(0, 8);
		return tmpTime;
	}

	/**
	 * 获取当前时间 10点08分  1008
	 * @return
	 */
//	public static long getDbTimeBy12bitByTime()
//	{
//		String tmpString = getDbDate();		
//		String tmpStr[] = tmpString.split(" ");
//		tmpString = tmpStr[1];
//		tmpString = tmpString.replace(":", "");
//		tmpString = tmpString.substring(0, 4);
//		return Integer.valueOf(tmpString);
//	}
	
	
	public static String getYearMonthDay(String tmpTime)
	{
		StringBuffer sb = new StringBuffer();
		tmpTime = tmpTime.replace("-", "");
		tmpTime = tmpTime.substring(0, 8);
		sb.append(tmpTime.substring(0, 4)).append("年");
		sb.append(tmpTime.substring(4, 6)).append("月");
		sb.append(tmpTime.substring(6, 8)).append("日");
		return sb.toString();
	}
	
	/*
	 * 获取小时分 例子：1018   10点18分
	 */
//	public static int getHourMin()
//	{
//		String currDte = getLocalDate();
//		currDte = currDte.substring(11,16).replaceAll(":", "");
//		return Integer.parseInt(currDte);
//	}
	
	public static void main(String arg[])
	{
		// Double aaa=Double.parseDouble(getSellGlideCode());
		// System.out.println((int)(Math.random()*10));
//		System.out.println(getTomorrowDateByDb());
//		System.out.println(getDbDate2Long());
//		System.out.println(getTomorrowDate());
		
		
//		 String s = "2012-05-21 23:59:59";
//		 System.out.println(getYearMonthDay(s));
		
//		long encashTime = Long.valueOf(DateUtil.getDateBy8bit() + Configuration.getInstance().getEncashTime());
		long currTime = Long.valueOf(DateUtil.getDateTimeByMinute());
//		System.out.println(encashTime + 100);
		System.out.println(currTime);
	}

}
