package annotatin;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author DELL
 */
//定义Annotation能够被应用于源码的哪些位置
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
//定义了Annotation的生命周期
//如果@Retention不存在，则该Annotation默认为CLASS
@Retention(RetentionPolicy.RUNTIME)
//定义Annotation是否可重复
//@Repeatable(value = Check.class)
//定义子类是否可继承父类定义的Annotation
@Inherited
public @interface Check {

    int[] min() default 1;

    int[] value() default 99;

    int[] max() default 100;
}
