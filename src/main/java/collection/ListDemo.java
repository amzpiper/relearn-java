package collection;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        //ArrayList先创建一个更大的新数组，然后把旧数组的所有元素复制到新数组，紧接着用新数组取代旧数组：
        List list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        System.out.println(list.size());
    }
}
