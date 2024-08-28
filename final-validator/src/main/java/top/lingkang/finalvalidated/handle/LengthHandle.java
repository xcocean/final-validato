package top.lingkang.finalvalidated.handle;

import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class LengthHandle implements ValidHandle {
    private Field field;
    private TakeValue takeValue;
    private long min, max;
    private String errorStr;

    public LengthHandle(Field field, String message, String tag, long min, long max) {
        if (min > max)
            throw new CheckException("@Length 所配置的min值不能大于max值，属性名称：" + field.getName());
        if (min < 0)
            throw new CheckException("@Length 所配置的min值不能小于 0 ，属性名称：" + field.getName());
        if (FinalValidatorUtils.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("Length")
                    .replace("{message}", tag)
                    .replace("{min}", Long.toString(min))
                    .replace("{max}", Long.toString(max));
        } else if (FinalValidatorUtils.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("Length")
                    .replace("{message}", field.getName())
                    .replace("{min}", Long.toString(min))
                    .replace("{max}", Long.toString(max));
        } else {
            errorStr = message;
        }
        this.field = field;
        this.min = min;
        this.max = max;
        takeValue = new TakeValue(field);
    }

    @Override
    public void valid(Object target) {
        if (min == 0)
            return;
        Object o = takeValue.take(target);
        if (o == null || o.toString().length() < min || o.toString().length() > max) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), field.getName());
        }
    }
}
