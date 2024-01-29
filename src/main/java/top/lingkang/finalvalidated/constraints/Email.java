package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang <br/>
 * created by 2024/1/28<br/>
 * 注解的属性的值是否是邮箱<br/>
 * 可自定义校验的表达式<br/>
 * 默认返回 {字段名称} 不是邮箱格式
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Email {
    /**
     * 邮箱校验的正则表达式，可自定义替换默认的表达式<br/>
     * 校验邮箱的正则表达式<br/>
     * 邮件，符合RFC 5322规范，正则来自：<a href="http://emailregex.com/">http://emailregex.com/</a><br/>
     * <a href="https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address/44317754">What is the maximum length of a valid email address?</a><br/>
     * 注意email 要宽松一点。<br/>
     * 比如 lk.acg@lingkang.top、l-k@lingkang.top、lk_zcg@lingkang.top、lk.acg@lingkang.top<br/>
     * 宽松一点把，都算是正常的邮箱<br/>
     */
    String value() default "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";

    /**
     * 校验失败时返回的消息，返回例示 message
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 不是邮箱格式
     */
    String tag() default "";
}
