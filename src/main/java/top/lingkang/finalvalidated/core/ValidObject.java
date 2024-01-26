package top.lingkang.finalvalidated.annotate;

import java.lang.annotation.*;

/**
 * @author lingkang
 * created by 2024/1/26
 * 开启需要检查的入参对象
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidObject {
}
