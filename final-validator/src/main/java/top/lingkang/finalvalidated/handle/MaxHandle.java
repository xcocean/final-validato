package top.lingkang.finalvalidated.handle;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.core.FinalValidatorFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class MaxHandle implements ValidHandle {
    private String name;
    private long value;
    private String errorStr;
    private String notNullStr;

    public MaxHandle(String name, String message, String tag, long value) {
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = FinalValidatorFactory.message.getProperty("Max")
                    .replace("{message}", tag)
                    .replace("{value}", Long.toString(value));
        } else if (StrUtil.isEmpty(message)) {
            errorStr = FinalValidatorFactory.message.getProperty("Max")
                    .replace("{message}", name)
                    .replace("{value}", Long.toString(value));
        } else {
            errorStr = message;
        }
        this.name = name;
        this.value = value;
        notNullStr = FinalValidatorFactory.message.getProperty("NotEmpty").replace("{message}", name);
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
        if (o == null) {
            throw new ValidatedException(notNullStr, target.getClass().getSimpleName(), name);
        }
        if (FinalValidatorUtils.notMax(o, value)) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
        }
    }
}
