package test;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class TestString extends BaseTest{
    @Test
    public void test01(){
        String result = get("/string?a=11111111&b=asfsda&c=1");
        Assert.isTrue(result.contains("a"));
        Assert.isTrue(result.contains("0"));
        Assert.isTrue(result.contains("5"));
        Assert.isTrue(result.contains("字符长度范围"));

        result = get("/string?a=asd&b=asfsda&c=1");
        Assert.isTrue(result.contains("d"));

        result = get("/string?a=asd&b=asfsda&c=1&d=a");
        Assert.isTrue(result.contains("a"));
        Assert.isTrue(result.contains("b"));
        Assert.isTrue(result.contains("c"));
        Assert.isTrue(result.contains("d"));
    }
}
