package app.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.NotBlank;
import top.lingkang.finalvalidated.constraints.NotEmpty;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class StringParam {
    @NotBlank
    @Length(min = 0, max = 5)
    private String a;
    @NotBlank(message = "~ b 不能为空！")
    private String b;
    private String c;
    @NotEmpty
    private String d;
}
