package collection;

//栈-后进先出
//把元素压栈：push(E)；
//把栈顶的元素“弹出”：pop()；
//取栈顶元素但不弹出：peek()。

//用Deque可以实现Stack的功能：
//
//把元素压栈：push(E)/addFirst(E)；
//把栈顶的元素“弹出”：pop()/removeFirst()；
//取栈顶元素但不弹出：peek()/peekFirst()。

import java.util.Deque;
import java.util.LinkedList;

//没有单独的Stack接口呢？因为有个遗留类名字就叫Stack，
//出于兼容性考虑，所以没办法创建Stack接口，只能用Deque接口来“模拟”一个Stack了。
//把Deque作为Stack使用时，注意只调用push()/pop()/peek()方法，
//不要调用addFirst()/removeFirst()/peekFirst()方法，这样代码更加清晰。
public class StackDemo {
    public static void main(String[] args) {

        //JVM在处理Java方法调用的时候就会通过栈这种数据结构维护方法调用的层次
        //每调用一个方法时，先将参数压栈，然后执行对应的方法；当方法返回时，返回值压栈，调用方法通过出栈操作获得方法返回值。
        //因为方法调用栈有容量限制，嵌套调用过多会造成栈溢出，即引发StackOverflowError：

        //测试无限递归调用
        //increase(1);

        //对整数进行进制的转换就可以利用栈。
        System.out.println(TenToSixty(12500));
    }

    static int increase(int x) {
        return increase(x) + 1;
    }
    static int TenToSixty(int x){
        System.out.println("x/16:"+x/16);
        Deque<Integer> deque = new LinkedList<Integer>();
        while (x / 16 != 0) {
            deque.push(x%16);
            System.out.println("x%16:"+x%16);
            x = x / 16;
            System.out.println("x/16:"+x/16);
        }
        System.out.println(deque.size());
        while (deque.poll()!=null) {
            x = x * 10 + x;
        }
        return x;
    }
}
