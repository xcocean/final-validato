package app.controller;

import app.param.*;
import custom.TestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lingkang.finalvalidated.core.FinalValidator;
import top.lingkang.finalvalidated.core.ValidObject;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
@RestController
public class ApiController {
    @RequestMapping("/login")
    public Object login(@ValidObject LoginParam param) {
        return param;
    }

    @RequestMapping("/integer")
    public Object integer(@ValidObject IntegerParam param) {
        return param;
    }

    @RequestMapping("/string")
    public Object string(@ValidObject StringParam param) {
        return param;
    }

    @RequestMapping("/bigDecimal")
    public Object string(@ValidObject BigDecimalParam param) {
        return param;
    }

    @RequestMapping("/email")
    public Object email(@ValidObject EmailParam param) {
        return param;
    }

    @RequestMapping("/pattern")
    public Object pattern(@ValidObject PatternParam param) {
        return param;
    }

    @RequestMapping("/custom")
    public Object custom(TestParam param) {
        FinalValidator.valid(param);
        return param;
    }

    @RequestMapping("/null")
    public Object aNull(@ValidObject NullParam param) {
        return param;
    }

    @RequestMapping("/notNull")
    public Object notNull(@ValidObject NotNullParam param) {
        return param;
    }

    @RequestMapping("/assertFalse")
    public Object assertFalse(@ValidObject AssertFalseParam param) {
        return param;
    }

    @RequestMapping("/assertTrue")
    public Object assertTrue(@ValidObject AssertTrueParam param) {
        return param;
    }
}
