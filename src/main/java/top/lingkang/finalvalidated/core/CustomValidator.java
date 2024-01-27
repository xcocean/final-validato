package top.lingkang.finalvalidated.core;

import org.springframework.validation.Errors;

/**
 * @author lingkang
 * created by 2024/1/26
 */
public interface CustomValidator {
    /**
     * 是否支持校验
     *
     * @param clazz 是否需要校验类
     * @return 是否校验
     */
    boolean supports(Class<?> clazz);

    /**
     * 校验此次入参
     *
     * @param target 入参
     * @param errors 异常
     */
    void validate(Object target, Errors errors);
}