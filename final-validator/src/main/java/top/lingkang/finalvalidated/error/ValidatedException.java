package top.lingkang.finalvalidated.error;

/**
 * @author lingkang
 * Created by 2024/1/26
 * 框架校验异常
 */
public class ValidatedException extends RuntimeException {
    private Class<?> paramClass;
    private String filedName;
    private String message;

    public ValidatedException(String message, Class<?> paramClass, String filedName) {
        this.paramClass = paramClass;
        this.filedName = filedName;
        this.message = message;
    }

    public ValidatedException(String message) {
        this.message = message;
    }

    public Class<?> getParamClass() {
        return paramClass;
    }

    public void setParamClass(Class<?> paramClass) {
        this.paramClass = paramClass;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
