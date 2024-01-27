package top.lingkang.finalvalidated.core;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
public class ParamAttribute {
    // 对象属性名称
    private String name;
    // 注解 message
    private String message;
    // 注解 tag
    private String tag;

    public ParamAttribute(String name, String message, String tag) {
        this.name = name;
        this.message = message;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
