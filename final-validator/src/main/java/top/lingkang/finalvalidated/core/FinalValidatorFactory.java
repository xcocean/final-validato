package top.lingkang.finalvalidated.core;

import top.lingkang.finalvalidated.constraints.Tag;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.handle.NestingValidHandle;
import top.lingkang.finalvalidated.handle.ValidHandle;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author lingkang
 * created by 2024/1/26
 */
public class FinalValidatorFactory {
    private static final Map<Class<?>, CheckObject> cache = new HashMap<>();
    private static final List<Class<?>> currentNestingClass = new ArrayList<>();
    public static final Properties message = new Properties();

    public boolean supports(Class<?> clazz) {
        CheckObject check = cache.get(clazz);
        if (check != null)
            return check.isNeed();

        check = new CheckObject();
        // 判断是否需要校验入参对象
        if (FinalValidatorUtils.existsConstraints(clazz)) {
            // 需要校验并初始化校验
            check.setNeed(true);
            check.setHandles(initValidHandle(clazz));
        }
        cache.put(clazz, check);
        return check.isNeed();
    }

    public void validate(Object target) {
        CheckObject checkObject = cache.get(target.getClass());
        try {
            for (ValidHandle handle : checkObject.getHandles()) {
                handle.valid(target);
            }
        } catch (ValidatedException e) {
            if (!e.getMessage().startsWith("{")) {
                String[] split = e.getMessage().split(" ", 2);
                e.setMessage("{\"" + split[0] + "\":\"" + split[1] + "\"}");
            }
            throw e;
        }
    }

    private List<ValidHandle> initValidHandle(Class<?> clazz) {
        List<ValidHandle> list = new ArrayList<>();
        List<Field> fieldList = FinalValidatorUtils.getAllFields(clazz);
        for (Field field : fieldList) {
            // 判断是否是对象嵌套对象
            if (field.getType() != clazz && !currentNestingClass.contains(field.getType())) {
                currentNestingClass.add(field.getType());// 防止无限套娃
                List<Field> checkField = FinalValidatorUtils.getAllCheckField(field.getType());
                if (!checkField.isEmpty()) {
                    field.setAccessible(true);
                    list.add(new NestingValidHandle(initValidHandle(field.getType()), field));
                    continue;
                }
            }

            List<Annotation> validAnn = FinalValidatorUtils.getValidAnn(field);
            if (validAnn.isEmpty())
                continue;
            for (Annotation annotation : validAnn) {
                try {
                    String strTag = null;
                    Tag tag = field.getAnnotation(Tag.class);
                    if (tag != null && FinalValidatorUtils.isNotEmpty(tag.value())) {
                        strTag = tag.value();
                    }
                    list.add(FinalValidatorUtils.annotationToValidHandle(field, annotation, strTag));
                } catch (CheckException e) {
                    throw new CheckException("校验异常对象：" + clazz.getName(), e);
                }
            }
        }
        currentNestingClass.clear();// 清空
        return list;
    }

    /**
     * 清理缓存，注意：清理后，您添加的自定义注解也被清除，需要重新添加。
     * @since 2.3.0
     */
    public void clearCache() {
        cache.clear();
        currentNestingClass.clear();
        FinalValidatorUtils.clearCache();
    }
}
