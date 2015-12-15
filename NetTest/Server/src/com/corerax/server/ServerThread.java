package com.corerax.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
