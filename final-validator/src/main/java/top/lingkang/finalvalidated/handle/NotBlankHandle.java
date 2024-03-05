package top.lingkang.finalvalidated.handle;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.ValidatedException;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class NotBlankHandle implements ValidHandle {
    private String errorStr;
    private TakeValue takeValue;
    private Field field;

    public NotBlankHandle(Field field, String message, String tag) {
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("NotBlank").replace("{message}", tag);
        } else if (StrUtil.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("NotBlank").replace("{message}", field.getName());
        } else {
            errorStr = message;
        }
        this.field=field;
        takeValue = new TakeValue(field);
    }

    @Override
    public void valid(Object target) {
        Object take = takeValue.take(target);
        if (take == null || StrUtil.isBlank(take.toString())) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), field.getName());
        }
    }
}
