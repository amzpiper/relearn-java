package file;

import java.io.*;

public class OutputStreamDemo {
    public static void main(String[] args) throws IOException {
        //public abstract void write(int b) throws IOException;
        //写入一个字节到输出流。要注意的是，虽然传入的是int参数，但只会写入一个字节，即只写入int最低8位表示字节的部分（相当于b & 0xff）

        //还提供了一个flush()方法，它的目的是将缓冲区的内容真正输出到目的地。
        //出于效率的考虑，操作系统并不是输出一个字节就立刻写入到文件或者发送到网络，
        //而是把输出的字节先放到内存的一个缓冲区里（本质上就是一个byte[]数组），等到缓冲区写满了，
        //再一次性写入文件或者网络。
        //缓冲区写满了OutputStream会自动调用它，并且，在调用close()方法关闭OutputStream之前，也会自动调用flush()方法。

        //在某些情况下，我们必须手动调用flush()方法。举个栗子：
        //小明正在开发一款在线聊天软件，当用户输入一句话后，就通过OutputStream的write()方法写入网络流。
        //小明测试的时候发现，发送方输入后，接收方根本收不到任何信息，怎么肥四
        //原因就在于写入网络流是先写入内存缓冲区，等缓冲区满了才会一次性发送到网络。
        //如果缓冲区大小是4K，则发送方要敲几千个字符后，操作系统才会把缓冲区的内容发送出去，
        //这个时候，接收方会一次性收到大量消息。
        //解决办法就是每输入一句话后，立刻调用flush()，不管当前缓冲区是否已满，
        //强迫操作系统把缓冲区的内容立刻发送出去
        OutputStream outputStream = new FileOutputStream("out/out.txt");
        outputStream.write(72);
        outputStream.write(101); // e
        outputStream.write(108); // l
        outputStream.write(108); // l
        outputStream.write(111); // o
        outputStream.write("hello".getBytes("utf-8"));
        outputStream.close();

        try(OutputStream outputStream1 = new FileOutputStream("")) {
            outputStream1.write("".getBytes("utf-8"));
        }
        //此外，ByteArrayOutputStream可以在内存中模拟一个OutputStream：

        try (InputStream input = new FileInputStream("");OutputStream output = new FileOutputStream("")){
            //input.transferTo(output); // transferTo的作用是?
        }
    }
}
