package web.springboot.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    // 使用spring自带的 json格式化 Jackson库
    private final ObjectMapper mapper = new ObjectMapper();
    /**
     * 捕获校验异常，返回rest结果
     */
    @ExceptionHandler(ValidatedException.class)
    public Object v(ValidatedException e) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", mapper.readValue(e.getMessage(), Map.class));
        // map.put("msg", mapper.readValue(e.getMessage(), Map.class)); // json格式化校验失败的结果
        // map.put("object", e.getObjectName());
        // map.put("filed", e.getFiledName());
        return map;
    }
}
