package top.lingkang.finalvalidated.handle;

import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class NotBlankHandle implements ValidHandle {
    private final String errorStr;
    private final TakeValue takeValue;
    private final Field field;

    public NotBlankHandle(Field field, String message, String tag) {
        if (FinalValidatorUtils.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("NotBlank").replace("{message}", tag);
        } else if (FinalValidatorUtils.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("NotBlank").replace("{message}", field.getName());
        } else {
            errorStr = message;
        }
        this.field = field;
        takeValue = new TakeValue(field);
    }

    @Override
    public void valid(Object target) {
        Object take = takeValue.take(target);
        if (take == null || FinalValidatorUtils.isBlank(take.toString())) {
            throw new ValidatedException(errorStr, target.getClass(), field.getName());
        }
    }
}
