package exception;

public class AssertDemo {
    public static void main(String[] args) {

        //断言不能用于可恢复的程序错误，只应该用于开发和测试阶段。
        //JVM默认关闭断言指令
        //-enableassertions（可简写为-ea）参数启用断言
        //实际开发中，很少使用断言。更好的方法是编写单元测试，后续我们会讲解JUnit的使用。
        double x = Math.abs(-123.45);
        assert x >= 0 : "x must >= 0";
        System.out.println(x);
    }
}
