package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang <br/>
 * created by 2024/1/28 <br/>
 * 验证注解的元素值是否是 false ，当值为 false 时将通过校验<br/>
 * 默认返回 {字段名称} 必须为false
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AssertFalse {

    /**
     * 校验失败时返回的消息，返回例示 message
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 必须为false
     */
    String tag() default "";
}
