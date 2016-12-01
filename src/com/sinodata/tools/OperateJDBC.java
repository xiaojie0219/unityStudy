package com.sinodata.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperateJDBC {
	public PropertiesUtil pu = null;
	public String DBIpAndPort = null;
	public static final String DBDRIVER = "com.sybase.jdbc3.jdbc.SybDriver";
	public String DBURL = null;
	private String DBUSER = null;
	private String DBPASS = null;

	public static final String SQL = "INSERT INTO test_jay(description,request,response,expectReturncode,actualReturncode,successFlag) "
			+ "VALUES (?,?,?,?,?,?)";
	public Connection conn;
	public PreparedStatement pstmt;

	public OperateJDBC() throws ClassNotFoundException, SQLException {
		pu = new PropertiesUtil();
		DBIpAndPort = pu.read("config.properties", "DBIpAndPort");
		DBURL = "jdbc:sybase:Tds:"
				+ pu.read("config.properties", "DBIpAndPort") + "/"
				+ pu.read("config.properties", "DBName")
				+ "?language=us_english&charset=cp936";
		DBUSER = pu.read("config.properties", "DBUser");
		DBPASS = pu.read("config.properties", "DBPassword");
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		pstmt = conn.prepareStatement(SQL);

	}

	/**
	 * 添加批处理
	 * @param description 用例描述
	 * @param request 请求参数
	 * @param response 接口响应内容
	 * @param expectReturncode 预期返回码
	 * @param actualReturncode 实际返回码
	 * @param successFlag 成功标志，1为成功，0为失败
	 * @throws SQLException
	 */
	public void addBatchJDBC(String description, String request, String response, String expectReturncode,String actualReturncode,
			boolean successFlag) throws SQLException {
		pstmt.setString(1, description);
		pstmt.setString(2, request);
		pstmt.setString(3, response);
		pstmt.setString(4, expectReturncode);
		pstmt.setString(5, actualReturncode);
		pstmt.setBoolean(6, successFlag);
		pstmt.addBatch();// 添加批处理
	}

	/**
	 * 执行批处理操作
	 * @throws SQLException
	 */
	public void insertJDBC() throws SQLException {
		pstmt.executeBatch();// 执行批处理操作
	}

	/**
	 * 关闭数据库连接
	 * @throws SQLException
	 */
	public void closeJDBC() throws SQLException {

		pstmt.close();
		conn.close();
	}
}
