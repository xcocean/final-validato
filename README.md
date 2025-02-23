# final-validator

## 介绍

final-validator 是一个JavaBean元数据校验模型和方法验证，能够自定义注解来扩展校验范围，符合国内开发习惯的参数校验java库。


## 引入依赖

`jdk8+`  `springboot2.x`  `springboot3.x`   `springmvc5.x` `springmvc6.x` `非spring体系项目`

```xml
<dependency>
    <groupId>top.lingkang</groupId>
    <artifactId>final-validator</artifactId>
    <version>2.3.0</version>
</dependency>
```
[版本查看](https://mvnrepository.com/artifact/top.lingkang/final-validator)

## springmvc中

### 1、编写一个入参类

```java
import top.lingkang.finalvalidated.constraints.Length;
import top.lingkang.finalvalidated.constraints.Tag;

@Data
public class LoginParam {
    @NotBlank// 不为空
    @Length(min = 6, max = 20) // 长度范围 6~20
    private String username;

    @Tag("密码")
    @NotBlank
    @Length(min = 6, max = 20) // 长度范围 6~20
    private String password;
}
```

### 2、springmvc的api如下，使用`@ValidObject`开启入参校验。
```java
@RequestMapping("/login")
public Object login(@ValidObject LoginParam param) {
    return param;
}
```
注意，不添加`@ValidObject`注解将导致无法使用`final-validator`的校验功能

### 3、编写一个异常捕获
若校验不通过将会抛出 `top.lingkang.finalvalidated.error.ValidatedException` 异常，捕获它自定义返回前端值。例如下面这样：
```java
@RestControllerAdvice
public class ExceptionConfig {
    // 使用spring自带的 json格式化 Jackson库
    private final ObjectMapper mapper = new ObjectMapper();
    /**
     * 捕获校验异常，返回rest结果
     */
    @ExceptionHandler(ValidatedException.class)
    public Object v(ValidatedException e) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", mapper.readValue(e.getMessage(), Map.class));
        // map.put("msg", mapper.readValue(e.getMessage(), Map.class)); // json格式化校验失败的结果
        // map.put("object", e.getObjectName());
        // map.put("filed", e.getFiledName());
        return map;
    }
}
```

### 4、调用
启动springboot，访问与结果返回：

`/login?username=&password=`<br>
返回结果
```json
{"msg":{"username":"不能为空"},"code":1}
```

`/login?username=123&password=`<br>
返回结果
```json
{"msg":{"username":"字符长度范围： 6 ~ 20"},"code":1}
```

`/login?username=123456&password=123`<br>
返回结果
```json
{"msg":{"密码":"字符长度范围： 6 ~ 20"},"code":1}
```

`/login?username=123456&password=12345678`<br>
返回结果
```json
{"username":"123456","password":"12345678"}
```

若你想直接校验某个参数对象，可以直接调用：
```java
LoginParam param=new LoginParam();
param.setUsername("admin");
// 校验，失败时会通过 ValidatedException 异常抛出，
// 能够通过 ValidatedException 获取到 字段名称、对象名称、错误信息等。
FinalValidator.valid(param);
```


## 非spring体系中使用

调用 `FinalValidator.init()` 初始化后，任意地方调用`FinalValidator.valid(param)`即可。
```java
public static void main(String[] args) {
    // 初始化校验，只需初始化一次即可
    FinalValidator.init();

    LoginParam param=new LoginParam();
    param.setUsername("admin");

    // 校验，失败时会通过 ValidatedException 异常抛出，
    // 能够通过 ValidatedException 获取到 字段名称、对象名称、错误信息等。
    FinalValidator.valid(param);
}
```

## solon 中使用

**solon：** [final-validator-solon](https://gitee.com/lingkang_top/final-validator/tree/master/final-validator-solon)


## 添加自己的自定义校验

可以自定义注解，自己实现校验，请查看：[添加自定义注解](https://gitee.com/lingkang_top/final-validator/blob/master/doc/02.%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B3%A8%E8%A7%A3%E5%A4%84%E7%90%86.md)

## 自定义消息文字

`final-validator` 默认的消息文字位于 `defaultValidated.properties`。
若您想自定义，可在`resources`下添加一个 **finalValidated.properties**配置文件，框架初始化时将会加载并覆盖原有的默认配置。
```properties
NotBlank={message} 不能是空值
```
具体key-value需要参考：[defaultValidated.properties](https://gitee.com/lingkang_top/final-validator/blob/master/final-validator/src/main/resources/defaultValidated.properties)

## 底层原理

底层原理介绍：[https://lingkang.top/archives/final-validator-shou-lu-validator](https://lingkang.top/archives/final-validator-shou-lu-validator)

## 性能

底层缓存了注解处理，性能几乎可以忽略不计。

## 注解

| 验证注解         | 说明                                  |
|--------------|-------------------------------------|
| @NotBlank    | 注解的属性必定不为空、不为空格字符                   |
| @NotEmpty    | 注解的属性必定不为空、但可以为空格字符                 |
| @NotNull     | 注解的属性必定不是空，即 非 null 值               |
| @Null        | 注解的属性必定是空，即 null 值                  |
| @Min         | 注解的属性最小值，小于等于                       |
| @Max         | 注解的属性最大值，不大于等于                      |
| @Length      | 注解的属性的长度，指定最小长度和最大长度，min与max不能相等    |
| @AssertFalse | 验证注解的元素值是否是 false ，当值为 false 时将通过校验 |
| @AssertTrue  | 验证注解的元素值是否是 true ，当值为 true 时将通过校验   |
| @Email       | 注解的属性的值是否是邮箱                        |
| @Pattern     | 注解的属性的值是否符合自定义正则表达式                 |
| @Tag         | 注解的属性的tag值，将会覆盖该字段其他注解的所有tag值(`v1.1.1`)     |

## 常用验证

#### 1. 验证账号
```java
@Pattern(value = "^[a-zA-Z0-9_]{4,20}$", message = "账号只能由字母、数字、下划线组成，并且长度为4~20个字符")
private String username;
```
#### 2. 性别验证
```java
@Pattern(value = "男|女|保密", message = "性别只能是：男、女或保密")
private String sex;
```

## 其它

* 有更好的建议请提 [Issues](https://gitee.com/lingkang_top/final-validator/issues)
* 使用快照版本 [SNAPSHOT](https://gitee.com/lingkang_top/final-validator/blob/master/doc/03.%E4%BD%BF%E7%94%A8%E5%BF%AB%E7%85%A7%E7%89%88%E6%9C%AC.md)
* 默认的校验消息 [defaultValidated.properties](https://gitee.com/lingkang_top/final-validator/blob/master/final-validator/src/main/resources/defaultValidated.properties)
* 作者在积极优化中，[版本发布说明](https://gitee.com/lingkang_top/final-validator/blob/master/doc/99.%E5%8F%91%E7%89%88.md)