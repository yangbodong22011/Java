package src.xupt.se.ttms.main;

import org.omg.PortableInterceptor.INACTIVE;
import src.xupt.se.ttms.model.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by kiosk on 6/16/16.
 */
public class main {
    private static final int SERVER_PORT = 30000;
    public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
    public static HashMap<Socket,User> socketUserHashMap = new HashMap<>();

    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(SERVER_PORT);    //创建服务器端的套接字

        // 初始化服务器端的main界面.

        while(true) {
            Socket s = ss.accept();
            socketList.add(s);
            new Thread(new serverThread(s)).start();
        }
    }

}
