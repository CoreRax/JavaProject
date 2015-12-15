package com.corerax.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * socket客户端
 * Created by chengchuan on 2015/12/15.
 */
public class Client {

    private static String serverIP = null;
    private static int serverPort = 0;

    static {
        //加载配置信息
        loadConfig();
    }
    /**
     * 加载配置信息
     */
    public static void loadConfig() {
        try {
            InputStream inStream = Client.class.getResourceAsStream("/serverAddr.properties");
            Properties prop = new Properties();
            prop.load(inStream);
            serverIP = prop.getProperty("server.ip");
            serverPort = Integer.parseInt(prop.getProperty("server.port"));
        } catch (IOException e) {
            throw new RuntimeException("读取配置文件异常", e);
        }
    }

    public void sendMessage(String message, Socket socket) throws IOException {
        //获取输出字节流
        OutputStream outputStream = socket.getOutputStream();
        //将字节流包装成字符流
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        //设置缓冲区
        PrintWriter printWriter = new PrintWriter(bufferedOutputStream);

        printWriter.write(message);
        //清空缓冲区
        printWriter.flush();

        //关闭资源
        printWriter.close();
        bufferedOutputStream.close();
        outputStream.close();
        socket.close();
    }


    public static void main(String[] args) {
        Socket socket = null;
        String message = "这是发送的第二条信息";
        //发送消息
        Client client = new Client();
        try {
            socket = new Socket(serverIP, serverPort);
            client.sendMessage(message, socket);
        } catch (UnknownHostException e) {
            System.out.println("未知的主机");
        } catch (IOException e) {
            System.out.println("信息发送失败");
        }
    }
}
