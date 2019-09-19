package com.fingard.xuesl.netty.share.oio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: xuesl
 * 开发时间: 2018/3/2<br>
 * <br>
 */
public class OioServer {
    public static void main(String[] args) {
        new OioServer().start();
    }

    public void start(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String request,response;
            while((request = in.readLine()) != null){
                if("Done".equals(request)){
                    break;
                }
                response = processRequest(request);
                out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String processRequest(String request){
        return "";
    }
}
