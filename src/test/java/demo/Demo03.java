package demo;

import app.param.LoginParam;
import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class Demo03 {
    public static void main(String[] args) {
        LoginParam param=new LoginParam();
        param.setUsername("admin");
        FinalValidator.init();
        FinalValidator.valid(param);
    }
}
