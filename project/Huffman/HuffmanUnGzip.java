package IO.Huffman;

import java.io.*;

public class HuffmanUnGzip {
    public static final int N = 256;                              //read的返回值始终是0-255之间,故设置哈夫曼数组大小为255
    public static final int M = 2 * N;                              //
    public static HuffmanTree[] ht = new HuffmanTree[M];          //哈夫曼数组
    public static int[] bump = new int[256];                      //读入源文件时用桶排的原理记录权值

    private static String sourcePath = "";

    public static int a = 1;       //获得编码时每次确定下次位置
    public static int m;           //ht的总个数

    public HuffmanUnGzip() {
    }

    public HuffmanUnGzip(String path) throws IOException, ClassNotFoundException {
        String[] str = path.split("\\.");
        sourcePath = str[0];          //文件
        //sourcePath = str[0]+ "." + str[1];  //图片
        String anotherPath = sourcePath + ".ano";
        String toPath = sourcePath + ".gz";
        String temp2Path = sourcePath + ".temp2";
        String to2Path = sourcePath + ".decode";
        String htPath = sourcePath + ".ht";
        initHuffmanTree();                //初始化，申请空间
        getHt(htPath);
        showHuffmanArray();
        getTemp2(toPath, anotherPath, temp2Path);   //解压缩时，将压缩文件转换成二进制码
        unGzip(temp2Path, to2Path);
    }

    public static void getHt(String htPath) throws IOException, ClassNotFoundException {
        BufferedReader input = new BufferedReader(new FileReader(htPath));
        String str = "";
        int i = 1;
        while ((str = input.readLine()) != null) {
            String[] str1 = str.split(" ");
            if (Integer.parseInt(str1[0]) != 0) {
                ht[i].weight = Integer.parseInt(str1[0]);
                ht[i].name = Integer.parseInt(str1[1]);
                ht[i].parent = Integer.parseInt(str1[2]);
                ht[i].lchild = Integer.parseInt(str1[3]);
                ht[i].rchild = Integer.parseInt(str1[4]);
                ht[i].code = str1[5];
                i++;
            }
        }
        m = i-1;
        input.close();
    }

    public static void unGzip(String temp2Path, String to2Path) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(temp2Path));
        FileOutputStream output = new FileOutputStream(to2Path);
        int a;
        int flag = 0;
        int k = 0;
        while ((a = input.read()) > 0) {  //依次读取，依次解释
            if (flag == 0) {
                k = m;
                if (a == '0') {
                    k = ht[k].lchild;
                }
                if (a == '1') {
                    k = ht[k].rchild;
                }
                flag = 1;
                continue;
            }
            if (flag == 1) {
                if (a == '0') {
                    k = ht[k].lchild;
                    if (ht[k].lchild == 0) {
                        output.write(ht[k].name);
                        flag = 0;
                    }
                }
                if (a == '1') {
                    k = ht[k].rchild;
                    if (ht[k].lchild == 0) {
                        output.write(ht[k].name);
                        flag = 0;
                    }
                }
            }
        }
        output.close();
    }

    public static void getTemp2(String toPath, String anotherPath, String temp2Path) throws IOException {
        FileInputStream input = new FileInputStream(toPath);
        BufferedReader input1 = new BufferedReader(new FileReader(anotherPath));
        BufferedWriter output = new BufferedWriter(new FileWriter(temp2Path));
        int a;
        while ((a = input.read()) != -1) {
            String str = changeToBinary(a);
            output.write(str);
        }
        String str;
        while ((str = input1.readLine()) != null) {
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
        if (str1.length() == 8) return str1;
        for (int i = 7, j = str1.length() - 1; i >= 0; i--, j--) {
            if (j >= 0) {
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
    public static void showBump() {
        int count = 0;
        for (int i = 0; i < bump.length; i++) {
            if (bump[i] != 0) {
                System.out.println(i + ":" + bump[i]);
                count++;
            }
        }
        System.out.println("count : " + count);
    }

    public static void showHuffmanArray() {
        System.out.println("   " + " 权值  " + " 姓名  " + " 双亲  " + " 左孩子  " + " 右孩子  " + " 编码");
        for (int i = 1; i <= M - 1; i++) {
            if (ht[i].weight != 0) {
                System.out.printf(" %d : %-4d %-4d %-8d %-8d %-8d %s\n", i, ht[i].weight, ht[i].name,
                        ht[i].parent, ht[i].lchild, ht[i].rchild, ht[i].code);
            }
        }
    }

    public static void initHuffmanTree() {
        for (int i = 0; i < M; i++) {
            ht[i] = new HuffmanTree();
        }
    }
}
