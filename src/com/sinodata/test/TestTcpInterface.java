package com.sinodata.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestTcpInterface {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket client = new Socket("10.10.37.149",8999);
		File file = new File("D:\\1\1.txt");
		
		BufferedReader buf = null;
		buf= new BufferedReader(new InputStreamReader(client.getInputStream()));
		String str = buf.readLine();
		System.out.println(str);
		client.close();
	}
}
