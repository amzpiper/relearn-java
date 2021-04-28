package file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ClassPathDemo {
    public static void main(String[] args) {
        //从classpath读取文件就可以避免不同环境下文件路径不一致的问题：
        //如果我们把default.properties文件放到classpath中，就不用关心它的实际存放路径。
//        try (InputStream inputStream = this.getClass().getResourceAsStream("/setting.properties")) {
            //TODO
            //如果资源文件不存在，它将返回null。因此，我们需要检查返回的InputStream是否为null，
            //如果为null，表示资源文件在classpath中没有找到
//        }

        Properties properties = new Properties();
//        properties.load();

        //资源存储在classpath中可以避免文件路径依赖；
        //Class对象的getResourceAsStream()可以从classpath中读取指定资源；
        //根据classpath读取资源时，需要检查返回的InputStream是否为null。
    }
}
