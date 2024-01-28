package app.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.NotEmpty;
import top.lingkang.finalvalidated.constraints.Null;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class NullParam {
    @Null
    private String a;
    @NotEmpty
    private String b;
}
