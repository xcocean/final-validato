package top.lingkang.fv.solon;

import org.noear.solon.annotation.Controller;
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
        FinalValidatorInterceptor interceptor = new FinalValidatorInterceptor();
        context.beanInterceptorAdd(ValidParams.class, interceptor, 666);
        context.beanInterceptorAdd(Controller.class, interceptor, 667);
    }
}
