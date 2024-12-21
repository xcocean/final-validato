package top.lingkang.finalvalidated.handle;

import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class MinHandle implements ValidHandle {
    private final Field field;
    private final long value;
    private final TakeValue takeValue;
    private final String errorStr;
    private String notNullStr;

    public MinHandle(Field field, String message, String tag, long value) {
        if (FinalValidatorUtils.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("Min")
                    .replace("{message}", tag)
                    .replace("{value}", Long.toString(value));
            notNullStr = FinalValidatorFactory.message.getProperty("NotEmpty").replace("{message}", tag);
        } else if (FinalValidatorUtils.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("Min")
                    .replace("{message}", field.getName())
                    .replace("{value}", Long.toString(value));
            notNullStr = FinalValidatorFactory.message.getProperty("NotEmpty").replace("{message}", field.getName());
        } else {
            errorStr = message;
        }
        this.value = value;
        this.field = field;
        takeValue = new TakeValue(field);
    }

    @Override
    public void valid(Object target) {
        Object take = takeValue.take(target);
        if (take == null) {
            throw new ValidatedException(notNullStr, target.getClass(), field.getName());
        }
        if (FinalValidatorUtils.notMin(take, value)) {
            throw new ValidatedException(errorStr, target.getClass(), field.getName());
        }
    }
}
