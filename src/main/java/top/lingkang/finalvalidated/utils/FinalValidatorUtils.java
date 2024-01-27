package top.lingkang.finalvalidated.utils;

import top.lingkang.finalvalidated.constraints.*;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.handle.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class FinalValidatorUtils {
    private static final Set<Class<? extends Annotation>> ann = new HashSet<>();

    static {
        ann.add(NotBlank.class);
        ann.add(Min.class);
        ann.add(Max.class);
        ann.add(Length.class);
        ann.add(NotEmpty.class);
    }

    /**
     * 判断属性是否存在约束注解
     */
    public static boolean existsConstraints(Field[] fields) {
        for (Field field : fields) {
            if (existsConstraints(field.getAnnotations()))
                return true;
        }
        return false;
    }

    /**
     * 判断注解是否存在约束注解
     */
    public static boolean existsConstraints(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (ann.contains(annotation.annotationType()))
                return true;
        }
        return false;
    }

    public static boolean annotationConstraints(Annotation annotation) {
        return ann.contains(annotation.annotationType());
    }


    /// ----------------------------------------------------------------------------------------------

    /**
     * @param name       对象属性名称
     * @param annotation 注解
     */
    public static ValidHandle annotationToValidHandle(String name, Annotation annotation) {
        if (annotation.annotationType() == NotBlank.class) {
            NotBlank notBlank = (NotBlank) annotation;
            return new NotBlankHandle(name, notBlank.message(), notBlank.tag());
        } else if (annotation.annotationType() == Min.class) {
            Min min = (Min) annotation;
            return new MinHandle(name, min.message(), min.tag(), min.value());
        } else if (annotation.annotationType() == Max.class) {
            Max max = (Max) annotation;
            return new MaxHandle(name, max.message(), max.tag(), max.value());
        } else if (annotation.annotationType() == Length.class) {
            Length length = (Length) annotation;
            return new LengthHandle(name, length.message(), length.tag(), length.min(), length.max());
        } else if (annotation.annotationType() == NotEmpty.class) {
            NotEmpty notEmpty = (NotEmpty) annotation;
            return new NotEmptyHandle(name, notEmpty.message(), notEmpty.tag());
        }

        // throw new ValidatedException("未支持的校验注解：" + annotation.annotationType());
        return null;
    }

    public static boolean notMin(Object o, long value) {
        if (o instanceof Integer) {
            return Integer.parseInt(o.toString()) < value;
        } else if (o instanceof Long) {
            return Long.parseLong(o.toString()) < value;
        } else if (o instanceof BigDecimal) {
            return ((BigDecimal) o).compareTo(new BigDecimal(value)) < 0;
        } else if (o instanceof Float) {
            return ((Float) o) < Float.parseFloat(String.valueOf(value));
        } else if (o instanceof Double) {
            return ((Double) o) < Double.parseDouble(String.valueOf(value));
        } else if (o instanceof Short) {
            return ((Short) o) < value;
        }
        throw new CheckException("long 无法与 " + o.getClass() + " 进行类型比较");
    }

    public static boolean notMax(Object o, long value) {
        if (o instanceof Integer) {
            return Integer.parseInt(o.toString()) > value;
        } else if (o instanceof Long) {
            return Long.parseLong(o.toString()) > value;
        } else if (o instanceof BigDecimal) {
            return ((BigDecimal) o).compareTo(new BigDecimal(value)) > 0;
        } else if (o instanceof Float) {
            return ((Float) o) > Float.parseFloat(String.valueOf(value));
        } else if (o instanceof Double) {
            return ((Double) o) > Double.parseDouble(String.valueOf(value));
        } else if (o instanceof Short) {
            return ((Short) o) > value;
        }
        throw new CheckException("long 无法与 " + o.getClass() + " 进行类型比较");
    }


}
