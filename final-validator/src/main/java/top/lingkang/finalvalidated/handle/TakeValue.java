package top.lingkang.finalvalidated.handle;

import java.lang.reflect.Field;

/**
 * 反射取值
 *
 * @author lingkang
 * @create by 2024/3/5 10:09
 */
class TakeValue {
    private volatile Field field;

    public TakeValue(Field field) {
        this.field = field;
        field.setAccessible(true);
    }

    public Object take(Object value) {
        try {
            return field.get(value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
