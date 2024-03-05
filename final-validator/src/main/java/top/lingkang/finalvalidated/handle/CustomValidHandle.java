package top.lingkang.finalvalidated.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/28
 * 自定义校验处理
 */
public class CustomValidHandle implements ValidHandle {

    protected Field field;
    protected Annotation annotation;

    /**
     * 初始化构造函数
     *
     * @param field      对象属性
     * @param annotation 自定义的注解对象
     */
    public CustomValidHandle(Field field, Annotation annotation) {
        this.annotation = annotation;
        this.field = field;
    }

    /**
     * 校验失败时，建议抛出 {@link top.lingkang.finalvalidated.error.ValidatedException} 异常
     *
     * @param target 待校验对象
     */
    @Override
    public void valid(Object target) {
        // 在此执行对 target 的校验
    }
}
