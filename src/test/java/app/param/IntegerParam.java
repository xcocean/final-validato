package app.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Max;
import top.lingkang.finalvalidated.constraints.Min;
import top.lingkang.finalvalidated.constraints.NotBlank;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class IntegerParam {
    @Min(5)
    @Max(100)
    private int a;

    @Min(1)
    private Integer b;

    @NotBlank
    private Integer c;
}
