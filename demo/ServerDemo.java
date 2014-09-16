// Project: web server and browser based on java
// Part: Server
// File: serverDemo.java
// Remarks: web服务器demo  
// Author: Hurray Zhu
// Time: 2014.09.16
// E-mail: i@ihurray.com
// Web-site: http://blog.ihurray.com

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerDemo {
    
    public static void main(String args[])
    {
        new ServerDemo();
    }
    public ServerDemo()
    {
        try{
            ServerSocket serverSocket = new ServerSocket(8080);
            while(true)
            {   
                Socket socket = serverSocket.accept();
                // System.out.println(socket);
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        }
        catch(Exception e)
        {}
    }
    class ServerThread extends Thread {
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
                    Stirng readHead = input.readLine();
                    // System.out.println(readHead);
                }

                String myout = "<h1>Hello Hurray!</h1>";

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
}  