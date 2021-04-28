package file;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//Java标准库提供的最基本的输入流
//java.io包提供了所有同步IO的功能
//InputStream并不是一个接口，而是一个抽象类，它是所有输入流的超类。这个抽象类定义的一个最重要的方法就是int read()
public class InputStreamDemo {
    public static void main(String[] args) throws IOException {
        //new InputStream().read();
        //方法会读取输入流的下一个字节，并返回字节表示的int值(0~255),已读到末尾，返回-1表示不能继续读取

        //FileInputStream就是从文件流中读取数据
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("./README.md");
            for (; ; ) {
                int n = inputStream.read();
                if (n == -1) {
                    break;
                }
                System.out.println(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        //计算机中，类似文件、网络端口这些资源，都是由操作系统统一管理的。
        //应用程序在运行的过程中，如果打开了一个文件进行读写，完成后要及时地关闭，
        //以便让操作系统把资源释放掉，否则，应用程序占用的资源会越来越多，不但白白占用内存，还会影响其他应用程序的运行。
        //关闭流就会释放对应的底层资源

        //利用Java 7引入的新的try(resource)的语法
        try (InputStream input = new FileInputStream("readme.md")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        }
        //编译器在此自动为我们写入finally并调用close()
        //编译器只看try(resource = ...)中的对象是否实现了java.lang.AutoCloseable接口，
        //如果实现了，就自动加上finally语句并调用close()方法

        //缓冲:定义一个byte[]数组作为缓冲区，read()方法会尽可能多地读取字节到缓冲区， 但不会超过缓冲区的大小
        try(InputStream input = new FileInputStream("readme.md")) {
            byte[] buffer = new byte[1024];
            int n;
            while ((n = input.read(buffer)) != -1) {//读取到缓冲区
                System.out.println("read " + n + "bytes.");
            }
        }

        //阻塞
        //必须等read()方法返回后才能继续。因为读取IO流相比执行普通代码，速度会慢很多
        byte[] data = { 72, 101, 108, 108, 111, 33 };
        try (InputStream input = new ByteArrayInputStream(data)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char)n);
            }
        }

        //把一个byte[]数组在内存中变成一个InputStream
        try (InputStream inputStream1 = new ByteArrayInputStream(data)) {
            String s = readAsString(inputStream1);
            System.out.println("s:"+s);
        }
        //接受InputStream抽象类型，而不是具体的FileInputStream类型，从而使得代码可以处理InputStream的任意实现类
    }

    private static String readAsString(InputStream inputStream1) throws IOException {
        int n;
        StringBuilder sb = new StringBuilder();
        while ((n = inputStream1.read()) != -1) {
            sb.append(((char) n));
        }
        return sb.toString();
    }
}
