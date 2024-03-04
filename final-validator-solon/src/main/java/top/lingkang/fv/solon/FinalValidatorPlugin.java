package top.lingkang.fv.solon;

import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * final-validator 插件初始化
 *
 * @author lingkang
 * Created by 2024/3/4
 */
public class FinalValidatorPlugin implements Plugin {
    @Override
    public void start(AppContext context) throws Throwable {
        context.beanInterceptorAdd(ValidParams.class, new FinalValidatorInterceptor(), 666);
    }
}
