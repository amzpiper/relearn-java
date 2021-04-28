package collection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) {
        //配置文件的特点是，它的Key-Value一般都是String-String类型的，因此我们完全可以用Map<String, String>来表示它
        //由于历史遗留原因，Properties内部本质上是一个Hashtable
        //Java默认配置文件以.properties为扩展名，每行以key=value表示，以#课开头的是注释
        String propertiesFile = "src/main/resources/setting.properties";
        Properties properties = new Properties();
        try {
            //后读取的key-value会覆盖已读取的key-value
            //开发环境下读取位置
            properties.load(new FileReader(propertiesFile));
            //jar文件下读取classpath下文件
            properties.load(PropertiesDemo.class.getResourceAsStream("/setting.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filepath = properties.getProperty("last_open_file");
        String interval  = properties.getProperty("auto_save_interval","120");
        System.out.println(filepath + "," + interval);
        //用Properties读取配置文件，一共有三步：
        //创建Properties实例；
        //调用load()读取文件；
        //调用getProperty()获取配置。
        //如果key不存在，将返回null,可以提供一个默认值

    }
}
