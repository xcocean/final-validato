package param;

import lombok.Data;
import lombok.experimental.Accessors;
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.Min;
import top.lingkang.finalvalidated.constraints.NotBlank;
import top.lingkang.finalvalidated.constraints.Tag;

/**
 * @author lingkang
 * Create by 2024/12/22 0:21
 */
@Data
@Accessors(chain = true)
public class AddProductParam {
    @Tag("产品名称")
    @NotBlank
    @Length(min = 1, max = 20)
    private String name;
    @Min(1)
    private int price;

    @Tag("属性")
    private ProductAttrParam attr;
}
