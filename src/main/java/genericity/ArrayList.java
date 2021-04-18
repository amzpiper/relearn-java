package genericity;

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
        ArrayList<String> arrayList = new ArrayList<String>();
        //不能把ArrayList<Integer>向上转型为ArrayList<Number>或List<Number>。
        //泛型就是编写模板代码来适应任意类型；
        //泛型的好处是使用时不必对类型进行强制转换，它通过编译器对类型进行检查；
        //注意泛型的继承关系：可以把ArrayList<Integer>向上转型为List<Integer>（T不能变！），
        //但不能把ArrayList<Integer>向上转型为ArrayList<Number>（T不能变成父类）。
        //如果不定义泛型类型时，泛型类型实际上就是Object
        //编写泛型类时，要特别注意，泛型类型<T>不能用于静态方法。
        //可以在static修饰符后面加一个<T>，编译就能通过
    }

    //对于静态方法，我们可以单独改写为“泛型”方法，只需要使用另一个类型即可。
    public static <K> ArrayList<K> create(K first, K last) {
        return new ArrayList<K>();
    }
}
