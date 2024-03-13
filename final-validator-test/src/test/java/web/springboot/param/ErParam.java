package web.springboot.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Pattern;

/**
 * @author lingkang
 * @create by 2024/3/13 9:34
 */
@Data
public class ErParam {
    @Pattern("^(男|女)$")
    private String sex;
}
