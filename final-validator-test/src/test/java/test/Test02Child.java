package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import param.ChildLoginParam;
import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * @author lingkang
 * Create by 2024/12/9 21:44
 */
public class Test02Child {
    @BeforeEach
    public void init() {
        FinalValidator.init();
    }

    @Test
    public void test01() {
        ChildLoginParam param = new ChildLoginParam();
        param.setUsername("lk1234");
        param.setPassword("199512");
        FinalValidator.valid(param);
    }
}
