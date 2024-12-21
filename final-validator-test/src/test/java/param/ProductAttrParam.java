package param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.NotBlank;
import top.lingkang.finalvalidated.constraints.Pattern;
import top.lingkang.finalvalidated.constraints.Tag;

/**
 * @author lingkang
 * Create by 2024/12/22 0:21
 */
@Data
public class ProductAttrParam {
    // private AddProductParam product;
    @Tag("嵌套属性1")
    private NestingParam1 nestingParam1;
    @Tag("产品类型")
    @NotBlank
    @Length(min = 1, max = 50)
    private String type;

    @Tag("产品尺寸")
    @NotBlank
    @Pattern(value = "small|large", message = "产品尺寸只能是 small 或者 large")
    private String size;
}
