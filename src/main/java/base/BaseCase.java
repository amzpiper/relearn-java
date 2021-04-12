package base;

import javax.xml.XMLConstants;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * @author guoyh
 */
public class BaseCase {


    public static void main(String... args) {
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
        if (s1 != null && "hello".equals(s1)) {
            System.out.println("hello");
        }

        //新的switch语法，不但不需要break，还可以直接返回值
        /*String fruit = "apple";
        switch (fruit){
            case "apple" -> System.out.println(1);
            case "paer" -> System.out.println(2);
            case "mango" -> {
                System.out.println(1);
                System.out.println(2);
            }
            default -> System.out.println(3);
        }*/

        int[] intArray = {1, 221, 21, 32, 61, 1, 341, 33};
        Arrays.sort(intArray);
        System.out.println(Arrays.toString(intArray));

        for (String arg : args) {
            if ("-version".equals(arg)) {
                System.out.println("v 1.0");
                break;
            }
        }
        String str = "";
        System.out.println(str instanceof String);

        Properties p = System.getProperties();
        System.out.println(p.getProperty("java.version"));

        Outer outer = new Outer("");
        Outer.Inner inner = outer.new Inner();

        // 必须引入java.xml模块后才能使用其中的类:
        System.out.println(XMLConstants.XML_NS_PREFIX);

        Integer x1 = 127;
        Integer y1 = 127;
        System.out.println("x1 == y1:"+(x1 == y1));

        Integer x2 = 12700123;
        Integer y2 = 12700123;
        System.out.println("x2 == y2:" + (x2 == y2));

        Integer oo = Integer.valueOf(1);
        System.out.println(oo);

        //无符号
        byte xx = -1;
        byte yy = 127;
        System.out.println(Byte.toUnsignedInt(xx));
        System.out.println(Byte.toUnsignedInt(yy));

        //枚举
        System.out.println(Color.RED.name());
        System.out.println(Color.RED.ordinal());
        Color red = Color.RED;
        System.out.println(red.value);
        System.out.println(red.toString());
        switch (red){
            case RED:
                System.out.println("这是红色");break;
            case BLUE:
                System.out.println("这是蓝色");break;
            default:
                throw new RuntimeException("cannot process" + red);
        }

        //StringJoiner
        String[] names = {"Bob", "Alice", "Grace"};
        StringJoiner sj = new StringJoiner(",", "Hello", "!");
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj);
        String name = String.join(",", names);

        // 大数
        BigInteger bi = new BigInteger("10");
        BigInteger bi2 = new BigInteger("2");
        System.out.println(bi.pow(100));
        System.out.println(bi.longValueExact());
        System.out.println(bi.subtract(bi2));
        System.out.println(bi.divide(bi2));

        BigDecimal bd = new BigDecimal("10.1111910010010000000");
        BigDecimal bd2 = new BigDecimal("10.100091001001");
        BigDecimal bd3 = new BigDecimal("10100");
        System.out.println(bd.multiply(bd2));
        System.out.println(bd3.stripTrailingZeros().scale());
        System.out.println(bd.stripTrailingZeros());
        System.out.println(bd2.setScale(4, RoundingMode.HALF_UP));
        System.out.println(bd2.setScale(4, RoundingMode.DOWN));

        // 做除法时,存在无法除尽的情况，这时，就必须指定精度以及如何进行截断
        BigDecimal one = new BigDecimal("12.345");
        BigDecimal two = new BigDecimal("0.12");
        BigDecimal three = new BigDecimal("12.345");
        BigDecimal res = one.divide(two, 10, RoundingMode.HALF_UP);
        System.out.println(res);
        BigDecimal[] dr = one.divideAndRemainder(two);
        for (BigDecimal bigDecimal : dr) {
            System.out.println(bigDecimal);
        }
        //1:false;0:true
        System.out.println(one.compareTo(two));
        System.out.println(one.compareTo(three));

        //常用工具类
        //绝对值
        System.out.println(Math.abs(-100));
        System.out.println(Math.sqrt(2));

        //对数
        System.out.println(Math.exp(2));
        System.out.println(Math.log(4));
        System.out.println(Math.log10(100));

        //三角函数
        System.out.println(Math.sin(90));
        System.out.println(Math.sin(Math.PI / 6));

        Random random = new Random(12345);
        System.out.println(random.nextInt(100));

        SecureRandom sr = new SecureRandom();
        System.out.println(sr.nextInt(100));

        try {
            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }
}
//Java 14开始，引入了新的Record类,
//public record Point(int x,int y){}
class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
class Outer {
    private String name;

    Outer(String name) {
        this.name = name;
    }

    class Inner {
        void hello() {
            System.out.println("Hello, " + Outer.this.name);
        }
    }
}