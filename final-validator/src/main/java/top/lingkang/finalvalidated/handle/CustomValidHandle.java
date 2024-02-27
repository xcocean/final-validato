package top.lingkang.finalvalidated.handle;

import java.lang.annotation.Annotation;

/**
 * @author lingkang
 * Created by 2024/1/28
 * 自定义校验处理
 */
public class CustomValidHandle implements ValidHandle {

    protected String name;
    protected Annotation annotation;

    /**
     * 初始化构造函数
     * @param name 对象属性名称
     * @param annotation 自定义的注解对象
     */
    public CustomValidHandle(String name, Annotation annotation) {
        this.annotation = annotation;
        this.name = name;
    }

    /**
     * 校验失败时，建议抛出 {@link top.lingkang.finalvalidated.error.ValidatedException} 异常
     * @param target 待校验对象
     */
    @Override
    public void valid(Object target) {
        // 在此执行对 target 的校验
    }
}
