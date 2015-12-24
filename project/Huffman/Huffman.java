package IO.Huffman;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Huffman {
    public static final int N = 256;                              //read的返回值始终是0-255之间,故设置哈夫曼数组大小为255
    public static final int M = 2*N;                              //
    public static final int Max = 2147483647;                     //定义最大数
    public static HuffmanTree[] ht = new HuffmanTree[M];          //哈夫曼数组
    public static int[] bump = new int[256];                      //读入源文件时用桶排的原理记录权值
    public static int Mmin;                                       //最小
    public static int Min;                                        //次小
    public static Map<Integer,String> map = new HashMap<>();      //存放编码
    //源文件
    private static final String sourcePath =  "/home/student/IdeaProjects/JAVA/src/IO/Huffman/data/passwd";
    //private static final String sourcePath = "/home/student/IdeaProjects/JAVA/src/IO/Huffman/data/1.jpg";
    //压缩过程中得到的二进制文件
    private static final String tempPath = sourcePath + ".temp";      //未经处理的01文件
    //8位一次处理,到最后余下来的
    private static final String anotherPath = sourcePath + ".ano";    //如果
    //压缩文件
    private static final String toPath = sourcePath + ".gz";
    //解压缩过程中的二进制文件,此文件和".temp"文件一样
    private static final String temp2Path = sourcePath + ".temp2";
    //最终的文件，此文件和源文件一样
    private static final String to2Path = sourcePath + ".last";
    //private static final String to2Path =  "/home/student/IdeaProjects/JAVA/src/IO/Huffman/data/2";
    //private static final String to2Path = "/home/student/IdeaProjects/JAVA/src/IO/Huffman/data/2.jpg";

    public static int a = 1;       //获得编码时每次确定下次位置
    public static int m;

    public static void main(String[] args) throws IOException {
        initHuffmanTree();               //初始化，申请空间
        readFile();                      //将压缩源文件以0-255的数读入
        crtHuffmanTree();                //编码并且存入hashMap中
        getallCode();                    //再次读入文件,将所有的字符根据Map值得到二进制码并写入文件
        reReadHuffman();                 //将二进制码文件读出来,然后8位存为一个字符,得到压缩文件
        getTemp2();                      //解压缩时，将压缩文件转换成二进制码
        unGzip();                        //根据二进制码,和哈夫曼树解压缩
    }
    public static void unGzip() throws IOException {
        BufferedReader input  = new BufferedReader(new FileReader(temp2Path));
        FileOutputStream output = new FileOutputStream(to2Path);
        int a;
        int flag = 0;
        int k = 0;
        while((a = input.read()) > 0) {  //依次读取，依次解释
            if(flag == 0) {
                k = m;
                if(a == '0') {
                    k = ht[k].lchild;
                }
                if(a == '1') {
                    k = ht[k].rchild;
                }
                flag = 1;
                continue;
            }
            if(flag == 1) {
                if(a == '0') {
                    k = ht[k].lchild;
                    if(ht[k].lchild == 0) {
                        output.write(ht[k].name);
                        flag = 0;
                    }
                }
                if(a == '1') {
                    k = ht[k].rchild;
                    if(ht[k].lchild == 0) {
                        output.write(ht[k].name);
                        flag = 0;
                    }
                }
            }
        }
        output.close();
    }
    public static void getTemp2() throws IOException{
        BufferedReader input  = new BufferedReader(new FileReader(toPath));
        BufferedReader input1 = new BufferedReader(new FileReader(anotherPath));
        BufferedWriter output = new BufferedWriter(new FileWriter(temp2Path));
        char[] buf = new char[1];
        int hasread;
        while((hasread = input.read(buf)) > 0) {
            int a = (int)buf[0];
            String str = changeToBinary(a);
            output.write(str);
        }
        String str;
        while((str = input1.readLine()) != null) {
            output.append(str);
        }
        input.close();
        input1.close();
        output.close();
    }
    public static String changeToBinary(int a) {      //自己的将整数转换成2进制的方法，
        char[] str = new char[8];                     //原因是我的格式是8位
        String str1 = Integer.toBinaryString(a);
        char[] str2 = str1.toCharArray();
        if(str1.length() == 8) return str1;
        for (int i = 7,j = str1.length()-1; i >= 0 ; i--,j--) {
            if(j >= 0) {
                str[i] = str2[j];
            } else {
                str[i] = '0';
            }
        }
        String ret = "";
        for (int i = 0; i <= 7; i++) {
            ret += str[i];
        }
        return ret;
    }


    public static void getallCode() throws IOException {    //首次得到二进制文件
        FileInputStream input = new FileInputStream(sourcePath);
        PrintStream output = new PrintStream(new FileOutputStream(tempPath));
        int a;
        while((a = input.read()) != -1) {
            String str = map.get(a);
            output.print(str);
        }
        input.close();
        output.close();
    }
    public static void reReadHuffman() throws IOException{
        BufferedReader input  = new BufferedReader(new FileReader(tempPath));
        BufferedWriter output = new BufferedWriter(new FileWriter(toPath));
        BufferedWriter output1 = new BufferedWriter(new FileWriter(anotherPath));
        char[] buf = new char[8];
        int hasread;
        while((hasread = input.read(buf)) == 8) {
            //System.out.println(buf);
            char a;
            int temp = Integer.valueOf(String.valueOf(buf),2);
            a = (char)temp;
            //System.out.println(a);
            output.write(a);
        }
        if(hasread != -1) {
            char[] str = new char[hasread];
            for (int i = 0; i < hasread ; i++) { //处理最后剩余的不足8位的部分
                str[i] = buf[i];
            }
            String str1 = String.valueOf(str);
            output1.write(str1);
            output1.close();
        }
        input.close();
        output.close();
    }
    public static void showBump() {
        int count = 0;
        for (int i = 0; i <bump.length ; i++) {
            if(bump[i] != 0) {
                System.out.println(i + ":" + bump[i]);
                count++;
            }
        }
        System.out.println("count : "+count);
    }
    public static void showHuffmanArray() {
        System.out.println("   "+"权值  "+ "姓名  "+"双亲  "+"左孩子  "+"右孩子  ");
        for (int i = 1; i <= M-1; i++) {
            if(ht[i].weight != 0) {
                System.out.printf(" %d : %-4d %-4d %-4d %-4d %-4d\n",i,ht[i].weight,ht[i].name,
                        ht[i].parent,ht[i].lchild,ht[i].rchild);
            }
        }
    }
    public static void initHuffmanTree() {
        for (int i = 0; i < M; i++) {
            ht[i] = new HuffmanTree();
        }
    }
    public static void readFile() throws IOException{
        FileInputStream input = new FileInputStream(sourcePath);
        int a;
        while((a = input.read()) != -1) {
            bump[a]++;
        }
        input.close();
    }

    public static void crtHuffmanTree() {
        int k = 1;
        for (int i = 1; i <= N-1; i++) {
            if(bump[i] != 0) {
                ht[k].name = i;
                ht[k].weight = bump[i];
                ht[k].parent = 0;
                ht[k].lchild = 0;
                ht[k].rchild = 0;
                k++;
            }
        }
        m = 2*(k-1)-1;
       // System.out.println("m : "+m);
        for (int i = k; i <= m ; i++) {
            ht[i].weight = 0;
            ht[i].parent = 0;
            ht[i].lchild = 0;
            ht[i].rchild = 0;
        }
        for (int i = k; i <= m ; i++) {
            select(i-1);
            ht[i].weight = ht[Min].weight + ht[Mmin].weight;
            ht[i].lchild = Mmin;
            ht[i].rchild = Min;
            ht[Mmin].parent = i;
            ht[Min].parent = i;
        }
        //根据构建的哈夫曼树获得编码
        for (int i = 0; i < N; i++) {
            if(bump[i] != 0) {
                getcode(i,k);
            }
        }
    }
    public static void getcode(int i,int k) {     //根据构建的哈夫曼数获取编码
        if(a <= k) {
            int temp = a+1;   //首先保存a的值
            String str = "";
            int j;
            while(ht[a].parent != 0) {
                j = a;
                a = ht[a].parent;
                if(ht[a].lchild == j) {
                    str += '0';
                }
                if(ht[a].rchild == j) {
                    str += '1';
                }
            }
            a = temp;
            char[] change1 = str.toCharArray();
            char[] change2 = new char[change1.length];
            for (int l = 0,m = change1.length-1; l < change1.length; l++,m--) {
                change2[l] = change1[m];
            }
            str = String.valueOf(change2);
            map.put(i,str);
       }
    }
    public static void select(int n) {
        int s3,s4;
        s3 = s4 = Max;
        for (int i = 1; i <= n ; i++) {
            if(ht[i].parent == 0) {
                if(ht[i].weight < s3) {
                    s4 = s3;
                    Min = Mmin;
                    s3 = ht[i].weight;
                    Mmin = i;
                    continue;
                }
                if((ht[i].weight >= s3) && (ht[i].weight < s4)) {  //防止最后一个比最小大，比第二小小
                    s4 = ht[i].weight;
                    Min = i;
                }
            }
        }
    }
}












