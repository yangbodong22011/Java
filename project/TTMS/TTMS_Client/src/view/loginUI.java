package view;

import model.User;
import net.sf.json.JSONObject;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


/**
 * Created by kiosk on 6/15/16.
 */
public class loginUI extends JFrame{

    private Socket s;              //传递数据的套接字
    JButton clickButton;           //点击按钮
    JButton loginUILoginButton = new JButton("登录");
    PrintStream ps;
    //JSONWriter js;


    public loginUI() {}
    public loginUI(Socket s) throws IOException{
        this.s = s;
        ps = new PrintStream(s.getOutputStream());
    }

    public void init() {
        this.add(loginUILoginButton);
        loginUILoginButton.addActionListener(new mybutActionListener());

        this.setTitle("未来影院登录");
        this.setSize(800,650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    class mybutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clickButton = (JButton)e.getSource();

            if(clickButton == loginUILoginButton) {
                User user = new User(1,"yang","123");
                JSONObject object = JSONObject.fromObject(user);
                ps.println(object);
            }
        }
    }
}
