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

                Request request = new Request(input);
                request.parse(); 

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
    
    public class Request {  

        private InputStream input;  
        private String uri;  

        public Request(InputStream input) {  
            this.input = input;  
        }  

        public void parse() {  
            // Read a set of characters from the socket  
            StringBuffer request = new StringBuffer(2048);  
            int i;  
            byte[] buffer = new byte[2048];  
            try {  
                i = input.read(buffer);  
            }  
            catch (IOException e) {  
                e.printStackTrace();  
                i = -1;  
            }  
            for (int j=0; j<i; j++) {  
                request.append((char) buffer[j]);  
            }  
            System.out.print(request.toString());  
            uri = parseUri(request.toString());  
        }  

        private String parseUri(String requestString) {  
            int index1, index2;  
            index1 = requestString.indexOf(' ');  
            if (index1 != -1) 
            {  
                index2 = requestString.indexOf(' ', index1 + 1);  
                if (index2 > index1)  
                    return requestString.substring(index1 + 1, index2);  
            }  
            return null;  
        }  

        public String getUri() {  
            return uri;  
        }  

    }  
}