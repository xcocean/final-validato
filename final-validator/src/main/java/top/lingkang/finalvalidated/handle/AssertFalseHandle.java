package top.lingkang.finalvalidated.handle;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class AssertFalseHandle implements ValidHandle {
    private String errorStr;
    private String name;

    public AssertFalseHandle(String name, String message, String tag) {
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("AssertFalse").replace("{message}", tag);
        } else if (StrUtil.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("AssertFalse").replace("{message}", name);
        } else {
            errorStr = message;
        }
        this.name = name;
    }

    @Override
    public void valid(Object target) {
        Object o = null;
        try {
            Field field = target.getClass().getDeclaredField(name);
            field.setAccessible(true);
            o = field.get(target);

        } catch (Exception e) {
            throw new CheckException(e);
        }
        if (o == null)
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
        if (o.getClass().isAssignableFrom(Boolean.class)) {
            if (Boolean.parseBoolean(o.toString()))
                throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
        } else if (o.getClass().isAssignableFrom(String.class)) {
            if (!"false".equals(o) || Boolean.parseBoolean(o.toString())) {
                throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
            }
        } else
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);

    }
}
