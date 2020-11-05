package com.example.demo.test.IO;

import java.io.*;

public class TestReadStream {
    public static void main(String[] args) {
        //字符文件流
        long start = System.currentTimeMillis();
        Reader reader = null;
        Writer writer = null;
        try {
            //得到输入流
            reader = new FileReader("F://JavaStream/ReadStream/gradle-6.7-all.zip");
            //得到输出流
            writer = new FileWriter("F://JavaStream/ReadStream/test_user.sql", true);
            char[] chars = new char[50];
            int i;
            while ((i = reader.read(chars)) != -1) {
                System.out.println("i = " + i);
                writer.write(chars, 0, i);
                writer.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        long l = (end - start) / 1000;
        System.out.println("字符流消耗时间 = " + l);
    }
}
