package qipa;

/**
 * @author guoyha
 */
public class IfTest {
    public static void main(String[] args) {
        judge("Hydra");
    }

    public static void judge(String param) {
        if (
                param == null ||
                new IfTest() {
                    {
                        IfTest.judge(null);
                        IfTest.judge(null);
                    }
                }.equals("Hydra") ||
                new IfTest() {
                    {
                        IfTest.judge(null);
                        IfTest.judge(null);
                    }
                }.equals("Hydra")
        ) {
            System.out.println("step one");
        } else {
            System.out.println("step two");
        }
    }
}
