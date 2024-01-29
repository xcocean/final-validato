package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang<br/>
 * created by 2024/1/27<br/>
 * 注解的属性最大值，字段小于等于此值<br/>
 * 类型：short, int, long、float、double及其包装类、BigDecimal<br/>
 * 默认返回 {字段名称} 不能大于 {value}
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Max {

    /**
     * 校验值，不能大于此值。例如 value=1，则1、0符合，2不符合
     */
    long value() default 0;

    /**
     * 校验失败时返回的消息，返回例示 message
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 不能大于 {value}
     */
    String tag() default "";
}
