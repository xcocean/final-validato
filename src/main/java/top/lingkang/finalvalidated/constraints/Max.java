package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang<br/>
 * created by 2024/1/26<br/>
 * 注解的属性最大值，大于等于<br/>
 * 类型：short, int, long、BigDecimal<br/>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Max {

    /**
     * 校验值
     */
    long value() default 0;

    /**
     * 校验失败时返回的消息，返回例示 message ，若为空时，将返回 {字段名称}不能为空
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag}不能为空
     */
    String tag() default "";
}
