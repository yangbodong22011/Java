package IO.Huffman;

import java.io.*;
import java.util.Map;

public class HuffmanGzip {
    public static final int N = 256;                              //read的返回值始终是0-255之间,故设置哈夫曼数组大小为255
    public static final int M = 2*N;                              //
    public static final int Max = 2147483647;                     //定义最大数
    public static HuffmanTree[] ht = new HuffmanTree[M];          //哈夫曼数组
    public static int[] bump = new int[256];                      //读入源文件时用桶排的原理记录权值
    public static int Mmin;                                       //最小
    public static int Min;                                        //次小
    //public static Map<Integer,String> map = new HashMap<>();      //存放编码

    private static  String sourcePath = "";

    public static int a = 1;       //获得编码时每次确定下次位置
    public static int m;           //ht的总个数

    public HuffmanGzip () {
    }
    public HuffmanGzip(String path) throws IOException {
        sourcePath = path;
        String tempPath = sourcePath + ".code";
        String anotherPath = sourcePath + ".ano";
        String toPath = sourcePath + ".gz";
        String htPath = sourcePath + ".ht";
        initHuffmanTree();                //初始化，申请空间
        readFile();                       //将压缩源文件以0-255的数读入
        crtHuffmanTree();                 //编码并且存入hashMap中
        getallCode(tempPath);                    //再次读入文件,将所有的字符根据Map值得到二进制码并写入文件
        reReadHuffman(tempPath,toPath,anotherPath);
        showHuffmanArray();
        reserveHt(htPath);
    }
    public static void reserveHt(String htPath) throws IOException{
        BufferedWriter output = new BufferedWriter(new FileWriter(htPath));
        for (int i = 0; i <ht.length ; i++) {
            String s = "";
            s += ht[i].weight;
            s += " ";
            s += ht[i].name;
            s += " ";
            s += ht[i].parent;
            s += " ";
            s += ht[i].lchild;
            s += " ";
            s += ht[i].rchild;
            s += " ";
            s += ht[i].code;
            s += "\n";
            output.write(s);
        }
        output.close();
    }

    public static void getallCode(String tempPath) throws IOException {    //首次得到二进制文件
        FileInputStream input = new FileInputStream(sourcePath);
        PrintStream output = new PrintStream(new FileOutputStream(tempPath));
        int a;
        while((a = input.read()) != -1) {
            String str = ht[a].code;
            output.print(str);
        }
        input.close();
        output.close();
    }
    public static void reReadHuffman(String tempPath,String toPath,String anotherPath) throws IOException{
        BufferedReader input  = new BufferedReader(new FileReader(tempPath));
        FileOutputStream output = new FileOutputStream(toPath);
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
        System.out.println("   "+" 权值  "+ " 姓名  "+" 双亲  "+" 左孩子  "+" 右孩子  "+ " 编码");
        for (int i = 1; i <= M-1; i++) {
            if(ht[i].weight != 0) {
                System.out.printf(" %d : %-4d %-4d %-8d %-8d %-8d %s\n",i,ht[i].weight,ht[i].name,
                        ht[i].parent,ht[i].lchild,ht[i].rchild,ht[i].code);
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
            ht[i].code = str;
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
