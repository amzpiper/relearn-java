package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guoyh
 */
public class regexDemo {
    public static void main(String[] args) {
        System.out.println(isValidMobileNumber("15512702732"));

        String regex = "20\\d\\d";
        System.out.println("2019".matches(regex));
        System.out.println("1997".matches(regex));

        //匹配规则是从左到右按规则匹配
        //正则表达式有特殊字符，那就需要用\转义
        //想匹配非ASCII字符，例如中文，那就用"\\u####"的十六进制表示
        regex = "a\\u548cc";
        System.out.println("a和c".matches(regex));

        //匹配任意字符
        //精确匹配实际上用处不大，因为我们直接用String.equals()就可以做到。大多数情况下，我们想要的匹配规则更多的是模糊匹配。
        //.匹配一个字符且仅限一个字符
        regex = "a.c";
        System.out.println("abc".matches(regex));
        System.out.println("alc".matches(regex));
        System.out.println("a&c".matches(regex));

        //匹配数字
        //则表达式00\d可以匹配：
        //"007"，因为\d可以匹配字符7；
        //"008"，因为\d可以匹配字符8。
        regex = "00\\d";
        System.out.println("008".matches(regex));

        //匹配常用字符
        //\w可以匹配一个字母、数字或下划线，w的意思是word。例如，java\w可以匹配：
        //"javac"，因为\w可以匹配英文字符c
        //"java9"，因为\w可以匹配数字字符9
        //"java_"，因为\w可以匹配下划线_
        regex = "java\\w";
        System.out.println("java_".matches(regex));
        System.out.println("java8".matches(regex));
        System.out.println("javac".matches(regex));

        //匹配空格字符
        //用\s可以匹配一个空格字符，注意空格字符不但包括空格，还包括tab字符（在Java中用\t表示）
        regex = "java\\s8";
        System.out.println("java 8".matches(regex));
        System.out.println("java\t8".matches(regex));
        System.out.println("java\n8".matches(regex));

        //匹配非数字
        //用\d可以匹配一个数字，而\D则匹配一个非数字。例如，00\D可以匹配：
        //"00A"，因为\D可以匹配非数字字符A；
        //"00#"，因为\D可以匹配非数字字符#。
        //\W可以匹配\w不能匹配的字符，
        //\S可以匹配\s不能匹配的字符
        regex = "00\\D";
        System.out.println("00a".matches(regex));
        System.out.println("00*".matches(regex));
        System.out.println("00_".matches(regex));
        regex = "00\\W";
        System.out.println("00a".matches(regex));
        System.out.println("00*".matches(regex));
        System.out.println("00_".matches(regex));
        regex = "00\\D";
        System.out.println("00a".matches(regex));
        System.out.println("00*".matches(regex));
        System.out.println("00_".matches(regex));

        //重复匹配
        //*可以匹配任意个字符，包括0个字符。我们用A\d*可以匹配：
        //A：因为\d*可以匹配0个数字；
        //A0：因为\d*可以匹配1个数字0；
        //A380：因为\d*可以匹配多个数字380。

        //+可以匹配至少一个字符。我们用A\d+可以匹配：
        //A0：因为\d+可以匹配1个数字0；
        //A380：因为\d+可以匹配多个数字380。

        //?可以匹配0个或一个字符。我们用A\d?可以匹配：
        //A：因为\d?可以匹配0个数字；
        //A0：因为\d?可以匹配1个数字0。

        //n个字符怎么办？用修饰符{n}就可以。A\d{3}可以精确匹配：
        //A380：因为\d{3}可以匹配3个数字380。

        //指定匹配n~m个字符怎么办？用修饰符{n,m}就可以。A\d{3,5}可以精确匹配：
        //A380：因为\d{3,5}可以匹配3个数字380；
        //A3800：因为\d{3,5}可以匹配4个数字3800；
        //A38000：因为\d{3,5}可以匹配5个数字38000。
        //如果没有上限，那么修饰符{n,}就可以匹配至少n个字符。

        //复杂匹配规则
        //匹配开头和结尾
        //用^表示开头，$表示结尾。
        //例如，^A\d{3}$，可以匹配"A001"、"A380"。

        //匹配指定范围
        //规定一个7~8位数字的电话号码不能以0开头
        //[...]可以匹配范围内的字符，例如，[123456789]可以匹配1~9,[...]还有一种写法，直接写[1-9]就可以
        //[123456789]\d{6,7}

        //匹配大小写不限的十六进制数，比如1A2b3c，我们可以这样写：[0-9a-fA-F]
        //匹配6位十六进制数，前面讲过的{n}仍然可以继续配合使用：[0-9a-fA-F]{6}

        //或规则匹配
        //用|连接的两个正则规则是或规则，例如，AB|CD表示可以匹配AB或CD
        String re = "java|php";
        System.out.println("java".matches(re));
        System.out.println("php".matches(re));
        System.out.println("go".matches(re));

        //使用括号
        //现在我们想要匹配字符串learn java、learn php和learn go怎么办？
        //一个最简单的规则是learn\sjava|learn\sphp|learn\sgo，但是这个规则太复杂了，
        //可以把公共部分提出来，然后用(...)把子规则括起来表示成learn\\s(java|php|go)
        regex = "learn\\s(([j|J]ava)|([p|P]hp)|([g|G]o))";
        System.out.println("learn java".matches(regex));
        System.out.println("learn php".matches(regex));
        System.out.println("learn go".matches(regex));
        System.out.println("learn Java".matches(regex));
        System.out.println("learn Php".matches(regex));
        System.out.println("learn Go".matches(regex));

        //分组匹配
        //正确的方法是用(...)先把要提取的规则分组，把上述正则表达式变为(\d{3,4})\-(\d{6,8})。
        //现在问题又来了：匹配后，如何按括号提取子串？
        //没办法用String.matches()这样简单的判断方法了，必须引入java.util.regex包，用Pattern对象匹配，
        //匹配后获得一个Matcher对象，如果匹配成功，就可以直接从Matcher.group(index)返回子串
        Pattern pattern = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        Matcher matcher = pattern.matcher("010-1234567");
        if (matcher.matches()) {
            //参数用1表示第一个子串，2表示第二个子串,传入0会得到什么呢？答案是010-12345678
            String group1 = matcher.group(1);
            String group2 = matcher.group(2);
            System.out.println(group1);
            System.out.println(group2);
        } else {
            System.out.println("匹配失败");
        }

        //反复使用String.matches()对同一个正则表达式进行多次匹配效率较低，因为每次都会创建出一样的Pattern对象。
        //完全可以先创建出一个Pattern对象，然后反复使用，就可以实现编译一次，多次匹配
        //必须首先调用matches()判断是否匹配成功，匹配成功后，才能调用group()提取子串
    }

    static boolean isValidMobileNumber(String s) {
        //注意Java字符串用\\表示\
        return s.matches("\\d{11}");
    }

}
