package io;

import java.io.*;

/**
 * Writer就是带编码转换器的OutputStream，它把char转换为byte并输出
 *
 * @author guoyh
 */
public class WriterDemo {
    public static void main(String[] args) {
        try (Writer writer = new FileWriter("readme2.md")){
            writer.write(65535);
            writer.write("123".toCharArray());
            writer.write("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CharArrayWriter可以在内存中创建一个Writer，它的作用实际上是构造一个缓冲区，
        //可以写入char，最后得到写入的char[]数组，这和ByteArrayOutputStream非常类似
        try (CharArrayWriter writer = new CharArrayWriter()) {
            writer.write("213");
            System.out.println(writer.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //StringWriter在内部维护了一个StringBuffer，并对外提供了Writer接口
        try (StringWriter stringWriter = new StringWriter()) {
            stringWriter.append("123");
            stringWriter.write("123");
            System.out.println(stringWriter.getBuffer().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //OutputStreamWriter就是一个将任意的OutputStream转换为Writer的转换器
        //Writer实际上是基于OutputStream构造的，它接收char，然后在内部自动转换成一个或多个byte，并写入OutputStream
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("readme2.md"))) {
            writer.write("123");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintStream printStream = new PrintStream("readme.md");
            printStream.println("printStream");
            printStream.close();

            PrintWriter writer = new PrintWriter("readme.md");
            writer.println("PrintWriter");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
