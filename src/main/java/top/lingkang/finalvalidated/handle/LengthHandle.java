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
public class LengthHandle implements ValidHandle {
    private String name;
    private long min, max;
    private String errorStr;

    public LengthHandle(String name, String message, String tag, long min, long max) {
        if (min == max)
            throw new CheckException("@Length 所配置的最大最小值不能相等，属性名称：" + name);
        if (min > max)
            throw new CheckException("@Length 所配置的min值不能大于max值，属性名称：" + name);
        if (min < 0)
            throw new CheckException("@Length 所配置的min值不能小于 0 ，属性名称：" + name);
        if (StrUtil.isNotEmpty(tag)) {
            errorStr = ValidatorFactory.message.getProperty("Length")
                    .replace("{message}", tag)
                    .replace("{min}", Long.toString(min))
                    .replace("{max}", Long.toString(max));
        } else if (StrUtil.isEmpty(message)) {
            errorStr = ValidatorFactory.message.getProperty("Length")
                    .replace("{message}", name)
                    .replace("{min}", Long.toString(min))
                    .replace("{max}", Long.toString(max));
        } else {
            errorStr = message;
        }
        this.name = name;
        this.min = min;
        this.max = max;
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
        if (o == null && min == 0)
            return;
        if (o == null || o.toString().length() < min || o.toString().length() > max) {
            throw new ValidatedException(errorStr, target.getClass().getSimpleName(), name);
        }
    }
}
