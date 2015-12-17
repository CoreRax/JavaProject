package com.corerax.client;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
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

    /**
     * 发送信息到socket服务器
     * @param message 信息内容
     * @param socket soket对象
     * @return 返回信息
     * @throws IOException
     */
    public String sendMessage(String message, Socket socket) throws IOException {
        String result = "";
        //获取输出字节流
        OutputStream outputStream = socket.getOutputStream();

        //获取打印流
        PrintWriter printWriter = new PrintWriter(outputStream);

        printWriter.write(message);
        //清空缓冲区
        printWriter.flush();
        //关闭输入流
        socket.shutdownOutput();


        /**
         * 接收消息
         */
        //获取字节输入流
        InputStream inputStream = socket.getInputStream();
        //将字节输入流包装成字符输入流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //设置缓冲
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //设置变量读取行
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            result+=line;
        }

        //关闭资源
        printWriter.close();
        outputStream.close();
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();

        return result;
    }

    /**
     * 获取v2ex网站上最新的内容
     * @return
     */
    public String getV2UpToDate(){
        URL url = null;
        String data = "";
        try {
            url = new URL("https://www.v2ex.com/api/topics/latest.json");
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line ="";
            while ((line = bufferedReader.readLine())!=null){
                data += line;
            }
            System.out.println(data);
        } catch (MalformedURLException e) {
            System.out.println("连接失败");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args) {
        Socket socket = null;
        String message = "这是发送的第二条信息";
        String result = null;
        //发送消息
        Client client = new Client();
        try {
            socket = new Socket(serverIP, serverPort);
            result = client.sendMessage(message, socket);
            System.out.println("服务器回复的消息是：" + result);
        } catch (UnknownHostException e) {
            System.out.println("未知的主机");
        } catch (IOException e) {
            System.out.println("信息发送失败");
            e.printStackTrace();
        }
    }
}
