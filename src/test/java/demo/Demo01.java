package demo;

import app.param.LoginParam;
import top.lingkang.finalvalidated.constraints.NotBlank;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class Demo01 {
    public static void main(String[] args) {
        Field[] fields = LoginParam.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(is(field.getAnnotations()));
        }
    }

    private static boolean is(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == NotBlank.class)
                return true;
        }
        return false;
    }
}
