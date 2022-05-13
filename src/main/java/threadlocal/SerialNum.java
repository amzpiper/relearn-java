package threadlocal;

public class SerialNum {

    private static int nextSerialNum = 0;

    private static ThreadLocal<Integer> threadLocalSerialNum = new ThreadLocal<Integer>() {
        protected synchronized Integer initialValue() {
            return nextSerialNum++;
        }
    };
    
    public static int getSerialNum() {
        return threadLocalSerialNum.get().intValue();
    }
}
