package custom;

import top.lingkang.finalvalidated.handle.CustomValidHandle;

import java.lang.annotation.Annotation;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class TestCustomValidHandle extends CustomValidHandle {
    public TestCustomValidHandle(String name, Annotation annotation) {
        super(name, annotation);
    }

    @Override
    public void valid(Object target) {
        System.out.println(target);
        System.out.println(name);
        System.out.println(annotation);
    }
}
