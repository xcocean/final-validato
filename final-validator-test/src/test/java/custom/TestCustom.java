package custom;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestCustom {
    /**
     * 自定义注解，给一个值，可以自定义多个
     */
    String value() default "123";
}
