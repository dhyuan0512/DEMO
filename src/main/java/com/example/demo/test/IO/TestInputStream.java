package com.example.demo.test.IO;

import java.io.*;

public class TestInputStream {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //读取文件
        int b = 0;
        FileInputStream in = null;
        try{
            in = new FileInputStream("F://JavaStream/InputStream/gradle-6.7-all.zip");
        }catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
            System.exit(-1);
        }

        try{
            long num = 0;
            while((b=in.read())!=-1) {
                System.out.print((char)b);
                num++;
            }
            in.close();
            System.out.println();
            System.out.println("共读取了"+num+"个字节");
            long end = System.currentTimeMillis();
            long l = (end - start) / 1000;
            System.out.println("字节流读取消耗多少时间 = " + l);
        }catch (IOException e1) {
            System.out.println("文件读取出错");System.exit(-1);
        }
        //写文件
        long startX = System.currentTimeMillis();
        int b1 = 0;
        FileInputStream in1 = null;
        FileOutputStream out = null;
        try{
            in1 = new FileInputStream("F://JavaStream/InputStream/gradle-6.7-all.zip");
            out = new FileOutputStream("F://JavaStream/InputStream/test_user.sql");
            while((b1=in1.read())!=-1) {
                out.write(b1);
            }
            in1.close();
            out.close();
        }catch (FileNotFoundException e2) {
            System.out.println("找不到指定文件");System.exit(-1);
        }catch(IOException e1) {
            System.out.println("文件复制出错");System.exit(-1);
        }
        long end = System.currentTimeMillis();
        long l = (end - startX) / 1000;
        System.out.println("字节流写文件消耗多少秒 = " + l);
        System.out.println("文件已复制");
    }

}
