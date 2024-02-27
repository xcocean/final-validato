package test.springboot;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class TestAssertTrueOrFalse extends BaseTest {
    @Test
    public void testFalse() {
        String result = get("/assertFalse?a=false");
        Assert.isTrue(result.contains("false"));

        result = get("/assertFalse?b=1&a=asd");
        Assert.isTrue(result.contains("false"));

        result = get("/assertFalse?b=1&a=asd");
        Assert.isTrue(result.contains("false"));

        result = get("/assertFalse?a=false&b=false&c=false");
        Assert.isTrue(result.contains("a"));
        Assert.isTrue(result.contains("b"));
        Assert.isTrue(result.contains("c"));
    }

    @Test
    public void testTrue() {
        String result = get("/assertTrue?a=true");
        Assert.isTrue(result.contains("true"));

        result = get("/assertTrue?b=1&a=asd");
        Assert.isTrue(result.contains("true"));

        result = get("/assertTrue?b=1&a=asd");
        Assert.isTrue(result.contains("true"));

        result = get("/assertTrue?a=true&b=true&c=true");
        Assert.isTrue(result.contains("a"));
        Assert.isTrue(result.contains("b"));
        Assert.isTrue(result.contains("c"));
    }
}
