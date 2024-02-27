package web.springboot.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.AssertTrue;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class AssertTrueParam {
    @AssertTrue
    private String a;
    @AssertTrue
    private boolean b;
    @AssertTrue
    private Boolean c;
}
