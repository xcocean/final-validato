package top.lingkang.finalvalidated.error;

/**
 * @author lingkang
 * Created by 2024/1/26
 * 框架校验异常
 */
public class ValidatedException extends RuntimeException{
    private String objectName;
    private String filedName;

    public ValidatedException(String message, String objectName, String filedName) {
        super(message);
        this.objectName = objectName;
        this.filedName = filedName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public ValidatedException(String message) {
        super(message);
    }

    public ValidatedException(Throwable cause) {
        super(cause);
    }
}
