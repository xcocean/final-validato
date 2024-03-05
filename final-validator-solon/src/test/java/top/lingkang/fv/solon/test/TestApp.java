package top.lingkang.fv.solon.test;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import top.lingkang.finalvalidated.core.ValidObject;
import top.lingkang.fv.solon.test.config.MyBean;
import top.lingkang.fv.solon.test.param.LoginParam;

/**
 * @author lingkang
 * Created by 2024/3/5
 */
@Controller
public class TestApp {
    @Mapping("/login")
    public Object login(@ValidObject LoginParam loginParam) {
        return "ok: " + loginParam;
    }

    @Inject
    private MyBean myBean;

    @Mapping("/")
    public Object index() {
        LoginParam param = new LoginParam();
        param.setUsername("final-validator");
        Object object = myBean.myMethod(param);
        return object;
    }

    public static void main(String[] args) {
        Solon.start(TestApp.class, args);
    }
}
