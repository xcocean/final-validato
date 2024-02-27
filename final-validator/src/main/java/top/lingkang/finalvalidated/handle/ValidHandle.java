package top.lingkang.finalvalidated.handle;

/**
 * @author lingkang
 * Created by 2024/1/26
 * 校验处理实现
 */
public interface ValidHandle {

    /**
     * 校验，自定义校验时，校验失败建议抛出 {@link top.lingkang.finalvalidated.error.ValidatedException} 异常
     *
     * @param target 待校验对象
     */
    void valid(Object target);
}
