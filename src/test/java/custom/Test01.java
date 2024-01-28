package custom;

import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class Test01 {
    public static void main(String[] args) {
        FinalValidator.init();
        FinalValidator.addCustom(TestCustom.class,TestCustomValidHandle.class);

        TestParam param=new TestParam();
        param.setA("aaaaaaa");
        FinalValidator.valid(param);
    }
}
