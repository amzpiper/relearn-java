package genericity;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class GenericityAndRefect {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Integer> clazz = Integer.class;
        Constructor<Integer> cons = clazz.getDeclaredConstructor(int.class);
        Integer i = cons.newInstance(123);
        System.out.println(i);

        Pair<String>[] ps = null; // ok
        Pair<String>[] ps2 = (Pair<String>[]) new Pair[2]; // compile error

        Pair[] arr = new Pair[2];
        Pair<String>[] ps3 = (Pair<String>[]) arr;

        //https://www.liaoxuefeng.com/wiki/1252599548343744/1265105940850016
        //必须借助Class<T>来创建泛型数组：
        createArray(ps3);

        //可以利用可变参数创建泛型数组T[]
        //谨慎使用泛型可变参数
        String arr2[] = asArray("one", "two", "three");
        System.out.println(Arrays.toString(arr2));
        //ClassCastException
        //第一层方法就把String擦拭成Object了，所以第二个方法T[] asArray(T... objs)在编译器检查泛型时会报错。
        String[] firstTwo = pickTwo("one", "two", "three");
        System.out.println(Arrays.toString(firstTwo));
    }

    static <T> T[] asArray(T... objs) {
        return objs;
    }

    static <K> K[] pickTwo(K k1, K k2, K k3) {
        return asArray(k1, k2);
    }

    //带泛型的数组实际上是编译器的类型擦除,擦拭后代码变为Object[]
    static <E> E[] createArray(Class<E> cls) {
        //return new E[5];
        return (E[]) Array.newInstance(cls, 5);
    }
}
