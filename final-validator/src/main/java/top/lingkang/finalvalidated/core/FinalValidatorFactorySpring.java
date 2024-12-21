package top.lingkang.finalvalidated.core;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import top.lingkang.finalvalidated.error.CheckException;

/**
 * @author lingkang
 * created by 2024/1/29
 * 此类用于折中调用 FinalValidatorFactory 。让其能够脱离spring依赖使用。
 */
public class FinalValidatorFactorySpring implements Validator {
    public FinalValidatorFactory finalValidatorFactory;

    public FinalValidatorFactorySpring(FinalValidatorFactory finalValidatorFactory) {
        this.finalValidatorFactory = finalValidatorFactory;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return finalValidatorFactory.supports(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 若已经积累了异常，将其抛出
        if (errors.hasErrors()) {
            throw new CheckException("校验参数前存在mvc解析异常" + errors)
                    .setErrorObject(errors);
        }
        // 校验
        finalValidatorFactory.validate(target);
    }

    /**
     * 清理缓存，注意：清理后，您添加的自定义注解也被清除，需要重新添加。
     *
     * @since 2.3.0
     */
    public void clearCache() {
        finalValidatorFactory.clearCache();
    }
}
