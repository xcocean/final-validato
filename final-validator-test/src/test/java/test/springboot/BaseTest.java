package test.springboot;

import cn.hutool.http.HttpUtil;

import java.util.Map;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class BaseTest {
    private String url = "http://127.0.0.1:8080";

    protected String get(String path) {
        return get(path, null);
    }

    protected String get(String path, Map<String, Object> paramMap) {
        return HttpUtil.get(url + path, paramMap, 1000);
    }
}
