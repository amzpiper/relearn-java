package qipa;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author guoyha
 */
public class TrueTest {

    public static void main(String[] args) {
        Boolean reality = true;
        if(reality) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    static {
        try {
            Field trueField = Boolean.class.getDeclaredField("TRUE");
            trueField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);

            modifiersField.setInt(trueField, trueField.getModifiers() & ~Modifier.FINAL);

            trueField.set(null, false);
        } catch(IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
