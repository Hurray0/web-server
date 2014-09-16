// Project: web server and browser based on java
// Part: Server
// File: serverDemo.java
// Remarks: 主线程
// Author: Hurray Zhu
// Time: 2014.09.16
// E-mail: i@ihurray.com
// Web-site: http://blog.ihurray.com

//通信包
import java.io.*;
import java.net.*;
import java.util.*;

public class ServerThread extends Thread {
    private Socket socket; 

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run()
    {
        try{
            DataInputStream input = new DataInputStream(socket.getInputStream());
            PrintStream output = new PrintStream(socket.getOutputStream());

            for(int i=0;i<7;i++){
                String readHead = input.readLine();
                    // System.out.println(readHead);
            }

            String myout = ReadHtml.print("url/helloworld.html");

            output.println("HTTP/1.0 200 OK");
            output.println("MIME_version:1.0");
            output.println("Content-Type:text/html");

            output.println("Content-Length:"+myout.length());
            output.println("");
            output.println(myout); 
            output.flush();

            socket.close();

        }
        catch(Exception e){}

    }
}