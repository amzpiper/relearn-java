package refect;

public class ParentClassDemo {
    public static void main(String[] args) {
        // 1.获取父类
        Class integer = Integer.class;
        Class parentInteger = integer.getSuperclass();
        System.out.println(parentInteger);

        Class parentParentInteger = parentInteger.getSuperclass();
        System.out.println(parentParentInteger);

        // 2.获取类实现的接口
        Class s = Integer.class;
        Class[] is = s.getInterfaces();
        for (Class i : is) {
            System.out.println(i);
        }
        // 获取接口的父接口要用getInterfaces()

        // 3.继承关系
        // 正常情况下，使用instanceof操作符：
        Object n = Integer.valueOf(123);
        // false
        boolean isDouble = n instanceof Double;

        // 判断一个向上转型是否成立，可以调用isAssignableFrom()：
        System.out.println(Number.class.isAssignableFrom(Integer.class));
    }
}
