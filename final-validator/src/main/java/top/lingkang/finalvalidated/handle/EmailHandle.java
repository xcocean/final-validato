package top.lingkang.finalvalidated.handle;

import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class EmailHandle implements ValidHandle {
    private final Field field;
    private final TakeValue takeValue;
    private final String errorStr;
    private final Pattern pattern;

    public EmailHandle(Field field, String message, String tag, String value) {
        if (FinalValidatorUtils.isBlank(value)) {
            throw new CheckException("@Email 所配置的正则表达式(value)不能为空！字段属性：" + field.getName());
        }
        if (FinalValidatorUtils.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("Email")
                    .replace("{message}", tag);
        } else if (FinalValidatorUtils.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("Email")
                    .replace("{message}", field.getName());
        } else {
            errorStr = message;
        }
        this.field = field;
        takeValue = new TakeValue(field);
        pattern = Pattern.compile(value);
    }

    @Override
    public void valid(Object target) {
        Object o = takeValue.take(target);
        if (o == null || !pattern.matcher(o.toString()).matches()) {
            throw new ValidatedException(errorStr, target.getClass(), field.getName());
        }
    }
}
