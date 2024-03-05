package top.lingkang.finalvalidated.core;

import cn.hutool.core.util.StrUtil;
import top.lingkang.finalvalidated.constraints.Tag;
import top.lingkang.finalvalidated.error.CheckException;
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
    public static final Properties message = new Properties();

    public boolean supports(Class<?> clazz) {
        CheckObject check = cache.get(clazz);
        if (check != null)
            return check.isNeed();

        check = new CheckObject();
        // 判断是否需要校验入参对象
        if (FinalValidatorUtils.existsConstraints(clazz.getDeclaredFields())) {
            // 需要校验并初始化校验
            check.setNeed(true);
            check.setHandles(initValidHandle(clazz));
        }
        cache.put(clazz, check);
        return check.isNeed();
    }

    public void validate(Object target) {
        CheckObject checkObject = cache.get(target.getClass());
        for (ValidHandle handle : checkObject.getHandles()) {
            handle.valid(target);
        }
    }

    private List<ValidHandle> initValidHandle(Class<?> clazz) {
        List<ValidHandle> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (FinalValidatorUtils.annotationConstraints(annotation)) {
                    try {
                        String strTag = null;
                        Tag tag = field.getAnnotation(Tag.class);
                        if (tag != null && StrUtil.isNotEmpty(tag.value())) {
                            strTag = tag.value();
                        }
                        list.add(FinalValidatorUtils.annotationToValidHandle(field, annotation, strTag));
                    } catch (CheckException e) {
                        throw new CheckException("校验异常对象：" + clazz.getName(), e);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 清理缓存
     */
    public void clearCache(){
        cache.clear();
    }
}
