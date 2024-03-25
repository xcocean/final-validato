package test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import param.NumberParam;
import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * @author lingkang
 * @create by 2024/3/25 14:30
 */
@Slf4j
public class Test02Number {
    @BeforeEach
    public void init() {
        FinalValidator.init();
    }

    @Test
    public void test01(){
        NumberParam param=new NumberParam();
        param.setMin(1);
        param.setMax(1);
        FinalValidator.valid(param);
    }
}
