package com.corerax.server;

import java.io.*;
import java.net.Socket;

/**
 * Created by chengchuan on 2015/12/15.
 */
public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(){}

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run() {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        StringBuffer datas = new StringBuffer();
        try {
            //获取字节输入流
            inputStream = socket.getInputStream();
            //将字节输入流包装成字符输入流
            inputStreamReader = new InputStreamReader(inputStream);
            //设置缓冲
            bufferedReader = new BufferedReader(inputStreamReader);
            //设置变量读取行
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("服务端接收到一条消息：" + line);
                datas.append(line);
            }
            //关闭输入流
            socket.shutdownInput();

            /**
             * 服务端返回消息
             */
            //获取字节输出流
            outputStream = socket.getOutputStream();
            //包装为打印流
            printWriter = new PrintWriter(outputStream);

            //发送消息
            printWriter.write("已经收到你的消息！");
            //清空缓冲区
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
                if (inputStreamReader!=null){
                    inputStreamReader.close();
                }
                if (inputStream!=null){
                    inputStream.close();
                }
                if (printWriter!=null){
                    printWriter.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
