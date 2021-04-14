package refect;

import org.apache.commons.logging.LogFactory;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //方法1.获取String的Class实例
        Class cls = String.class;
        //方法2.通过getClass()
        String s = "s";
        Class sCls = s.getClass();
        //方法3.完整类名，可以通过静态方法Class.forName()获取：
        Class clsName = Class.forName("java.lang.String");

        //==比较
        System.out.println(cls == sCls);
        System.out.println(clsName == sCls);
        System.out.println(cls == clsName);

        //instanceof比较
        Integer n = 1;
        System.out.println(n instanceof Integer);
        System.out.println(n instanceof Number);

        System.out.println(n.getClass() == Integer.class);
        //System.out.println(n.getClass() == Number.class);

        Class class_ = String.class;
        System.out.println(class_.getName());
        System.out.println(class_.getSimpleName());
        System.out.println(class_.getPackage().getName());
        System.out.println(class_.isInterface());

        //用class创建实例,只能调用public的无参数构造方法
        String string = (String) class_.newInstance();

        //JVM在执行Java程序的时候，并不是一次性把所有用到的class全部加载到内存，而是第一次需要用到class时才加载
        LogFactory factory = null;
        if (isClassPresent("org.apache.logging.log4j.Logger")) {
            factory = createLog4j();
        }else{
            factory = createJdkLog();
        }
    }

    private static LogFactory createJdkLog() {
        return LogFactory.getFactory();
    }

    private static LogFactory createLog4j() {
        return LogFactory.getFactory();
    }

    //动态加载,只有存在该类时，就用该类
    static boolean isClassPresent(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
