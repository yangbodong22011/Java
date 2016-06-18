package test;

import model.User;
import net.sf.json.JSONObject;

/**
 * Created by kiosk on 6/16/16.
 */
public class testJson {
    public static void main(String[] args) {
        User user = new User("yang","123",100,1);
        JSONObject object = JSONObject.fromObject(user);
        //user = (User)JSONObject.toBean(object,User.class);
        //Product product=(Product)JSONObject.toBean(object,Product.class);
        System.out.println(object.get("username").toString());
        System.out.println(object.get("userPasswd").toString());
        System.out.println(object.get("userLeftMoney").toString());
        System.out.println(object.get("userStatus").toString());

    }
}

