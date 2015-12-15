package test;

import com.corerax.client.Client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by chengchuan on 2015/12/15.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8888);
        String message = "这是发送的信息";
        //发送消息
        Client client = new Client();
        client.sendMessage(message, socket);
    }
}
