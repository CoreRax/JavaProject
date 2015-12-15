package com.corerax.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 * Created by chengchuan on 2015/12/15.
 */
public class Server {

    /**
     * 等待客户端连接，打印客户端发来的信息
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("***这里是服务端，正在等待连接***");
        /**
         * 在一个死循环中监听客户端的请求，有的新的请求就打开一个新的线程处理
         */
        while (true){
            Socket socket = serverSocket.accept();
            ServerThread serverThread = new ServerThread(socket);
            serverThread.start();
        }
    }
}
