package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang <br/>
 * created by 2024/2/3<br/>
 * 注解的属性的tag值<br/>
 * 此注解优先级最高，将会覆盖该字段其他注解的所有tag值<br/>
 * 例示：之前写法：<br/>
 * {@code @Length(min = 3, max = 16, tag="密码")} <br/>
 * {@code @NotBlank(tag="密码")} <br/>
 * String password; <br/>
 * <br/><br/>
 * @Tag 之后写法：<br/>
 * {@code @Tag("密码")} <br/>
 * {@code @Length(min = 3, max = 16)} <br/>
 * {@code @NotBlank} <br/>
 * String password; <br/>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tag {

    /**
     * 消息提示的tag值，优先级最高，将会覆盖该字段其他tag值。
     */
    String value() default "";
}
