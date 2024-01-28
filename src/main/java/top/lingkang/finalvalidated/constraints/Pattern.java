package top.lingkang.finalvalidated.constraints;

import java.lang.annotation.*;

/**
 * @author lingkang<br />
 * created by 2024/1/28<br/>
 * 注解的属性的值是否符合自定义正则表达式<br/>
 * value为自定义校验的表达式<br/>
 * 需要注意，校验的对象属性必定不为null。底层校验时会提前判空<br/>
 * 默认返回 {字段名称} 数据校验不通过
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pattern {
    /**
     * 校验自定义正则表达式，不可为空<br/>
     * 需要注意，校验的对象属性必定不为null。底层校验时会提前判空<br/>
     * 并给出一些正则例子：<br/>
     * 判断手机号：^1[3-9]\\d{9}$
     * 判断纯汉字：[\u2E80-\u2EFF\u2F00-\u2FDF\u31C0-\u31EF\u3400-\u4DBF\u4E00-\u9FFF\uF900-\uFAFF\uD840\uDC00-\uD869\uDEDF\uD869\uDF00-\uD86D\uDF3F\uD86D\uDF40-\uD86E\uDC1F\uD86E\uDC20-\uD873\uDEAF\uD87E\uDC00-\uD87E\uDE1F]+
     * 必须包括：英文字母、数字、下划线：^\w+$
     */
    String value() default "";

    /**
     * 校验失败时返回的消息，返回例示 message
     */
    String message() default "";

    /**
     * 校验失败时返回的消息，优先级比 message 高，返回例示 {tag} 数据校验不通过
     */
    String tag() default "";
}
