package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        //Java编译器并不知道如何遍历List。上述代码能够编译通过，
        //只是因为编译器把for each循环通过Iterator改写为了普通的for循环：

        //如果把ArrayList换成LinkedList，get(int)方法耗时会随着index的增加而增加。
        //如果把ArrayList换成Set，上述代码就无法编译，因为Set内部没有索引。

        //如果我们自己编写了一个集合类，想要使用for each循环，只需满足以下条件：
        //集合类实现Iterable接口，该接口要求返回一个Iterator对象；
        //用Iterator对象迭代集合内部数据。

        //集合类通过调用iterator()方法，返回一个Iterator对象，这个对象必须自己知道如何遍历该集合。
        ReverseList<String> reverseList = new ReverseList<String>();
        reverseList.add("1");
        reverseList.add("2");
        reverseList.add("3");
        for (String s : reverseList) {
            System.out.println(s);
        }
        reverseList.forEach(item ->{
            System.out.println(item);
        });
        //编写Iterator的时候，我们通常可以用一个内部类来实现Iterator接口，这个内部类可以直接访问对应的外部类的所有字段和方法。
        //例如，上述代码中，内部类ReverseIterator可以用ReverseList.this获得当前外部类的this引用，
        //然后，通过这个this引用就可以访问ReverseList的所有字段和方法。

        //小结
        //Iterator是一种抽象的数据访问模型。使用Iterator模式进行迭代的好处有：
        //对任何集合都采用同一种访问模型；
        //调用者对集合内部结构一无所知；
        //集合类返回的Iterator对象知道如何迭代。
    }
}

class ReverseList<T> implements Iterable<T> {

    private List<T> list = new ArrayList<>();

    public void add(T t) {
        list.add(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseIterator(list.size());
    }

    class ReverseIterator implements Iterator<T>{
        int index;

        public ReverseIterator(int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public T next() {
            index--;
            return ReverseList.this.list.get(index);
        }
    }
}
