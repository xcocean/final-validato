package top.lingkang.fv.solon;

import java.lang.annotation.*;

/**
 * 开启当前类的入参校验，这个类通常是controller。<br/>
 * 对于要校验的入参，添加 {@link top.lingkang.finalvalidated.core.ValidObject} 即可校验<br/>
 *
 * @author lingkang
 * Created by 2024/3/5
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidParams {
}
