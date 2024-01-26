package top.lingkang.finalvalidated.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lingkang
 * created by 2024/1/26
 */
@Configuration
public class ParamValidator implements Validator {
    private static final Map<Class,Object> cache=new HashMap<>();

    @Override
    public boolean supports(Class<?> clazz) {
        Object o = cache.get(clazz);
        if (o!=null)
            return true;
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {


    }
}
