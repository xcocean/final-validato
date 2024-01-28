package test;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class TestEmail extends BaseTest{
    @Test
    public void test01(){
        String result = get("/email?a=123@1.a&b=asd");
        Assert.isTrue(result.contains("不是邮箱"));

        result = get("/email?a=123@1.a&b=asd@1");
        Assert.isTrue(result.contains("不是邮箱"));

        result = get("/email?a=123@1.a&b=asd@lingkang.top");
        Assert.isTrue(result.contains("asd@lingkang.top"));

        result = get("/email?a=lk_acg@1.a&b=asd@lingkang.top");
        Assert.isTrue(result.contains("asd@lingkang.top"));

        result = get("/email?a=lk_acg@1.a&b=asd@lingkang.top");
        Assert.isTrue(result.contains("asd@lingkang.top"));

        result = get("/email?a=lk.acg@1.a&b=asd@lingkang.top");
        Assert.isTrue(result.contains("asd@lingkang.top"));

        result = get("/email?a=lk.acg@1111111111111111.a&b=asd@lingkang.top");
        Assert.isTrue(result.contains("范围"));
    }
}
