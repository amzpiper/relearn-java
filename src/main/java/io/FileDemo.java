package io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        //提供了File对象来操作文件和目录
        //可以传入绝对路径，也可以传入相对路径
        File f = new File("C:/Windows/notepad.exe");
        System.out.println(f);
        // 有3种形式表示的路径，一种是getPath()，返回构造方法传入的路径，
        // 一种是getAbsolutePath()，返回绝对路径，
        // 一种是getCanonicalPath，它和绝对路径类似，但是返回的是规范路径

        //File是文件时
        //创建&删除文件
        try {
            File file = new File("D:/file.txt");
            if (file.createNewFile()) {
                System.out.println("新增文件成功");
                if (file.delete()) {
                    System.out.println("删除文件成功");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //临时文件
        File tempFile = File.createTempFile("tmp-", ".txt");
        tempFile.deleteOnExit();//jvm退出时自动删除
        System.out.println(tempFile.isFile());
        System.out.println(tempFile.getAbsolutePath());

        //遍历文件和目录
        //使用list()和listFiles()列出目录下的文件和子目录名
        //listFiles()提供了一系列重载方法，可以过滤不想要的文件和目录
        File dir = new File("C:/Windows");
        File[] files = dir.listFiles();//列出所有文件和子目录
        for (File file : files) {
            System.out.println(file.getName());
        }
        //过滤出所有.exe文件
        File[] exeFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".exe");
            }
        });

        for (File exeFile : exeFiles) {
            System.out.println(exeFile.getName());
        }
        //创建和删除目录
        File dirFile = new File("C:/test/test1");
        if (!(dirFile.isDirectory())) {
            if (dirFile.mkdirs()) {
                System.out.println("创建目录成功");
                if (dirFile.delete()) {
                    System.out.println("删除目录成功");
                }
            }
        }

        //Path
        //Path对象，它位于java.nio.file包。Path对象和File对象类似，但操作更加简单
        Path path1 = Paths.get(".", "project", "study");// 构造一个Path对象
        System.out.println(path1);
        Path path2 = path1.toAbsolutePath();// 转换为绝对路径
        System.out.println(path2);
        Path path3 = path2.normalize();// 转换为规范路径
        System.out.println(path3);
        File ff = path3.toFile();//转换为File对象
        System.out.println(ff);
        for (Path path : Paths.get(".").toAbsolutePath()) {
            System.out.println(path);
        }
    }
}
