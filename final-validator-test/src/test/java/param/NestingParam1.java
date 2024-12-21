package param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.NotBlank;
import top.lingkang.finalvalidated.constraints.Tag;

/**
 * @author lingkang
 * Create by 2024/12/22 1:26
 */
@Data
public class NestingParam1 {
    @Tag("嵌套2属性")
    private NestingParam2 nestingParam2;
    @Tag("嵌套1")
    @NotBlank
    private String nesting1;
}
