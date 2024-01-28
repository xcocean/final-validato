package test;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class TestNotNull extends BaseTest {
    @Test
    public void test01() {
        String result = get("/notNull?b=1&a=asd");
        Assert.isTrue(result.contains("必须为空"));
        Assert.isTrue(result.contains("b"));

        result = get("/notNull?a=asd");
        Assert.isTrue(result.contains("asd"));
    }
}
