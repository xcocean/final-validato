package top.lingkang.finalvalidated.utils;

import top.lingkang.finalvalidated.constraints.*;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.handle.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class FinalValidatorUtils {
    private static final Set<Class<? extends Annotation>> annotation = new HashSet<>();
    private static final Map<Class<? extends Annotation>, Class<? extends CustomValidHandle>> customHandle = new HashMap<>();

    static {
        annotation.add(NotBlank.class);
        annotation.add(Min.class);
        annotation.add(Max.class);
        annotation.add(Length.class);
        annotation.add(NotEmpty.class);
        annotation.add(Email.class);
        annotation.add(Pattern.class);
        annotation.add(Null.class);
        annotation.add(NotNull.class);
        annotation.add(AssertFalse.class);
        annotation.add(AssertTrue.class);
    }

    /**
     * 添加自定义注解和处理
     *
     * @param annotation  自定义的注解
     * @param validHandle 自定义注解的校验处理
     */
    public static void addCustom(Class<? extends Annotation> annotation, Class<? extends CustomValidHandle> validHandle) {
        if (FinalValidatorUtils.annotation.contains(annotation))
            throw new CheckException("不能重复添加自定义注解：" + annotation.getName());
        FinalValidatorUtils.annotation.add(annotation);
        customHandle.put(annotation, validHandle);
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
            if (FinalValidatorUtils.annotation.contains(annotation.annotationType()))
                return true;
        }
        return false;
    }

    public static boolean annotationConstraints(Annotation annotation) {
        return FinalValidatorUtils.annotation.contains(annotation.annotationType());
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
        } else if (annotation.annotationType() == Email.class) {
            Email email = (Email) annotation;
            return new EmailHandle(name, email.message(), email.tag(), email.value());
        } else if (annotation.annotationType() == Pattern.class) {
            Pattern pattern = (Pattern) annotation;
            return new PatternHandle(name, pattern.message(), pattern.tag(), pattern.value());
        } else if (annotation.annotationType() == Null.class) {
            Null aNull = (Null) annotation;
            return new NullHandle(name, aNull.message(), aNull.tag());
        } else if (annotation.annotationType() == NotNull.class) {
            NotNull notNull = (NotNull) annotation;
            return new NotNullHandle(name, notNull.message(), notNull.tag());
        } else if (annotation.annotationType() == AssertFalse.class) {
            AssertFalse assertFalse = (AssertFalse) annotation;
            return new AssertFalseHandle(name, assertFalse.message(), assertFalse.tag());
        } else if (annotation.annotationType() == AssertTrue.class) {
            AssertTrue assertTrue = (AssertTrue) annotation;
            return new AssertTrueHandle(name, assertTrue.message(), assertTrue.tag());
        }

        // 可能是自定义的
        Class<? extends CustomValidHandle> validHandle = customHandle.get(annotation.annotationType());
        if (validHandle != null) {
            try {
                return validHandle.getConstructor(String.class, Annotation.class).newInstance(name, annotation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new CheckException(annotation.annotationType() + " 未找到对应的 ValidHandle 处理类实现！");
        }

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
