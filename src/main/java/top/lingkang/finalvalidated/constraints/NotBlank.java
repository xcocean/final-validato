package top.lingkang.finalvalidated.annotate;

import java.lang.annotation.*;

/**
 * @author lingkang
 * created by 2024/1/26
 * 注解的元素属性必定不为空、不为空格字符
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotBlank {

    String message() default "{NotBlank}";
    String tag();
}
