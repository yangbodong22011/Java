package src.xupt.se.ttms.main;


import net.sf.json.JSONObject;
import src.xupt.se.ttms.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class serverThread implements Runnable{
    Socket s = null;
    BufferedReader br = null;
    PrintStream ps;
    public serverThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ps = new PrintStream(s.getOutputStream());
        new logs("accept a new connection"+"  IP:"+s.getInetAddress());
    }
    public void run() {
        try {
            String content;
            while((content = readFromClient()) != null) {
                org.json.JSONObject object = new org.json.JSONObject(content);

                if(Integer.parseInt(object.get("type").toString()) == 1) {
                    User user = new User();
                    user = new handleConnect(s,object).passwdIsTure();
                    user.setType(1);
                    JSONObject object1 = JSONObject.fromObject(user);
                    ps.println(object);
                }
            }

        }catch (Exception e) {

        }
    }

    private String readFromClient() {
        try {
            return br.readLine();
        }catch (IOException e) {
            main.socketList.remove(s);
        }
        return null;
    }
}
