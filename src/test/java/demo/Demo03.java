package demo;

import app.param.LoginParam;
import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class Demo03 {
    public static void main(String[] args) {
        // 初始化校验，只需初始化一次即可
        FinalValidator.init();

        LoginParam param=new LoginParam();
        param.setUsername("admin");

        // 校验，失败时会通过 ValidatedException 异常抛出，能够通过 ValidatedException 获取到 字段名称、对象名称、错误信息等。
        FinalValidator.valid(param);
    }
}
