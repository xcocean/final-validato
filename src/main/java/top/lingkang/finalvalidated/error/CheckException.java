package top.lingkang.finalvalidated.error;

/**
 * @author lingkang
 * Created by 2024/1/27
 * 框架检查异常
 */
public class CheckException extends RuntimeException {
    // 异常对象，通常用于记录解析前的异常
    private Object errorObject;

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
        errorObject = cause;
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public Object getErrorObject() {
        return errorObject;
    }

    public CheckException setErrorObject(Object errorObject) {
        this.errorObject = errorObject;
        return this;
    }
}
