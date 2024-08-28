package top.lingkang.fv.solon.test.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.Pattern;
import top.lingkang.finalvalidated.constraints.Tag;

/**
 * @author lingkang
 * Create by 2024/8/29 2:38
 */
@Data
public class RegisterParam {
    @Pattern(value = "^[a-zA-Z0-9_@]{6,20}$", message = "账号只能由字母、数字、下划线、@等组成，并且长度为6~20个字符")
    private String username;
    @Pattern(value = "^[a-zA-Z0-9_@]{8,20}$", message = "密码只能由字母、数字、下划线、@等组成，并且长度为8~20个字符")
    private String password;
    private String password2;
    @Tag("手机号")
    @Length(min = 11, max = 11)
    private String phone;
    private String code;
}
