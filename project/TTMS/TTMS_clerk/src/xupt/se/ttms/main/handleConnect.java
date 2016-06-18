package src.xupt.se.ttms.main;

import src.xupt.se.ttms.dao.UserDAO;
import src.xupt.se.ttms.model.User;

import java.net.Socket;

/**
 * Created by kiosk on 6/16/16.
 */
public class handleConnect {
    Socket s = null;
    org.json.JSONObject object;
    String name;
    String passwd;


    public handleConnect() {}
    public handleConnect(Socket s, org.json.JSONObject object) {
        this.s = s;
        this.object = object;
        name = object.get("username").toString();
        passwd = object.get("userPasswd").toString();
    }

    public User passwdIsTure (){

        User user = new UserDAO().select(name);

        if(user.getUserPasswd().equals(passwd)) {
            user.setUserStatus(1);                //设置用户的登录状态为1
            new UserDAO().update(user);
            main.socketUserHashMap.put(s,user);
            return user;
        }else {
            return null;
        }
    }

}
