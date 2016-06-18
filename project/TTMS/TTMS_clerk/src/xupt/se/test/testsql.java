package src.xupt.se.test;

import src.xupt.se.ttms.model.User;

/**
 * Created by kiosk on 6/17/16.
 */
public class testsql {
    private static User user = new User("yang","123",0,0);

    public static void main(String[] args) {
        String sql = "update user set " + " user_name ='"
                + user.getUsername() + "', " + " user_passwd = '"
                + user.getUserPasswd() + "', " + " user_leftmoney = '"
                + user.getUserLeftMoney() + "', " + " user_status = '"
                + user.getUserStatus() + "'" ;


        sql += " where user_name = " + "'"+user.getUsername()+"'";
        System.out.println(sql);
    }
}
