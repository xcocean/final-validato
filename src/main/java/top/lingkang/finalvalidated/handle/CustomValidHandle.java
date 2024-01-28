package top.lingkang.finalvalidated.handle;

import java.lang.annotation.Annotation;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class CustomValidHandle implements ValidHandle {

    protected String name;
    protected Annotation annotation;

    public CustomValidHandle(String name, Annotation annotation) {
        this.annotation = annotation;
        this.name = name;
    }

    @Override
    public void valid(Object target) {

    }
}
