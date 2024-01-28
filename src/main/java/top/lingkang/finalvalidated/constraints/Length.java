package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang<br />
 * created by 2024/1/26<br/>
 * 注解的属性的长度，指定最小长度和最大长度，min与max不能相等<br/>
 * 通常用于校验string字符串，注意，其它类型将被 toString() 后比较<br/>
 * 类型：short, int, long、BigDecimal 将被 toString() 后比较<br/>
 * 需要注意，若min=0，则对象可能为空值<br/>
 * 默认返回 {字段名称} 字符长度范围： {min} ~ {max}
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Length {

    /**
     * 最小长度
     */
    long min() default 0;

    /**
     * 最大长度
     */
    long max() default 0;

    /**
     * 校验失败时返回的消息，返回例示 message
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 字符长度范围： {min} ~ {max}
     */
    String tag() default "";
}
