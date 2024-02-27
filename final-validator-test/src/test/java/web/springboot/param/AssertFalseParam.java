package web.springboot.param;

import lombok.Data;
import top.lingkang.finalvalidated.constraints.AssertFalse;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
@Data
public class AssertFalseParam {
    @AssertFalse
    private String a;
    @AssertFalse
    private boolean b;
    @AssertFalse
    private Boolean c;
}
