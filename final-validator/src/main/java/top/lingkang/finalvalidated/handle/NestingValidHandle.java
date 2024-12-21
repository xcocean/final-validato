package top.lingkang.finalvalidated.handle;

import top.lingkang.finalvalidated.constraints.Tag;
import top.lingkang.finalvalidated.error.ValidatedException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 嵌套校验对象，报错将返回json形式：
 * <pre>
 * {@code
 * {"属性":{"嵌套属性1":{"nestingParam2":"嵌套3 不能为空"}}}
 * }
 * </pre>
 *
 * @author lingkang
 * Create by 2024/12/22 0:53
 * @since 2.3.0
 */
public class NestingValidHandle implements ValidHandle {
    private final List<ValidHandle> handles;
    private final Field field;
    private final String nullError;
    private final String tip;

    public NestingValidHandle(List<ValidHandle> handles, Field field) {
        this.handles = handles;
        this.field = field;
        Tag tag = field.getAnnotation(Tag.class);
        tip = tag != null ? tag.value() : field.getName();
        nullError = tip + " 参数属性不能为空";
    }

    @Override
    public void valid(Object target) {
        Object val = null;
        try {
            val = field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (val == null)
            throw new ValidatedException(nullError);
        try {
            for (ValidHandle handle : handles) {
                handle.valid(val);
            }
        } catch (ValidatedException e) {
            if (e.getMessage().endsWith("}")) {
                e.setMessage("{\"" + tip + "\":" + e.getMessage() + "}");
            } else
                e.setMessage("{\"" + tip + "\":\"" + e.getMessage() + "\"}");
            throw e;
        }
    }
}
