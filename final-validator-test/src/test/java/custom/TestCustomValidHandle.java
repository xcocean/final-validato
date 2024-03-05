package custom;

import top.lingkang.finalvalidated.handle.CustomValidHandle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class TestCustomValidHandle extends CustomValidHandle {

    public TestCustomValidHandle(Field field, Annotation annotation) {
        super(field, annotation);
        if (!Modifier.isPublic(field.getModifiers()))
            field.setAccessible(true);
    }

    @Override
    public void valid(Object target) {
        System.out.println(target);
        System.out.println(field.getName());
        System.out.println(annotation);
        try {
            System.out.println(field.get(target));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
