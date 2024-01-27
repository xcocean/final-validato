package app.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.NotBlank;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
@Data
public class LoginParam {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
