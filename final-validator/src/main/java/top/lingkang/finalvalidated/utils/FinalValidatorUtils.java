package top.lingkang.finalvalidated.utils;

import top.lingkang.finalvalidated.constraints.*;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.handle.*;

import java.io.Closeable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class FinalValidatorUtils {
    private static final Set<Class<? extends Annotation>> checkAnnotation = new HashSet<>();
    private static final Map<Class<? extends Annotation>, Class<? extends CustomValidHandle>> customHandle = new HashMap<>();
    private static final Map<Class<?>, List<Field>> checkFieldCache = new HashMap<>();
    private static final Map<Field, List<Annotation>> checkAnnotationCache = new HashMap<>();

    static {
        checkAnnotation.add(NotBlank.class);
        checkAnnotation.add(Min.class);
        checkAnnotation.add(Max.class);
        checkAnnotation.add(Length.class);
        checkAnnotation.add(NotEmpty.class);
        checkAnnotation.add(Email.class);
        checkAnnotation.add(Pattern.class);
        checkAnnotation.add(Null.class);
        checkAnnotation.add(NotNull.class);
        checkAnnotation.add(AssertFalse.class);
        checkAnnotation.add(AssertTrue.class);
    }

    /**
     * 添加自定义注解和处理
     *
     * @param annotation  自定义的注解
     * @param validHandle 自定义注解的校验处理
     */
    public static void addCustom(Class<? extends Annotation> annotation, Class<? extends CustomValidHandle> validHandle) {
        if (FinalValidatorUtils.checkAnnotation.contains(annotation))
            throw new CheckException("不能重复添加自定义注解：" + annotation.getName());
        FinalValidatorUtils.checkAnnotation.add(annotation);
        customHandle.put(annotation, validHandle);
    }

    /**
     * 判断属性是否存在约束注解
     */
    public static boolean existsConstraints(Class<?> clazz) {
        List<Field> list = getAllCheckField(clazz);
        return !list.isEmpty();
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> list = checkFieldCache.get(clazz);
        if (list != null)
            return list;
        list = new ArrayList<>();
        HashSet<String> names = new HashSet<>();

        while (clazz != null && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (names.contains(field.getName()))// 不会检查父级的相同属性
                    continue;
                names.add(field.getName());
                list.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        checkFieldCache.put(clazz, list);
        return list;
    }

    public static List<Field> getAllCheckField(Class<?> clazz) {
        List<Field> list = checkFieldCache.get(clazz);
        if (list != null)
            return list;
        list = new ArrayList<>();
        HashSet<String> names = new HashSet<>();

        while (clazz != null && clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                List<Annotation> validAnn = getValidAnn(field);
                if (validAnn.isEmpty())// 不存在检查注解的跳过
                    continue;
                if (names.contains(field.getName()))// 不会检查父级的相同属性
                    continue;
                names.add(field.getName());
                list.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        checkFieldCache.put(clazz, list);
        return list;
    }

    /**
     * 判断注解是否存在约束注解
     */
    public static List<Annotation> getValidAnn(Field field) {
        List<Annotation> list = checkAnnotationCache.get(field);
        if (list == null) {
            list = new ArrayList<>();
        } else
            return list;
        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            if (checkAnnotation.contains(annotation.annotationType()))
                list.add(annotation);
        }
        checkAnnotationCache.put(field, list);
        return list;
    }

    /// ----------------------------------------------------------------------------------------------

    /**
     * @param field      对象属性
     * @param annotation 注解
     */
    public static ValidHandle annotationToValidHandle(Field field, Annotation annotation, String tag) {
        if (annotation.annotationType() == NotBlank.class) {
            NotBlank notBlank = (NotBlank) annotation;
            return new NotBlankHandle(field, notBlank.message(), tag == null ? notBlank.tag() : tag);
        } else if (annotation.annotationType() == Min.class) {
            Min min = (Min) annotation;
            return new MinHandle(field, min.message(), tag == null ? min.tag() : tag, min.value());
        } else if (annotation.annotationType() == Max.class) {
            Max max = (Max) annotation;
            return new MaxHandle(field, max.message(), tag == null ? max.tag() : tag, max.value());
        } else if (annotation.annotationType() == Length.class) {
            Length length = (Length) annotation;
            return new LengthHandle(field, length.message(), tag == null ? length.tag() : tag, length.min(), length.max());
        } else if (annotation.annotationType() == NotEmpty.class) {
            NotEmpty notEmpty = (NotEmpty) annotation;
            return new NotEmptyHandle(field, notEmpty.message(), tag == null ? notEmpty.tag() : tag);
        } else if (annotation.annotationType() == Email.class) {
            Email email = (Email) annotation;
            return new EmailHandle(field, email.message(), tag == null ? email.tag() : tag, email.value());
        } else if (annotation.annotationType() == Pattern.class) {
            Pattern pattern = (Pattern) annotation;
            return new PatternHandle(field, pattern.message(), tag == null ? pattern.tag() : tag, pattern.value());
        } else if (annotation.annotationType() == Null.class) {
            Null aNull = (Null) annotation;
            return new NullHandle(field, aNull.message(), tag == null ? aNull.tag() : tag);
        } else if (annotation.annotationType() == NotNull.class) {
            NotNull notNull = (NotNull) annotation;
            return new NotNullHandle(field, notNull.message(), tag == null ? notNull.tag() : tag);
        } else if (annotation.annotationType() == AssertFalse.class) {
            AssertFalse assertFalse = (AssertFalse) annotation;
            return new AssertFalseHandle(field, assertFalse.message(), tag == null ? assertFalse.tag() : tag);
        } else if (annotation.annotationType() == AssertTrue.class) {
            AssertTrue assertTrue = (AssertTrue) annotation;
            return new AssertTrueHandle(field, assertTrue.message(), tag == null ? assertTrue.tag() : tag);
        }

        // 可能是自定义的
        Class<? extends CustomValidHandle> validHandle = customHandle.get(annotation.annotationType());
        if (validHandle != null) {
            try {
                return validHandle.getConstructor(Field.class, Annotation.class)
                        .newInstance(field, annotation);
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

    // ----------------------------------------------------------------------------------------------------------

    public static boolean isNotEmpty(CharSequence str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isBlank(CharSequence str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; ++i) {
                if (!isBlankChar(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == 65279 || c == 8234 || c == 0 || c == 12644 || c == 10240 || c == 6158;
    }

    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
        }
    }

    /**
     * 清理缓存，注意：清理后，您添加的自定义注解也被清除，需要重新添加。
     * @since 2.3.0
     */
    public static void clearCache() {
        if (!customHandle.isEmpty()){
            Set<Class<? extends Annotation>> set = customHandle.keySet();
            for (Class<? extends Annotation> aClass : set) {
                checkAnnotation.remove(aClass);
            }
        }
        checkFieldCache.clear();
        checkAnnotationCache.clear();
        customHandle.clear();
    }

}
