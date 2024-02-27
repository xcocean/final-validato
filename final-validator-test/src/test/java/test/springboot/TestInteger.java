package test.springboot;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class TestInteger extends BaseTest{

    @Test
    public void test01(){
        String result = get("/integer?a=1");
        Assert.isTrue(result.contains("5"));
        Assert.isTrue(result.contains("a"));

        result = get("/integer?a=aa");
        Assert.isTrue(result.contains("5"));
        Assert.isTrue(result.contains("a"));

        result = get("/integer?a=99&b=-1");
        Assert.isTrue(result.contains("b"));
        Assert.isTrue(result.contains("1"));

        result = get("/integer?a=1001");
        Assert.isTrue(result.contains("100"));
        Assert.isTrue(result.contains("a"));

        result = get("/integer?a=99&b=asd");
        Assert.isTrue(result.contains("b"));
        Assert.isTrue(result.contains("1"));

        result = get("/integer?a=11&b=");
        Assert.isTrue(result.contains("b"));
        Assert.isTrue(result.contains("1"));

        result = get("/integer?a=5&b=555&c=1");
        Assert.isTrue(result.contains("b"));
        Assert.isTrue(result.contains("a"));
        Assert.isTrue(result.contains("c"));
    }
}
