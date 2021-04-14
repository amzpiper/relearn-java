package log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author guoyh
 */
public class CommonLogDemo {
    /**
     * Commons Logging定义了6个日志级别：
     * FATAL
     * ERROR
     * WARNING
     * INFO
     * DEBUG
     * TRACE
     */
    public static void main(String[] args) {
        Log log = LogFactory.getLog(CommonLogDemo.class);
        log.info("start...");
        log.warn("end.");

        foo();
    }

    /**
     * 在静态方法中使用
     */
    static final Log log = LogFactory.getLog(CommonLogDemo.class);
    static void foo() {
        log.info("foo");
    }
}
/**
 * LogFactory.getLog(getClass())，
 * 虽然也可以用LogFactory.getLog(Person.class)，
 * 但是前一种方式有个非常大的好处，就是子类可以直接使用该log实例
 */
class Person{
    protected final Log log = LogFactory.getLog(getClass());

    void foo() {
        log.info("person-foo");
    }
}