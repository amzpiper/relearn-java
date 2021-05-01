package io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipInputStreamDemo {
    public static void main(String[] args) {
        //传入一个FileInputStream作为数据源，然后，循环调用getNextEntry()，直到返回null，表示zip流结束。
        //一个ZipEntry表示一个压缩文件或目录，如果是压缩文件，我们就用read()方法不断读取，直到返回-1

        //JarInputStream是从ZipInputStream派生，它增加的主要功能是直接读取jar文件里面的MANIFEST.MF文件。
        //因为本质上jar包就是zip包，只是额外附加了一些固定的描述文件。

        //读取zip中的文件
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(""))) {
            ZipEntry entry = null;
            while ((entry = zip.getNextEntry()) != null) {
                String name = entry.getName();
                if (!entry.isDirectory()) {
                    System.out.println("不是目录");
                    int n;
                    while ((n = zip.read()) != -1) {
                        System.out.println("读取文件");
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(""))) {
            File[] files = new File(".").listFiles();
            for (File file : files) {
                zip.putNextEntry(new ZipEntry(file.getName()));
                zip.write(getFileDataAsBytes(file));
                zip.close();
            }
            //上面的代码没有考虑文件的目录结构。如果要实现目录层次结构，new ZipEntry(name)传入的name要用相对路径。
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getFileDataAsBytes(File file) {
        return 1;
    }
}
