package io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Reader是Java的IO库提供的另一个输入流接口。和InputStream的区别是:
 * InputStream是一个字节流，即以byte为单位读取，
 * 而Reader是一个字符流，即以char为单位读取
 *
 * @author guoyh
 */
public class ReaderDemo {
    public static void main(String[] args) {
        Reader reader = null;
        try {
            int n;
            reader = new FileReader("readme.md");
            while ((n = reader.read()) != -1) {
                System.out.println(((char) n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read(char[])
        //CharArrayReader可以在内存中模拟一个Reader，它的作用实际上是把一个char[]数组变成一个Reader，
        //这和ByteArrayInputStream非常类似
        //StringReader可以直接把String作为数据源，它和CharArrayReader几乎一样
        try {
            int n;
            char[] buffer = new char[1024];
//            try (Reader reader1 = new CharArrayReader("123".toCharArray());){
            try (Reader reader1 = new StringReader("123");){
                while ((n = reader1.read(buffer)) != -1) {
                    System.out.println(Arrays.toString(buffer));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //除了特殊的CharArrayReader和StringReader，普通的Reader实际上是基于InputStream构造的，
        //因为Reader需要从InputStream中读入字节流（byte），然后，根据编码设置，再转换为char就可以实现字符流。
        //如果我们查看FileReader的源码，它在内部实际上持有一个FileInputStream
        //Reader本质上是一个基于InputStream的byte到char的转换器

        try {
            // 持有InputStream:
            InputStream input = new FileInputStream("readme.md");
            // 变换为Reader:
            Reader reader1 = new InputStreamReader(input, StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
