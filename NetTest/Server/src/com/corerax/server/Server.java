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
        //等待客户端连接，未连接之前在这里阻塞的,返回的是客户端的一个连接socket
        Socket socket = serverSocket.accept();
        //获取字节输入流
        InputStream inputStream = socket.getInputStream();
        //将字节输入流包装成字符输入流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //设置缓冲
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String dates = null;

        while ((dates = bufferedReader.readLine())!=null){
            System.out.println("我是服务端，我接收到的数据是："+dates);
        }

        //关闭资源
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();
    }
}
