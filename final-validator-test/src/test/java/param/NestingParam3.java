package param;

import lombok.Data;
import lombok.experimental.Accessors;
import top.lingkang.finalvalidated.constraints.NotBlank;
import top.lingkang.finalvalidated.constraints.Tag;

/**
 * @author lingkang
 * Create by 2024/12/22 1:26
 */
@Data
@Accessors(chain = true)
public class NestingParam3 {
    @Tag("嵌套3")
    @NotBlank
    private String nesting3;
}
