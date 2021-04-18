package genericity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class ArrayList<T> {
    private T[] array;
    private int size;

    public void add(T t) {

    }

    public void remove(int index) {

    }

    public T get(int index) {
        return array[index];
    }

    public static void main(String[] args) {
        //1.转型
        ArrayList<String> arrayList = new ArrayList<String>();
        //不能把ArrayList<Integer>向上转型为ArrayList<Number>或List<Number>。
        //泛型就是编写模板代码来适应任意类型；
        //泛型的好处是使用时不必对类型进行强制转换，它通过编译器对类型进行检查；
        //注意泛型的继承关系：可以把ArrayList<Integer>向上转型为List<Integer>（T不能变！），
        //但不能把ArrayList<Integer>向上转型为ArrayList<Number>（T不能变成父类）。
        //如果不定义泛型类型时，泛型类型实际上就是Object
        //编写泛型类时，要特别注意，泛型类型<T>不能用于静态方法。
        //可以在static修饰符后面加一个<T>，编译就能通过

        //2.静态方法，我们可以单独改写为“泛型”方法，只需要使用另一个类型即可。

        //3.多个泛型类型
        PersonList<String, Integer> personList = new PersonList<>("guoyuhang", 25);
        System.out.println(personList.toString());

        //4.擦拭法
        //java语言的泛型实现方式是擦拭法（Type Erasure）。
        //所谓擦拭法是指，虚拟机对泛型其实一无所知，所有的工作都是编译器做的。
        //Java的泛型是由编译器在编译时实行的，编译器内部永远把所有类型T视为Object处理，
        //但是，在需要转型的时候，编译器会根据T的类型自动为我们实行安全地强制转型。
        //Java使用擦拭法实现泛型，导致了：
        //编译器把类型<T>视为Object；
        //编译器根据<T>实现安全的强制转型。
        //Java的泛型是由编译器在编译时实行的，编译器内部永远把所有类型T视为Object处理，
        //但是，在需要转型的时候，编译器会根据T的类型自动为我们实行安全地强制转型。
        //
        //局限:
        //局限一：<T>不能是基本类型，例如int，因为实际类型是Object，Object类型无法持有基本类型：
        //局限二：无法取得带泛型的Class,无论T的类型是什么，getClass()返回同一个Class实例，因为编译后它们全部都是Pair<Object>
        //局限三：无法判断带泛型的类型：p instanceof Pair<String>,并不存在Pair<String>.class，而是只有唯一的Pair.class。
        //局限四：不能实例化T类型：擦拭后实际上变成了：first = new Object();
        //借助Class<T>参数并通过反射来实例化T类型，使用的时候，也必须传入Class<T>。例如：
        try {
            Pair<String> pair = new Pair<>(String.class);
            pair.setFirst("guo");
            pair.setLast("yuhang");
            System.out.println(pair.getFirst()+pair.getLast());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        //在继承了泛型类型的情况下，子类可以获取父类的泛型类型。
        //例如：IntPair可以获取到父类的泛型类型Integer。获取父类的泛型类型代码比较复杂：
        Class<IntPair> class_ = IntPair.class;
        Type type = class_.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) type;
            // 可能有多个泛型类型
            Type[] types = pt.getActualTypeArguments();
            // 取第一个泛型类型
            Type firstType = types[0];
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass);
        }

        //总结:
        //Java的泛型是采用擦拭法实现的；
        //擦拭法决定了泛型<T>：
        //  不能是基本类型，例如：int；
        //  不能获取带泛型类型的Class，例如：Pair<String>.class；
        //  不能判断带泛型类型的类型，例如：x instanceof Pair<String>；
        //  不能实例化T类型，例如：new T()。
        //泛型方法要防止重复定义方法，例如：public boolean equals(T obj)；
        //子类可以获取父类的泛型类型<T>。


        //extends
        Pair2<Integer> pair2 = new Pair2<>(1,2);
        add(pair2);

        //只读list
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        int count = sumOfList(list);
        System.out.println(count);

        //extend限制T类型
        Pair3<Number> p1 = null;
        Pair3<Integer> p2 = new Pair3<>(1, 2);
        Pair3<Double> p3 = null;

        //小结
        //使用类似<? extends Number>通配符作为方法参数时表示：
        //  方法内部可以调用获取Number引用的方法，例如：Number n = obj.getFirst();；
        //  方法内部无法调用传入Number引用的方法（null除外），例如：obj.setFirst(Number n);。
        //即一句话总结：使用extends通配符表示可以读，不能写。
        //使用类似<T extends Number>定义泛型类时表示：
        //  泛型类型限定为Number以及Number的子类。
    }

    //对于静态方法，我们可以单独改写为“泛型”方法，只需要使用另一个类型即可。
    public static <K> ArrayList<K> create(K first, K last) {
        return new ArrayList<K>();
    }
    //这种使用<? extends Number>的泛型定义称之为上界通配符,把泛型类型T的上界限定在Number
    static void add(Pair2<? extends Number> pair2) {
        Number first = pair2.getFirst();
        Number last = pair2.getLast();
        //方法参数签名setFirst(? extends Number)无法传递任何Number的子类型给setFirst(? extends Number)。
        //这里唯一的例外是可以给方法参数传入null
        //pair2.setFirst(new Integer(first.intValue() + 100));
        //pair2.setLast(new Integer(last.intValue() + 100));
        System.out.println(first.intValue() + "" + last.intValue());
    }
    //允许调用get()方法获取Integer的引用；
    //不允许调用set(? extends Integer)方法并传入任何Integer的引用（null除外）
    //表明了该方法内部只会读取List的元素，不会修改List的元素
    static int sumOfList(List<? extends Integer> list) {
        int sum = 0;
        for (int i=0; i<list.size(); i++) {
            Integer n = list.get(i);
            System.out.println(n);
            sum = sum + n;
        }
        return sum;
    }
}

class PersonList<K, T> {
    private K name;
    private T age;

    public PersonList(K name, T age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonList{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}
class Pair<T> {
    private T first;
    private T last;
    public Pair(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        first = clazz.newInstance();
        last = clazz.newInstance();
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setLast(T last) {
        this.last = last;
    }
}

class IntPair extends Pair<Integer> {

    public IntPair(Class<Integer> clazz) throws IllegalAccessException, InstantiationException {
        super(clazz);
    }
}

class Pair2<T> {
    private T first;
    private T last;
    public Pair2(T first, T last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setLast(T last) {
        this.last = last;
    }
}

//定义泛型类型Pair<T>的时候，也可以使用extends通配符来限定T的类型
class Pair3<T extends Number> {
    private T first;
    private T last;
    public Pair3(T first, T last) {
        this.first = first;
        this.last = last;
    }
    public T getFirst() {
        return first;
    }
    public T getLast() {
        return last;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setLast(T last) {
        this.last = last;
    }
}
