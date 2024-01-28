package custom;

import lombok.Data;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
@Data
public class TestParam {
    @TestCustom
    private String a;
    @TestCustom("这是自定义@TestCustom所带的值")
    private String b;
    private String c;
}
