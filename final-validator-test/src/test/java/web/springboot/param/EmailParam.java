package web.springboot.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Email;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.NotBlank;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class EmailParam {
    @Length(max = 10)
    @NotBlank
    @Email
    private String a;
    @Email
    private String b;
    private String c;
}
