package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang <br/>
 * created by 2024/2/3<br/>
 * 注解的属性的tag值<br/>
 * 此注解优先级最高，将会覆盖该字段其他注解的所有tag值<br/>
 * 例示：之前写法：
 * <pre>
 * {@code
 * @Length(min = 3, max = 16, tag="密码")
 * @NotBlank(tag="密码")
 * String password;
 * }
 * </pre>
 * <br/>
 * 使用 @Tag 之后写法：
 * <pre>
 * {@code
 *  @Tag("密码")
 *  @Length(min = 3, max = 16)
 *  @NotBlank
 *  String password;
 * }
 * </pre>
 * 其他写法：作用于嵌套校验对象(v2.3.0+)
 * <pre>
 * {@code
 * @Data
 * public class NestingParam1 {
 *     @Tag("嵌套2属性") // v2.3.0+
 *     private NestingParam2 nestingParam2;
 *     @Tag("嵌套1")
 *     @NotBlank
 *     private String nesting1;
 * }
 * }
 * </pre>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tag {

    /**
     * 消息提示的tag值，优先级高，将会覆盖该字段其他tag值。但是无法覆盖message的内容，message 比 tag 优先级更高
     */
    String value() default "";
}
