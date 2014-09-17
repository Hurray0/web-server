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

            Request request = new Request(input);
            request.parse();
            System.out.println(request.geturl());

            String myout;
            System.out.println(myout);
            try{
                myout = ReadHtml.print("url"+request.geturl());
            }
            catch(Exception e)
            {
                myout = "404 not found";
            }

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