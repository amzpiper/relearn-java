package io;

import java.io.*;

public class SerializableDemo {
    public static void main(String[] args) {
        //序列化是指把一个Java对象变成二进制内容，本质上就是一个byte[]数组。
        //把byte[]通过网络传输到远程，这样，就相当于把Java对象存储到文件或者通过网络传输出去了。

        //一个Java对象要能序列化，必须实现一个特殊的java.io.Serializable接口
        //实现了标记接口的类仅仅是给自身贴了个“标记”，并没有增加任何方法。
        //transient和static修饰的变量不参加序列化

        //把一个Java对象变为byte[]数组，需要使用ObjectOutputStream,负责把一个Java对象写入一个字节流
        //ObjectOutputStream既可以写入基本类型，如int，boolean，也可以写入String（以UTF-8编码），还可以写入实现了Serializable接口的Object。
        Person person = new Person();
        person.name = "name";
        person.address = "address";
        person.score = 100;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("C:/tmp/person.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Serialized data is saved in ./tmp/person.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ObjectInputStream负责从一个字节流读取Java对象
        //ClassNotFoundException，这种情况常见于一台电脑上的Java程序把一个Java对象，例如，Person对象序列化以后，
        //通过网络传给另一台电脑上的另一个Java程序，但是这台电脑的Java程序并没有定义Person类，所以无法反序列化
        //InvalidClassException，这种情况常见于序列化的Person对象定义了一个int类型的age字段，但是反序列化时，
        //Person类定义的age字段被改成了long类型，所以导致class不兼容。
        //为了避免这种class定义变动导致的不兼容，Java的序列化允许class定义一个特殊的serialVersionUID静态变量，
        //用于标识Java类的序列化“版本”，通常可以由IDE自动生成。如果增加或修改了字段，可以改变serialVersionUID的值，
        //这样就能自动阻止不匹配的class版本：
        Person person1 = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("C:/tmp/person.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            person1 = (Person) objectInputStream.readObject();
            System.out.println(person1.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
class Person implements Serializable {
    public String name;
    public String address;
    public static int age = 12;
    public transient int score;
    private static final long serialVersionUID = 2709425275741743919L;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                '}';
    }
}