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

	public static String print(String filename)
	{
		File file = new File(filename);
		System.out.println(file.getAbsolutePath());
		BufferedReader reader = null;
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			
			int line = 1;
            // 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
                // 显示行号
				//System.out.println("line " + line + ": " + tempString);
				//line++;
				outputMsg += tempString+"\n";
			}
			// System.out.println(outputMsg);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return outputMsg;
	}

	public static void main(String args[])
	{
		// ReadHtml newfile = new ReadHtml();
		System.out.println(ReadHtml.print("helloworld.html"));
	}
	
}