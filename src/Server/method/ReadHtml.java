// Project: web server and browser based on java
// Part: Server
// File: serverDemo.java
// Remarks: 读HTML的方法实现
// Author: Hurray Zhu
// Time: 2014.09.16
// E-mail: i@ihurray.com
// Web-site: http://blog.ihurray.com

import java.io.*;

public class ReadHtml {
	public static String outputMsg = "";

	public static String print(String filename) throws Exception
	{
		outputMsg = "";
		File file = new File(filename);
		System.out.println(file.getAbsolutePath());
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String tempString = null;

		int line = 1;
		while ((tempString = reader.readLine()) != null) {
			outputMsg += tempString+"\n";
		}
		reader.close();

		return outputMsg;
	}
}