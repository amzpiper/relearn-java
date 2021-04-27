package collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author guoyh
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        List<String> list = new LinkedList<String>();
        Collections.addAll(list, "1", "2", "3");
        for (String s : list) {
            System.out.println(s);
        }

        //空集合
        List emptyList = Collections.EMPTY_LIST;
        emptyList.add("1");

        //创建单元素集合
        List<Object> objects = Collections.singletonList("");

        //排序
        //对List进行排序
        Collections.sort(list);

        //洗牌
        Collections.shuffle(list);

        //把可变集合封装成不可变集合
        //实际上是通过创建一个代理对象，拦截掉所有修改方法实现的
        Collections.unmodifiableList(list);

        //可以把线程不安全的集合变为线程安全的集合
        Collections.synchronizedList(list);
    }
}
