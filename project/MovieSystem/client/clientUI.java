package MovieSystem.client;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import jdk.nashorn.internal.scripts.JO;

import javax.naming.BinaryRefAddr;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.List;


public class clientUI extends JFrame {
    CardLayout card = new CardLayout();
    JPanel cardPanel = new JPanel(card);                //采用卡片布局的方式
    JButton clickButton;                                //点击的按钮
    int MyleftMoney = 150;

    //panel 1  登录界面
    /*********************************************************/
    JLabel FirstPanelLabelImage = new JLabel(new ImageIcon("/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/1.png"));
    JLabel FirstPanelLabelName = new JLabel("帐号  ");
    JLabel FirstPanelLabelPasswd = new JLabel("密码  ");
    JTextField FirstPanelFieldName = new JTextField(15);
    JPasswordField FirstPanelFieldPasswd = new JPasswordField(15);

    JButton FirstPanelButton1 = new JButton("登录");
    JButton FirstPanelButton2 = new JButton("注册");

    //panel 2  登录后主界面
    /*********************************************************/

    JLabel SecondPanelUserHeadImage = new JLabel(new ImageIcon("/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/head1.png"));
    JLabel SecondPanelUserName = new JLabel("王晓明");
    JLabel SecondPanelLeftMoney = new JLabel("$" + MyleftMoney);
    JButton SecondPanelViewFilmButton = new JButton("正在上映");
    JButton SecondPanelRechargeButton = new JButton("我要充值");
    JButton SecondPanelChangePasswdButton = new JButton("修改密码");
    JButton SecondPanelViewChargedButton = new JButton("我的影票");

    //panel 3  修改密码界面
    /*********************************************************/
    JLabel ThirdPanelOldPasswd = new JLabel("请输入原始密码  ",JLabel.CENTER);
    JLabel ThirdPanelNewPasswd = new JLabel("请输入新的密码  ",JLabel.CENTER);
    JLabel ThirdPanelConfirmPasswd = new JLabel("请再次输入密码  ",JLabel.CENTER);
    JPasswordField ThirdPanelOldPasswdField = new JPasswordField(10);
    JPasswordField ThirdPanelNewPasswdField = new JPasswordField(10);
    JPasswordField ThirdPanelConfirmPasswdField = new JPasswordField(10);
    JButton ThirdPanelConfirmPasswdButton = new JButton("确认");
    JButton ThirdPanelCanelChangePasswdButton = new JButton("取消");
    JLabel ThirdPanelUserHeadImage = new JLabel(new ImageIcon("/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/head1.png"));
    JLabel ThirdPanelUserName = new JLabel("王晓明");
    JLabel ThirdPanelLeftMoney = new JLabel("$" + MyleftMoney);
    JButton ThirdPanelViewFilmButton = new JButton("正在上映");
    JButton ThirdPanelRechargeButton = new JButton("我要充值");
    JButton ThirdPanelChangePasswdButton = new JButton("修改密码");
    JButton ThirdPanelViewChargedButton = new JButton("我的影票");

    //panel 4  充值界面
    /*********************************************************/
    JLabel FourthPanelChargeMoneyLabel = new JLabel("请输入充值金额  ",JLabel.CENTER);
    JTextField FourthPanelChargeMoneyField = new JTextField(10);
    JLabel FourthPanelChargePasswdLabel = new JLabel("请输入支付密码  ",JLabel.CENTER);
    JPasswordField FourthPanelChargePasswdField  = new JPasswordField(10);
    JButton FourthPanelConfirmChargeMoneyButton = new JButton("确认");
    JButton FourthPanelCanelChargeMoneyButton = new JButton("取消");
    JLabel FourthPanelUserHeadImage = new JLabel(new ImageIcon("/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/head1.png"));
    JLabel FourthPanelUserName = new JLabel("王晓明");
    JLabel FourthPanelLeftMoney = new JLabel("$"+MyleftMoney);
    JButton FourthPanelViewFilmButton = new JButton("正在上映");
    JButton FourthPanelRechargeButton = new JButton("我要充值");
    JButton FourthPanelChangePasswdButton = new JButton("修改密码");
    JButton FourthPanelViewChargedButton = new JButton("我的影票");





    public void init(){
        add(cardPanel);

        //panel 1  登录界面
        /*********************************************************/
        JPanel FirstPanel = new JPanel(new BorderLayout());
        cardPanel.add(FirstPanel);

        FirstPanelButton1.addActionListener(new MyButtonActionListener());

        FirstPanel.add(FirstPanelLabelImage,BorderLayout.NORTH);
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel FirstPanelDown = new JPanel(gb);

        FirstPanel.add(FirstPanelDown,BorderLayout.CENTER);
        FirstPanelDown.setBackground(Color.GRAY);
        FirstPanelLabelName.setFont(new Font("宋体",Font.BOLD,20));
        FirstPanelLabelPasswd.setFont(new Font("宋体",Font.BOLD,20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(FirstPanelLabelName,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(FirstPanelFieldName,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(new JLabel(" "),gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(FirstPanelLabelPasswd,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(FirstPanelFieldPasswd,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(new JLabel(" "),gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(FirstPanelButton1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        FirstPanelDown.add(FirstPanelButton2,gbc);


        //panel 2  登录后主界面
        /*********************************************************/
        JPanel SecondPanel = new JPanel(new BorderLayout());
        cardPanel.add(SecondPanel);

        JPanel SecondPanelUP = new JPanel(new GridLayout(1,7,5,5));
        SecondPanel.add(SecondPanelUP,BorderLayout.NORTH);

        SecondPanelUP.add(SecondPanelUserHeadImage);
        SecondPanelUP.add(SecondPanelUserName);
        SecondPanelUserName.setFont(new Font("宋体",Font.BOLD,20));
        SecondPanelUP.add(SecondPanelLeftMoney);
        SecondPanelLeftMoney.setFont(new Font("宋体",Font.BOLD,15));
        SecondPanelUP.add(SecondPanelViewFilmButton);
        SecondPanelViewFilmButton.setBackground(Color.YELLOW);
        SecondPanelViewFilmButton.setFont(new Font("宋体",Font.BOLD,15));
        SecondPanelViewFilmButton.addActionListener(new MyButtonActionListener());
        SecondPanelUP.add(SecondPanelRechargeButton);
        SecondPanelRechargeButton.setBackground(Color.ORANGE);
        SecondPanelRechargeButton.setFont(new Font("宋体",Font.BOLD,15));
        SecondPanelRechargeButton.addActionListener(new MyButtonActionListener());
        SecondPanelUP.add(SecondPanelChangePasswdButton);
        SecondPanelChangePasswdButton.addActionListener(new MyButtonActionListener());
        SecondPanelChangePasswdButton.setBackground(Color.PINK);
        SecondPanelChangePasswdButton.setFont(new Font("宋体",Font.BOLD,15));
        SecondPanelUP.add(SecondPanelViewChargedButton);
        SecondPanelViewChargedButton.setBackground(Color.GREEN);
        SecondPanelViewChargedButton.setFont(new Font("宋体",Font.BOLD,15));
        SecondPanelViewChargedButton.addActionListener(new MyButtonActionListener());


        JPanel SecondPanelDown = new JPanel(new GridLayout(3,4,10,10));
        SecondPanelDown.setBorder(new MatteBorder(20,5,10,30,Color.LIGHT_GRAY));
        SecondPanel.add(new JScrollPane(SecondPanelDown));

        clientMoviePanel film1 = new clientMoviePanel("十二生肖","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film1.png");
        clientMoviePanel film2 = new clientMoviePanel("饥饿游戏","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film2.png");
        clientMoviePanel film3 = new clientMoviePanel("听风者","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film3.png");
        clientMoviePanel film4 = new clientMoviePanel("复仇者联盟","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film4.png");
        clientMoviePanel film5 = new clientMoviePanel("泰坦尼克号","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film5.png");
        clientMoviePanel film6 = new clientMoviePanel("师父","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film6.png");
        clientMoviePanel film7 = new clientMoviePanel("九层妖塔","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film7.png");
        clientMoviePanel film8 = new clientMoviePanel("心花怒放","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film8.png");
        clientMoviePanel film9 = new clientMoviePanel("十二生肖","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film1.png");
        clientMoviePanel film10 = new clientMoviePanel("十二生肖","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film1.png");
        clientMoviePanel film11 = new clientMoviePanel("十二生肖","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film1.png");
        clientMoviePanel film12 = new clientMoviePanel("十二生肖","/home/kiosk/IdeaProjects/Practice/src/MovieSystem/picture/film1.png");
        SecondPanelDown.add(film1.panel);
        SecondPanelDown.add(film2.panel);
        SecondPanelDown.add(film3.panel);
        SecondPanelDown.add(film4.panel);
        SecondPanelDown.add(film5.panel);
        SecondPanelDown.add(film6.panel);
        SecondPanelDown.add(film7.panel);
        SecondPanelDown.add(film8.panel);
        SecondPanelDown.add(film9.panel);
        SecondPanelDown.add(film10.panel);
        SecondPanelDown.add(film11.panel);
        SecondPanelDown.add(film12.panel);

        //panel 3  修改密码界面
        /*********************************************************/
        JPanel ThirdPanel = new JPanel(new BorderLayout());
        cardPanel.add(ThirdPanel);

        JPanel ThirdPanelUP = new JPanel(new GridLayout(1,7,5,5));
        ThirdPanel.add(ThirdPanelUP,BorderLayout.NORTH);

        ThirdPanelUP.add(ThirdPanelUserHeadImage);
        ThirdPanelUP.add(ThirdPanelUserName);
        ThirdPanelUserName.setFont(new Font("宋体",Font.BOLD,20));
        ThirdPanelUP.add(ThirdPanelLeftMoney);
        ThirdPanelLeftMoney.setFont(new Font("宋体",Font.BOLD,15));
        ThirdPanelUP.add(ThirdPanelViewFilmButton);
        ThirdPanelViewFilmButton.setBackground(Color.YELLOW);
        ThirdPanelViewFilmButton.setFont(new Font("宋体",Font.BOLD,15));
        ThirdPanelViewFilmButton.addActionListener(new MyButtonActionListener());
        ThirdPanelUP.add(ThirdPanelRechargeButton);
        ThirdPanelRechargeButton.setBackground(Color.ORANGE);
        ThirdPanelRechargeButton.setFont(new Font("宋体",Font.BOLD,15));
        ThirdPanelRechargeButton.addActionListener(new MyButtonActionListener());
        ThirdPanelUP.add(ThirdPanelChangePasswdButton);
        ThirdPanelChangePasswdButton.addActionListener(new MyButtonActionListener());
        ThirdPanelChangePasswdButton.setBackground(Color.PINK);
        ThirdPanelChangePasswdButton.setFont(new Font("宋体",Font.BOLD,15));
        ThirdPanelUP.add(ThirdPanelViewChargedButton);
        ThirdPanelViewChargedButton.setBackground(Color.GREEN);
        ThirdPanelViewChargedButton.setFont(new Font("宋体",Font.BOLD,15));
        ThirdPanelViewChargedButton.addActionListener(new MyButtonActionListener());


        GridBagLayout gb3 = new GridBagLayout();
        GridBagConstraints gbc3 = new GridBagConstraints();
        JPanel ThirdPanelDown = new JPanel(gb3);
        ThirdPanelDown.setBorder(new MatteBorder(20,5,10,30,Color.LIGHT_GRAY));
        ThirdPanelDown.setBackground(Color.PINK);
        ThirdPanel.add(ThirdPanelDown,BorderLayout.CENTER);

        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelOldPasswd,gbc3);


        gbc3.gridx = 1;
        gbc3.gridy = 0;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelOldPasswdField,gbc3);


        gbc3.gridx = 0;
        gbc3.gridy = 1;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(new JLabel(" "),gbc3);


        gbc3.gridx = 0;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelNewPasswd,gbc3);


        gbc3.gridx = 1;
        gbc3.gridy = 2;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelNewPasswdField,gbc3);


        gbc3.gridx = 0;
        gbc3.gridy = 3;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(new JLabel(" "),gbc3);


        gbc3.gridx = 0;
        gbc3.gridy = 4;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelConfirmPasswd,gbc3);


        gbc3.gridx = 1;
        gbc3.gridy = 4;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelConfirmPasswdField,gbc3);

        gbc3.gridx = 0;
        gbc3.gridy = 5;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(new JLabel(" "),gbc3);


        gbc3.gridx = 0;
        gbc3.gridy = 6;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelConfirmPasswdButton,gbc3);
        ThirdPanelConfirmPasswdButton.addActionListener(new MyButtonActionListener());

        gbc3.gridx = 1;
        gbc3.gridy = 6;
        gbc3.gridwidth = 1;
        gbc3.gridheight = 1;
        ThirdPanelDown.add(ThirdPanelCanelChangePasswdButton,gbc3);

        //panel 4  充值界面
        /*********************************************************/
        JPanel FourthPanel = new JPanel(new BorderLayout());
        cardPanel.add(FourthPanel);

        JPanel FourthPanelUP = new JPanel(new GridLayout(1,7,5,5));
        FourthPanel.add(FourthPanelUP,BorderLayout.NORTH);

        FourthPanelUP.add(FourthPanelUserHeadImage);
        FourthPanelUP.add(FourthPanelUserName);
        FourthPanelUserName.setFont(new Font("宋体",Font.BOLD,20));
        FourthPanelUP.add(FourthPanelLeftMoney);
        FourthPanelLeftMoney.setFont(new Font("宋体",Font.BOLD,15));
        FourthPanelUP.add(FourthPanelViewFilmButton);
        FourthPanelViewFilmButton.setBackground(Color.YELLOW);
        FourthPanelViewFilmButton.setFont(new Font("宋体",Font.BOLD,15));
        FourthPanelViewFilmButton.addActionListener(new MyButtonActionListener());
        FourthPanelUP.add(FourthPanelRechargeButton);
        FourthPanelRechargeButton.setBackground(Color.ORANGE);
        FourthPanelRechargeButton.setFont(new Font("宋体",Font.BOLD,15));
        FourthPanelRechargeButton.addActionListener(new MyButtonActionListener());
        FourthPanelUP.add(FourthPanelChangePasswdButton);
        FourthPanelChangePasswdButton.addActionListener(new MyButtonActionListener());
        FourthPanelChangePasswdButton.setBackground(Color.PINK);
        FourthPanelChangePasswdButton.setFont(new Font("宋体",Font.BOLD,15));
        FourthPanelUP.add(FourthPanelViewChargedButton);
        FourthPanelViewChargedButton.setBackground(Color.GREEN);
        FourthPanelViewChargedButton.setFont(new Font("宋体",Font.BOLD,15));
        FourthPanelViewChargedButton.addActionListener(new MyButtonActionListener());

        GridBagLayout gb4 = new GridBagLayout();
        GridBagConstraints gbc4 = new GridBagConstraints();
        JPanel FourthPanelDown = new JPanel(gb4);
        FourthPanelDown.setBorder(new MatteBorder(20,5,10,30,Color.LIGHT_GRAY));
        FourthPanelDown.setBackground(Color.ORANGE);
        FourthPanel.add(FourthPanelDown,BorderLayout.CENTER);

        gbc4.gridx = 0;
        gbc4.gridy = 0;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(FourthPanelChargeMoneyLabel,gbc4);


        gbc4.gridx = 1;
        gbc4.gridy = 0;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(FourthPanelChargeMoneyField,gbc4);


        gbc4.gridx = 0;
        gbc4.gridy = 1;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(new JLabel(" "),gbc4);


        gbc4.gridx = 0;
        gbc4.gridy = 2;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(FourthPanelChargePasswdLabel,gbc4);


        gbc4.gridx = 1;
        gbc4.gridy = 2;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(FourthPanelChargePasswdField,gbc4);


        gbc4.gridx = 0;
        gbc4.gridy = 3;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(new JLabel(" "),gbc4);


        gbc4.gridx = 0;
        gbc4.gridy = 4;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(FourthPanelConfirmChargeMoneyButton,gbc4);
        FourthPanelConfirmChargeMoneyButton.addActionListener(new MyButtonActionListener());


        gbc4.gridx = 1;
        gbc4.gridy = 4;
        gbc4.gridwidth = 1;
        gbc4.gridheight = 1;
        FourthPanelDown.add(FourthPanelCanelChargeMoneyButton,gbc4);






        this.setSize(800,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

    }

    class clientMoviePanel extends JFrame {
        public clientMoviePanel() {}
        public clientMoviePanel(String FilmNamePath,String FilmImagePath) {
            this.FilmName = new JLabel(FilmNamePath,JLabel.CENTER);
            this.FilmImage = new JLabel(new ImageIcon(FilmImagePath));
            BuyTicketButton = new JButton("买票");
            BuyTicketButton.addActionListener(new MovieButtonListener());
            ViewIntroduce = new JButton("介绍");
            ViewIntroduce.addActionListener(new MovieButtonListener());
            Box horizontal = Box.createHorizontalBox();
            horizontal.add(BuyTicketButton);
            horizontal.add(Box.createHorizontalStrut(40));
            horizontal.add(ViewIntroduce);

            panel.add(FilmName,BorderLayout.NORTH);
            panel.add(FilmImage,BorderLayout.CENTER);
            panel.add(horizontal,BorderLayout.SOUTH);

        }
        JPanel panel = new JPanel(new BorderLayout());
        JLabel FilmName;
        JLabel FilmImage;
        JButton BuyTicketButton;
        JButton ViewIntroduce;
        class MovieButtonListener implements ActionListener {

            ArrayList<JButton> TimeButton = new ArrayList<>(); //选择时间按钮的数组
            ArrayList<JButton> SeatButton = new ArrayList<>(); //选择座位按钮的数组
            JFrame FrameChoiceTime = new JFrame();   //选择时间的窗体
            JFrame FrameChoiceSeat = new JFrame();   //选择座位的窗体
            JButton ConfirmChoiceSeat = new JButton("确认购买");

            public void actionPerformed(ActionEvent e) {
                clickButton = (JButton)e.getSource();
                if(clickButton == BuyTicketButton) {

                    JPanel ChoiceTimePanel = new JPanel(new BorderLayout());

                    JPanel ChoiceTime = new JPanel(new GridLayout(20,1,0,2));
                    ChoiceTime.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                    ChoiceTimePanel.add(new JScrollPane(ChoiceTime),BorderLayout.CENTER);
                    JPanel HeadPanel = new JPanel(new GridLayout(1,3,10,0));
                    HeadPanel.add(new JLabel("时间",JLabel.CENTER));
                    HeadPanel.add(new JLabel("演出厅",JLabel.CENTER));
                    HeadPanel.add(new JLabel("票价",JLabel.CENTER));
                    ChoiceTimePanel.add(HeadPanel,BorderLayout.NORTH);
                    for (int i = 0; i < 16 ; i+=2) {
                        JButton ChoiceTimeButton = new JButton(i+8+":00"+" ~ "+(i+10)+":00"
                                +"                          "+i+"号厅"+
                                "                           "+(i+30));
                        TimeButton.add(ChoiceTimeButton);
                        ChoiceTimeButton.addActionListener(new ChoiceTimeListener());
                        ChoiceTime.add(ChoiceTimeButton);
                    }
                    FrameChoiceTime.setTitle("时间选择");
                    FrameChoiceTime.add(ChoiceTimePanel);
                    FrameChoiceTime.setSize(430,230);
                    FrameChoiceTime.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    FrameChoiceTime.setLocationRelativeTo(null);
                    FrameChoiceTime.setVisible(true);
                    FrameChoiceTime.setResizable(false);

                } else if(clickButton == ViewIntroduce) {
                    JTextArea textArea = new JTextArea("这是电影的介绍!");
                    textArea.setColumns(10);
                    textArea.setRows(5);
                    textArea.setLineWrap(true);
                    textArea.setEditable(false);
                    JOptionPane.showMessageDialog(textArea,new JScrollPane(textArea),
                            "电影介绍",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            class ChoiceTimeListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    clickButton = (JButton)e.getSource();
                    for(int i = 0;i < 8;i++) {
                    if(clickButton == TimeButton.get(i)) {
                        FrameChoiceTime.dispose();
                        JPanel ChoiceSeatPanel = new JPanel(new BorderLayout());

                        ChoiceSeatPanel.add(ConfirmChoiceSeat,BorderLayout.SOUTH);
                        ConfirmChoiceSeat.addActionListener(new ChoiceSeatListener());
                        ChoiceSeatPanel.setFont(new Font("宋体",Font.BOLD,15));
                        JPanel SeatPanel = new JPanel(new GridLayout(5,5,2,2));
                        SeatPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
                        ChoiceSeatPanel.add(SeatPanel,BorderLayout.CENTER);

                        for (int j = 0; j < 25 ; j++) {
                            JButton ChoiceSeatButton = new JButton();
                            ChoiceSeatButton.addActionListener(new ChoiceSeatListener());
                            ChoiceSeatButton.setBackground(Color.GREEN);
                            SeatPanel.add(ChoiceSeatButton);
                            SeatButton.add(ChoiceSeatButton);
                        }

                        FrameChoiceSeat.setTitle("座位选择");
                        FrameChoiceSeat.add(ChoiceSeatPanel);
                        FrameChoiceSeat.setSize(430,230);
                        FrameChoiceSeat.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        FrameChoiceSeat.setLocationRelativeTo(null);
                        FrameChoiceSeat.setVisible(true);
                        FrameChoiceSeat.setResizable(false);
                        }
                    }
                }
            }
            class ChoiceSeatListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    clickButton = (JButton)e.getSource();
                    for(int i = 0;i < 25;i++) {
                        if(clickButton == SeatButton.get(i)) {
                            if(SeatButton.get(i).getBackground() == Color.GREEN) {
                                SeatButton.get(i).setBackground(Color.RED);
                            }else {
                                SeatButton.get(i).setBackground(Color.GREEN);
                            }
                            break;
                        }
                    }
                    if(clickButton == ConfirmChoiceSeat) {
                        String str = "成功购买:\n";
                        for (int i = 0; i < 25; i++) {
                            if(SeatButton.get(i).getBackground() == Color.RED) {
                                str += i/5+1;
                                str += " 排";
                                str += i%5+1;
                                str += " 列\n";
                            }
                        }
                        str += "祝您观影愉快!";
                        JOptionPane.showMessageDialog(null,str,"确认信息",
                                JOptionPane.INFORMATION_MESSAGE);
                        FrameChoiceSeat.dispose();
                    }
                }
            }
        }
    }
    class MyButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clickButton = (JButton)e.getSource();
            if(clickButton == FirstPanelButton1
                    || clickButton == SecondPanelViewFilmButton
                    || clickButton == ThirdPanelViewFilmButton
                    || clickButton == FourthPanelViewFilmButton) {
                card.first(cardPanel);
                for (int i = 0; i <1 ; i++) {
                    card.next(cardPanel);
                }
                repaint();
            }else if(clickButton == SecondPanelChangePasswdButton
                    || clickButton == ThirdPanelChangePasswdButton
                    || clickButton == FourthPanelChangePasswdButton) {
                card.first(cardPanel);
                for(int i = 0;i < 2;i++) {
                    card.next(cardPanel);
                }
                repaint();
            } else if(clickButton == SecondPanelRechargeButton
                    ||clickButton == ThirdPanelRechargeButton
                    || clickButton == FourthPanelRechargeButton) {
                card.first(cardPanel);
                for (int i = 0; i < 3; i++) {
                    card.next(cardPanel);
                }
            } else if(clickButton == FourthPanelConfirmChargeMoneyButton) {
                String str = "充值成功\n充值金额: ";
                str += FourthPanelChargeMoneyField.getText();
                JOptionPane.showMessageDialog(null,str,"确认信息",
                        JOptionPane.INFORMATION_MESSAGE);
                FourthPanelChargeMoneyField.setText("");
                FourthPanelChargePasswdField.setText("");
            } else if(clickButton == ThirdPanelConfirmPasswdButton) {
                String str = "密码修改成功\n新密码: ";
                str += ThirdPanelNewPasswdField.getText();
                str += "\n请妥善保管!";
                JOptionPane.showMessageDialog(null,str,"确认信息",
                        JOptionPane.INFORMATION_MESSAGE);
                ThirdPanelOldPasswdField.setText("");
                ThirdPanelNewPasswdField.setText("");
                ThirdPanelConfirmPasswdField.setText("");
                repaint();
            } /*else if(clickButton == rightBut6) {
                card.first(cardPanel);
                for (int i = 0; i < 5; i++) {
                    card.next(cardPanel);
                }
                repaint();
            } else if(clickButton == rightBut7) {
                card.first(cardPanel);
                for (int i = 0; i < 6; i++) {
                    card.next(cardPanel);
                }
            }*/
        }
    }
    public static void main(String[] args) {
        new clientUI().init();
    }

}
