package qipa;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author guoyha
 */
public class Caozuoneicun {

    public static void main(String[] args) {
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);

            long addr = unsafe.allocateMemory(4);
            unsafe.putInt(addr, 1);
            int a = unsafe.getInt(addr);
            System.out.println(a);
            unsafe.freeMemory(addr);
            System.out.println(a);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
