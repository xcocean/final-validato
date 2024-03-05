package top.lingkang.finalvalidated.handle;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class MinHandle implements ValidHandle {
    private Field field;
    private long value;
    private TakeValue takeValue;
    private String errorStr;
    private String notNullStr;

    public MinHandle(Field field, String message, String tag, long value) {
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("Min")
                    .replace("{message}", tag)
                    .replace("{value}", Long.toString(value));
        } else if (StrUtil.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("Min")
                    .replace("{message}", field.getName())
                    .replace("{value}", Long.toString(value));
        } else {
            errorStr = message;
        }
        this.value=value;
        this.field = field;
        takeValue = new TakeValue(field);
        notNullStr = FinalValidatorFactory.message.getProperty("NotEmpty").replace("{message}", field.getName());
    }

    @Override
    public void valid(Object target) {
        Object take = takeValue.take(target);
        if (take == null) {
            throw new ValidatedException(notNullStr, target.getClass().getSimpleName(), field.getName());
        }
        if (FinalValidatorUtils.notMin(take, value)) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), field.getName());
        }
    }
}
