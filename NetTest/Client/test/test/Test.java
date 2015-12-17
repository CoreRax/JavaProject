package test;

import com.corerax.client.Client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by chengchuan on 2015/12/15.
 */
public class Test {
    public static void main(String[] args) throws IOException {

        //发送消息
        Client client = new Client();
        client.getV2UpToDate();
    }
}
