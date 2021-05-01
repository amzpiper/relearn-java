package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Java 7开始，提供了Files和Paths这两个工具类
 * 是java.nio包里面的类
 * @author guoyh
 */
public class FilesDemo {
    public static void main(String[] args) {
        try {
            //要把一个文件的全部内容读取为一个byte[]
            byte[] bytes = Files.readAllBytes(Paths.get("readme.md"));
            for (byte aByte : bytes) {
                System.out.print(((char) aByte));
            }
            bytes[bytes.length-1] = 123;
            Files.write(Paths.get("readme.md"),bytes);

            List<String> strings = new ArrayList<String>();
            strings.add("1");
            strings.add("2");
            Files.write(Paths.get("readme.md"), strings);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
