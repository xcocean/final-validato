package app.Config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.lingkang.finalvalidated.error.ValidatedException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
@RestControllerAdvice
public class ExceptionConfig {

    /**
     * 捕获校验异常，返回rest结果
     */
    @ExceptionHandler(ValidatedException.class)
    public Object v(ValidatedException e) {
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg",e.getMessage());
        return map;
    }
}
