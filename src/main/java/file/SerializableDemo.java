package file;

public class SerializableDemo {
    public static void main(String[] args) {
        //序列化是指把一个Java对象变成二进制内容，本质上就是一个byte[]数组。
        //把byte[]通过网络传输到远程，这样，就相当于把Java对象存储到文件或者通过网络传输出去了。

        //一个Java对象要能序列化，必须实现一个特殊的java.io.Serializable接口
        //实现了标记接口的类仅仅是给自身贴了个“标记”，并没有增加任何方法。

        //把一个Java对象变为byte[]数组，需要使用ObjectOutputStream

    }
}
