package app.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.NotNull;
import top.lingkang.finalvalidated.constraints.Null;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class NotNullParam {
    @NotNull
    private String a;
    @Null
    private String b;
}
