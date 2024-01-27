package test;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class TestBigDecimal extends BaseTest{
    @Test
    public void test01(){
        String result = get("/bigDecimal?price=1");
        Assert.isTrue(result.contains("price"));
        Assert.isTrue(result.contains("1"));

        result = get("/bigDecimal?price=1&a=0.1");
        Assert.isTrue(result.contains("1"));
        Assert.isTrue(result.contains("0.1"));

        result = get("/bigDecimal?price=1&a=0.001");
        Assert.isFalse(result.contains("price"));
        Assert.isFalse(result.contains("."));

        result = get("/bigDecimal?price=1&a=0.1");
        Assert.isTrue(result.contains("1"));
        Assert.isTrue(result.contains("0.1"));

        result = get("/bigDecimal?price=1&a=0.1&b=1536.0111");
        Assert.isTrue(result.contains("1"));
        Assert.isTrue(result.contains("0.1"));
        Assert.isTrue(result.contains("0111"));


    }
}
