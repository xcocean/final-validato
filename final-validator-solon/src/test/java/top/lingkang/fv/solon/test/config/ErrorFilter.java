package top.lingkang.fv.solon.test.config;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lingkang.finalvalidated.error.ValidatedException;

/**
 * @author lingkang
 * Created by 2024/3/5
 */
@Component
public class ErrorFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            chain.doFilter(ctx);
        } catch (ValidatedException e) {
            log.warn(e.getMessage());
            ctx.outputAsHtml(e.getMessage());
        }
    }
}
