package top.lingkang.finalvalidated.handle;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.core.ValidatorFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class MinHandle implements ValidHandle {
    private String name;
    private long value;
    private String errorStr;

    public MinHandle(String name, String message, String tag, long value) {
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = ValidatorFactory.message.getProperty("Min")
                    .replace("{message}", tag)
                    .replace("{value}", Long.toString(value));
        } else if (StrUtil.isEmpty(message)) {
            errorStr = ValidatorFactory.message.getProperty("Min")
                    .replace("{message}", name)
                    .replace("{value}", Long.toString(value));
        } else {
            errorStr = message;
        }
        this.name = name;
        this.value = value;
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
        if (o == null || FinalValidatorUtils.notMin(o, value)) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
        }
    }
}
