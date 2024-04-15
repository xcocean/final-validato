package param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Max;
import top.lingkang.finalvalidated.constraints.Min;
import top.lingkang.finalvalidated.constraints.Tag;

/**
 * @author lingkang
 * @create by 2024/3/25 14:29
 */
@Data
public class NumberParam {
    @Min(1)
    private Integer min;
    @Max(1)
    @Tag("最大")
    private Integer max;
}
