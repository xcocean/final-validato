package web.springboot.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.NotBlank;
import top.lingkang.finalvalidated.constraints.Tag;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
@Data
public class LoginParam {
    @NotBlank// 不为空
    @Length(min = 6, max = 20) // 长度范围 6~20
    private String username;

    @Tag("密码")
    @NotBlank
    @Length(min = 6, max = 20) // 长度范围 6~20
    private String password;
}
