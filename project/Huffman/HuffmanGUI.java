package IO.Huffman;

import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;


public class HuffmanGUI extends JFrame{
    private static JButton GzipBut = new JButton("压缩");
    private static JButton UnGzipBut = new JButton("解压");
    private static JButton clickBut;
    private String path = "";
    private JFileChooser fileChooser;
    public HuffmanGUI() {
        JPanel panel = new JPanel(new BorderLayout());
        fileChooser = new JFileChooser();
        panel.add(fileChooser,BorderLayout.CENTER);
        JPanel Downpanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,0));
        Downpanel.add(GzipBut);
        Downpanel.add(UnGzipBut);
        add(panel,BorderLayout.CENTER);
        add(Downpanel,BorderLayout.SOUTH);
        GzipBut.addActionListener(new MyButListener());
        UnGzipBut.addActionListener(new MyButListener());
    }

    public static void main(String[] args) {
        HuffmanGUI frame = new HuffmanGUI();
        frame.setTitle("         哈夫曼编/译码器");
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[8192];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public static void Message(String path) throws IOException{
        String toPath = path + ".gz";
        FileInputStream input1 = new FileInputStream(new File(path));
        FileInputStream input2 = new FileInputStream(new File(toPath));
        double oringal = input1.available()/1000;
        double last = input2.available()/1000;
        int rate = (int)(last/oringal*100);
        String str = "恭喜你,压缩成功" + "\n" +
                     "压缩前大小: " + oringal + " KB" + "\n" +
                     "压缩后大小: " + last + " KB" + "\n" +
                     "压缩比例: " + rate + "%" + "\n";
        JOptionPane.showMessageDialog(null,str,"系统提示",JOptionPane.INFORMATION_MESSAGE);
    }
    class MyButListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clickBut = (JButton)e.getSource();
            if(clickBut == GzipBut) {
                try {
                    path = fileChooser.getSelectedFile().getAbsolutePath();
                    HuffmanGzip huffmanGzip = new HuffmanGzip(path);
                    fileChooser.rescanCurrentDirectory();
                    Message(path);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }else if(clickBut == UnGzipBut) {
                try {
                    path = fileChooser.getSelectedFile().getAbsolutePath();
                    HuffmanUnGzip huffman = new HuffmanUnGzip(path);
                    fileChooser.rescanCurrentDirectory();
                    String[] str = path.split("\\.");
                    String sourcePath = str[0];      //文件
                    //String sourcePath = str[0] + "." + str[1];  //图片
                    File file1 = new File(sourcePath);
                    File file2 = new File(sourcePath + ".decode");
                    if(getFileMD5(file1).equals(getFileMD5(file2))) {
                        JOptionPane.showMessageDialog(null,"恭喜你,解压成功,原文件与.decode文件一致",null,JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"恩...原文件与.decode不一致,解压失败！",null,JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}

