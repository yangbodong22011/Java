package control;

import model.User;
import view.loginUI;
import view.logs;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by kiosk on 6/16/16.
 */
public class main {
    private static Socket s;
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 30000;
    public static User user = new User();

    public static void main(String[] args) throws IOException {
        try {
            s = new Socket(SERVER_IP,SERVER_PORT);
            new logs("连接服务器成功");
        }catch (IOException e) {
            new logs("连接服务器出错").showDialog();
        }

        new loginUI(s).init();                           //new 出loginUI
        new Thread(new clientThread(s)).start();         //新线程去处理连接

    }

}
