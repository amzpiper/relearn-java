package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author guoyh
 */
public class JunitDemo {
    public static void main(String[] args) {
        //所谓测试驱动开发，是指先编写接口，紧接着编写测试。编写完测试后，我们才开始真正编写实现代码。
        //在编写实现代码的过程中，一边写，一边测，什么时候测试全部通过了，那就表示编写的实现完成了：
        if (Factorial.fact(10) == 3628800) {
            System.out.println("pass");
        } else {
            System.out.println("fail");
        }

        //使用JUnit编写单元测试的好处在于，我们可以非常简单地组织测试代码，并随时运行它们
        //JUnit就会给出成功的测试和失败的测试，还可以生成测试报告，不仅包含测试的成功率，
        //还可以统计测试的代码覆盖率，即被测试的代码本身有多少经过了测试。对于高质量的代码来说，测试覆盖率应该在80%以上
        //assertEquals(): 期待与期待结果相同
        //assertTrue(): 期待结果为true
        //assertFalse(): 期待结果为false
        //assertNotNull(): 期待结果为非null
        //assertArrayEquals(): 期待结果为数组并与期望数组每个元素的值均相等

        //单元测试的好处
        //单元测试可以确保单个方法按照正确预期运行，如果修改了某个方法的代码，只需确保其对应的单元测试通过，即可认为改动正确。
        //此外，测试代码本身就可以作为示例代码，用来演示如何调用该方法。
        //使用JUnit进行单元测试，我们可以使用断言（Assertion）来测试期望结果，可以方便地组织和运行测试，并方便地查看测试结果。
        //此外，JUnit既可以直接在IDE中运行，也可以方便地集成到Maven这些自动化工具中运行。
        //在编写单元测试的时候，我们要遵循一定的规范：
        //一是单元测试代码本身必须非常简单，能一下看明白，决不能再为测试代码编写测试；
        //二是每个单元测试应当互相独立，不依赖运行的顺序；
        //三是测试时不但要覆盖常用测试用例，还要特别注意测试边界条件，例如输入为0，null，空字符串""等情况。
    }

    @Test
    public void testFact() {
        assertEquals(1, Factorial.fact(1));
        assertEquals(2, Factorial.fact(2));
        assertEquals(6, Factorial.fact(3));
        assertEquals(3628800, Factorial.fact(10));
        //由于浮点数无法精确地进行比较，因此，我们需要调用assertEquals(double expected, double actual, double delta)这个重载方法
        //指定一个误差值：
        assertEquals(0.1, Math.abs(1 - 9 / 10.0), 0.0000001);
    }

    //JUnit提供了编写测试前准备、测试后清理的固定代码，我们称之为Fixture。
    //junit4	    junit5	    特点
    //@BeforeClass	@BeforeAll	在当前类的所有测试方法之前执行。注解在【静态方法】上。
    //@AfterClass	@AfterAll	在当前类中的所有测试方法之后执行。注解在【静态方法】上。
    //@Before	    @BeforeEach	在每个测试方法之前执行。注解在【非静态方法】上。
    //@After	    @AfterEach	在每个测试方法之后执行。注解在【非静态方法】上。

    Calculator calculator;

    @Before
    public void setUpTest() {
        this.calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        assertEquals(100,this.calculator.add(100));
    }

    @Test
    public void testSub() {
        assertEquals(0,this.calculator.sub(100));
    }

    @After
    public void tearDown() {
        this.calculator = null;
    }
}
class Factorial {
    public static long fact(long n) {
        long r = 1;
        for (long i = 1; i <= n; i++) {
            r = r * i;
        }
        return r;
    }
}
class Calculator {
    private long n = 0;

    public long add(long x) {
        n = n + x;
        return n;
    }

    public long sub(long x) {
        n = n - x;
        return n;
    }
}