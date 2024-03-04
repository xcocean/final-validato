package top.lingkang.fv.solon.test.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.NotBlank;

/**
 * @author lingkang
 * Created by 2024/3/5
 */
@Data
public class LoginParam {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
