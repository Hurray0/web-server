// Project: web server and browser based on java
// Part: Server
// File: server.java
// Remarks: 基于Java的web服务器 主程序
// Author: Hurray Zhu
// Time: 2014.09.16
// E-mail: i@ihurray.com
// Web-site: http://blog.ihurray.com

//通信包
import java.io.*;
import java.net.*;
import java.util.*;
//线程池包
import java.util.concurrent.*;

public class Server {
    
    public static void main(String args[])
    {
        new Server();
    }
    public Server()
    {
        // 线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        System.out.println("主线程池启动");
        try{
            ServerSocket serverSocket = new ServerSocket(8080);
            while(true)
            {   
                Socket socket = serverSocket.accept();
                // System.out.println(socket);
                ServerThread thread = new ServerThread(socket);
                executor.execute(thread);
            }
        }
        catch(Exception e)
        {}
    }
    
}  