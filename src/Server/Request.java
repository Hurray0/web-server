// Project: web server and browser based on java
// Part: Server
// File: Request.java
// Remarks: 处理request-header的类  
// Author: Hurray Zhu
// Time: 2014.09.18
// E-mail: i@ihurray.com
// Web-site: http://blog.ihurray.com

//通信包
import java.io.*;
import java.net.*;
import java.util.*;

public class Request {  

    private InputStream input;  
    private String url;  

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
        url = parseurl(request.toString());  
    }  

    private String parseurl(String requestString) {  
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

    public String geturl() {  
        return url;  
    }  

}  