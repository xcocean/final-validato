package top.lingkang.fv.solon.test;

import org.noear.solon.Solon;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import top.lingkang.finalvalidated.core.ValidObject;
import top.lingkang.fv.solon.ValidParams;
import top.lingkang.fv.solon.test.param.LoginParam;

/**
 * @author lingkang
 * Created by 2024/3/5
 */
@ValidParams
@Controller
public class TestApp {
    public static void main(String[] args) {
        Solon.start(TestApp.class, args);
    }

    @Mapping("/")
    public Object index(@ValidObject LoginParam loginParam) {
        return "ok: " + loginParam;
    }

    @Mapping("/2")
    public Object index2() {
        return "ok: ";
    }
}
