package top.lingkang.finalvalidated.handle;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.core.ValidatorFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;

import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class NotEmptyHandle implements ValidHandle {
    private String errorStr;
    private String name;

    public NotEmptyHandle(String name, String message, String tag) {
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = ValidatorFactory.message.getProperty("NotEmpty").replace("{message}", tag);
        } else if (StrUtil.isEmpty(message)) {
            errorStr = ValidatorFactory.message.getProperty("NotEmpty").replace("{message}", name);
        } else {
            errorStr = message;
        }
        this.name = name;
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
        if (o == null || StrUtil.isEmpty(o.toString())) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
        }
    }
}
