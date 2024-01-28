package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang<br />
 * created by 2024/1/28<br/>
 * 注解的属性必定是空，即 null 值，<br/>
 * 例如 /login?username=&password=123，中，username、password不为null，phone为null<br/>
 * 默认返回 {字段名称} 必须为空
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Null {

    /**
     * 校验失败时返回的消息，返回例示 message
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 必须为空
     */
    String tag() default "";
}
