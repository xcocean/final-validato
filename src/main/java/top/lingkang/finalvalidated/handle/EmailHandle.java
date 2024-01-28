package top.lingkang.finalvalidated.handle;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class EmailHandle implements ValidHandle {
    private String name;
    private String errorStr;
    private Pattern pattern;

    public EmailHandle(String name, String message, String tag, String value) {
        if (StrUtil.isBlank(value)) {
            throw new CheckException("@Email 所配置的正则表达式(value)不能为空！字段属性：" + name);
        }
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("Email")
                    .replace("{message}", tag);
        } else if (StrUtil.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("Email")
                    .replace("{message}", name);
        } else {
            errorStr = message;
        }
        this.name = name;
        pattern = Pattern.compile(value);
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
        if (o == null || !pattern.matcher(o.toString()).matches()) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
        }
    }
}
