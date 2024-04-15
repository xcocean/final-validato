package top.lingkang.finalvalidated.handle;

import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class MaxHandle implements ValidHandle {
    private Field field;
    private TakeValue takeValue;
    private long value;
    private String errorStr;
    private String notNullStr;

    public MaxHandle(Field field, String message, String tag, long value) {
        if (FinalValidatorUtils.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("Max")
                    .replace("{message}", tag)
                    .replace("{value}", Long.toString(value));
            notNullStr = FinalValidatorFactory.message.getProperty("NotEmpty").replace("{message}", tag);
        } else if (FinalValidatorUtils.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("Max")
                    .replace("{message}", field.getName())
                    .replace("{value}", Long.toString(value));
            notNullStr = FinalValidatorFactory.message.getProperty("NotEmpty").replace("{message}", field.getName());
        } else {
            errorStr = message;
        }
        this.field = field;
        this.value = value;
        takeValue = new TakeValue(field);
    }

    @Override
    public void valid(Object target) {
        Object take = takeValue.take(target);
        if (take == null) {
            throw new ValidatedException(notNullStr, target.getClass().getSimpleName(), field.getName());
        }
        if (FinalValidatorUtils.notMax(take, value)) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), field.getName());
        }
    }
}
