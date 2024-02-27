package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang <br/>
 * created by 2024/1/28<br/>
 * 注解的属性必定不是空，即 非 null 值<br/>
 * 默认返回 {字段名称} 不能为空<br/>
 * 适用类型：string、Integer、Long、Boolean等包装类<br/>
 * int、long、boolean等基础类型将不适用，将会通过检查
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNull {

    /**
     * 校验失败时返回的消息，返回例示 message
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 不能为空
     */
    String tag() default "";
}
