package app.controller;

import app.param.BigDecimalParam;
import app.param.IntegerParam;
import app.param.LoginParam;
import app.param.StringParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Object integer(@ValidObject IntegerParam param){
        return param;
    }

    @RequestMapping("/string")
    public Object string(@ValidObject StringParam param){
        return param;
    }

    @RequestMapping("/bigDecimal")
    public Object string(@ValidObject BigDecimalParam param){
        return param;
    }
}
