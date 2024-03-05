package top.lingkang.fv.solon.test.config;

import org.noear.solon.annotation.Component;
import top.lingkang.finalvalidated.core.ValidObject;
import top.lingkang.fv.solon.ValidParams;
import top.lingkang.fv.solon.test.param.LoginParam;

/**
 * @author lingkang
 * @create by 2024/3/5 10:59
 */
@ValidParams// 在此bean中开启入参校验
@Component
public class MyBean {
    // 必须添加 @ValidObject 注解才会校验这个入参
    public Object myMethod(@ValidObject LoginParam param){
        return param.toString();
    }
}
