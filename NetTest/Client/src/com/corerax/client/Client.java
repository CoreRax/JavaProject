package com.corerax.client;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * socket客户端
 * Created by chengchuan on 2015/12/15.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888);
        //获取输出字节流
        OutputStream outputStream = socket.getOutputStream();
        //将字节流包装成字符流
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        //设置缓冲区
        PrintWriter printWriter = new PrintWriter(bufferedOutputStream);

        printWriter.write("你好，我是客户端");
        //清空缓冲区
        printWriter.flush();

        //关闭资源
        printWriter.close();
        bufferedOutputStream.close();
        outputStream.close();
        socket.close();
    }
}
