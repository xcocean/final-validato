package web.springboot.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.Min;
import top.lingkang.finalvalidated.constraints.NotEmpty;

import java.math.BigDecimal;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class BigDecimalParam {
    @NotEmpty
    @Min(0)
    private BigDecimal price;

    @Length(max = 3)
    private BigDecimal a;

    private BigDecimal b;
}
