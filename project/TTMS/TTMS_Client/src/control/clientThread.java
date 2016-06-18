package control;

import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by kiosk on 6/16/16.
 */

public class clientThread implements Runnable{
    private Socket s;

    BufferedReader br = null;
    public clientThread(Socket s) throws IOException {
        this.s = s;
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    public void run() {
        try {
            String content;
            while((content = readFromServer()) != null) {
                org.json.JSONObject object = new org.json.JSONObject(content);

                if(Integer.parseInt(object.get("type").toString()) == 1) {
                    main.user = new User(object.get("username").toString(),
                            object.get("userPasswd").toString(),
                            Integer.parseInt(object.get("userLeftMoney").toString()),
                            Integer.parseInt(object.get("userStatus").toString()) );
                }

            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String readFromServer() {
        try {
            return br.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
