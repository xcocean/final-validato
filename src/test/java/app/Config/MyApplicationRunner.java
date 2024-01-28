package app.Config;

import custom.TestCustom;
import custom.TestCustomValidHandle;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    // @Autowired
    // private FinalValidator finalValidator;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 直接添加自定义注册，无须初始化FinalValidator，因为spring已经帮我们初始化了
        FinalValidator.addCustom(TestCustom.class, TestCustomValidHandle.class);

        // 或者使用bean对象添加
        // finalValidator.addCustom(TestCustom.class, TestCustomValidHandle.class);
    }
}
