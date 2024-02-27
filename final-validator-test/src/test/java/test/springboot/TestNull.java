package test.springboot;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class TestNull extends BaseTest {
    @Test
    public void test01() {
        String result = get("/null?b=1");
        Assert.isTrue(result.contains("1"));

        result = get("/null?b=1&a=asd");
        Assert.isTrue(result.contains("必须为空"));
        Assert.isTrue(result.contains("a"));
    }
}
