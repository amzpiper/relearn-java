package file;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        //提供了File对象来操作文件和目录
        File f = new File("C:\\Windows\\notepad.exe");
        System.out.println(f);
    }
}
