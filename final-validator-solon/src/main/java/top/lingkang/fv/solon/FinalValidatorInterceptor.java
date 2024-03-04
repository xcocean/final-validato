package top.lingkang.fv.solon;

import org.noear.solon.core.aspect.Interceptor;
import org.noear.solon.core.aspect.Invocation;
import org.noear.solon.core.wrap.ParamWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lingkang.finalvalidated.core.FinalValidator;
import top.lingkang.finalvalidated.core.ValidObject;

/**
 * @author lingkang
 * Created by 2024/3/5
 */
public class FinalValidatorInterceptor implements Interceptor {
    private static final Logger log = LoggerFactory.getLogger(FinalValidatorInterceptor.class);

    public FinalValidatorInterceptor() {
        // 初始化
        FinalValidator.init();
        log.info("初始化 final-validator 完成");
    }

    @Override
    public Object doIntercept(Invocation inv) throws Throwable {
        ParamWrap[] paramWraps = inv.method().getParamWraps();
        if (paramWraps.length > 0) {
            Object[] args = inv.args();
            for (int i = 0; i < paramWraps.length; i++) {
                if (paramWraps[i].getParameter().isAnnotationPresent(ValidObject.class)) {
                    // 可能需要校验
                    FinalValidator.valid(args[i]);
                }
            }
        }
        return inv.invoke();
    }

    private String getId(Invocation inv) {
        return inv.getTargetClz().getName() + "." + inv.method().getMethod().getName();
    }
}
