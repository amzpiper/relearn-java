package base;

import java.util.Properties;
import java.util.Scanner;

/**
 * @author guoyh
 */
public class BaseCase {
    public static void main(String[] args) {
        //浮动类型
        float f = 3.14e38f;
        System.out.println(f);

        // 浮点数运算误差
        double x = 1.0 / 10.0;
        double y = (1.0 - 0.9) / 10.0;
        System.out.println(x);
        System.out.println(y);

        //类型提升
        int n = 5;
        double d = 1.2 + 24.0 / n;
        System.out.println(d);
        double dd = 1.2 + 24.0 / 5;
        System.out.println(dd);

        //溢出
        double d1 = 0.0 / 0;
        double d2 = 1.0 / 0;
        double d3 = -1.0 / 0;
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);

        //强制转换
        int n1 = ((int) 12.3);
        int n2 = ((int) -12.7);
        System.out.println(n1);
        System.out.println(n2);
        //四舍五入
        int n3 = ((int) (12.6 + 0.5));
        System.out.println(n3);

        //短路运算
        boolean result = (5 < 3) && (5 / 0 > 0);
        System.out.println(result);

        //Unicode编码
        char a = 'A';
        int A = '中';
        char c = '\u4e2d';
        System.out.println(A);
        System.out.println(c);

        //从Java 13开始，字符串可以用"""..."""表示多行字符串
        //多行字符串前面共同的空格会被去掉
        /*
        String s = """
                123
                """;
        */
        //初始化为默认值，整型都是0，浮点型是0.0，布尔型是false
        int[] ns = new int[15];

        //格式化输出
        System.out.printf("%.2f\n", .222f);
        //预占位
        String s = "insert into %s";
        String sql = String.format(s, "table");
        System.out.println(sql);

        //输入
        Scanner scanner = new Scanner(System.in);

        //jvm
        Properties version = System.getProperties();
        System.out.println(version.getProperty("java.version"));

        //利用短路，s1=null会报异常
        String s1 = null;
        if (s1 != null && s1.equals("hello")) {
            System.out.println("hello");
        }

        //新的switch语法，不但不需要break，还可以直接返回值
        /*
        String fruit = "apple";
        switch (fruit){
            case "apple" -> System.out.println(1);
            case "paer" -> System.out.println(2);
            case "mango" -> {
                System.out.println(1);
                System.out.println(2);
            }
            default -> System.out.println(3);
        }*/


    }
}
