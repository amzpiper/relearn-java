package exception;

import java.io.UnsupportedEncodingException;

public class Exce {

    public static void main(String[] args) throws Exception {
        int code = 0;

        try {
            String s = "";
            byte[] b= s.getBytes("GBK");
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("continue");

        //通过Throwable.getSuppressed()可以获取所有的Suppressed Exception。
        //绝大多数情况下，在finally中不要抛出异常。因此，我们通常不需要关心Suppressed Exception。
        Exception orgin = null;
        try {
            //Integer.parseInt("abc");
            throw new BaseException();
        } catch (BaseException e) {
            orgin = e;
            throw e;
        } finally {
            Exception e = new IllegalAccessException();
            if (orgin != null) {
                e.addSuppressed(orgin);
            }
            throw e;
        }
    }
}
