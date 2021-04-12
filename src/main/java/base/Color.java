package base;

/**
 * @author guoyh
 */

public enum Color {

    /**
     * 红色
     */
    RED("red","红色"),
    /**
     * 蓝色
     */
    BLUE("blue","蓝色"),
    ;

    public final String value;
    public final String chinese;

    private Color(String color,String chinese) {
        this.value = color;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return "Color{" +
                "value='" + this.value + '\'' +
                ", chinese='" + this.chinese + '\'' +
                '}';
    }
}
