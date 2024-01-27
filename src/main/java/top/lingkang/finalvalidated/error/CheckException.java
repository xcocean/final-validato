package top.lingkang.finalvalidated.error;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class CheckException extends RuntimeException{
    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }
}
