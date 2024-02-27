package web.springboot.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.Pattern;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class PatternParam {
    @Pattern(value = "^1[3-9]\\d{9}$", tag = "手机号")
    @Length(max = 11)
    private String a;
    @Pattern(value = "^1[3-9]\\d{9}$", tag = "手机号")
    private String b;
    private String c;
}
