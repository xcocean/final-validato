package top.lingkang.finalvalidated.handle;

import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class AssertFalseHandle implements ValidHandle {
    private final String errorStr;
    private final Field field;
    private final TakeValue takeValue;

    public AssertFalseHandle(Field field, String message, String tag) {
        if (FinalValidatorUtils.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("AssertFalse").replace("{message}", tag);
        } else if (FinalValidatorUtils.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("AssertFalse").replace("{message}", field.getName());
        } else {
            errorStr = message;
        }
        this.field = field;
        takeValue = new TakeValue(field);
    }

    @Override
    public void valid(Object target) {
        Object o = takeValue.take(target);
        if (o == null)
            throw new ValidatedException(errorStr, target.getClass(), field.getName());
        if (o.getClass().isAssignableFrom(Boolean.class)) {
            if (Boolean.parseBoolean(o.toString()))
                throw new ValidatedException(errorStr, target.getClass(), field.getName());
        } else if (o.getClass().isAssignableFrom(String.class)) {
            if (!"false".equals(o) || Boolean.parseBoolean(o.toString())) {
                throw new ValidatedException(errorStr, target.getClass(), field.getName());
            }
        } else
            throw new ValidatedException(errorStr, target.getClass(), field.getName());

    }
}
