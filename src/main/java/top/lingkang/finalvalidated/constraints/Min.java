package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang<br/>
 * created by 2024/1/27<br/>
 * 注解的属性最小值，小于等于<br/>
 * 类型：short, int, long、float、double及其包装类、BigDecimal<br/>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Min {

    /**
     * 校验值，不能小于此值。例如 value=1，则1、0不符合，2符合
     */
    long value() default 0;

    /**
     * 校验失败时返回的消息，返回例示 message ，若为空时，将返回 {字段名称}不能为空
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 不能小于 {value}
     */
    String tag() default "";
}
