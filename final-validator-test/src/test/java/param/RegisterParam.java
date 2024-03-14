package param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Pattern;

/**
 * @author lingkang
 * @create by 2024/3/14 10:35
 */
@Data
public class RegisterParam {
    @Pattern(value = "^[a-zA-Z0-9_]{4,20}$", message = "账号只能由字母、数字、下划线组成，并且长度为4~20个字符")
    private String username;

    @Pattern(value = "^[a-zA-Z0-9_]{4,20}$", message = "密码只能由字母、数字、下划线组成，并且长度为4~20个字符")
    private String password;
    @Pattern(value = "男|女|保密", message = "性别只能是：男、女或保密")
    private String sex;
}
