# final-validator-solon

`final-validator` 的 `solon` 整合。

## 依赖

```xml
<dependency>
    <groupId>org.noear</groupId>
    <artifactId>solon-web</artifactId>
    <exclusions>
        <!-- 排除 solon自带的 validation 提升性能-->
        <exclusion>
            <groupId>org.noear</groupId>
            <artifactId>solon.validation</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>top.lingkang</groupId>
    <artifactId>final-validator-solon</artifactId>
    <version>2.1.0</version>
</dependency>
```

## 检查对象

```java
@Data
public class LoginParam {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
```

## 定义异常捕获

```java
@Component
public class ErrorFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            chain.doFilter(ctx);
        } catch (ValidatedException e) {// 捕获 ValidatedException 异常为校验异常
            log.warn(e.getMessage());
            ctx.outputAsHtml(e.getMessage());
        }
    }
}
```

## controller 中使用

```java
@Controller
public class TestApp {
    // 添加 @ValidObject 注解的入参才会校验
    @Mapping("/login")
    public Object login(@ValidObject LoginParam loginParam) {
        return "ok: " + loginParam;
    }

    public static void main(String[] args) {
        Solon.start(TestApp.class, args);
    }
}
```

访问：http://localhost:8080/login?username=123
```html
password 不能为空
```

## 非controller中使用

非 `controller` 中，需要对象被bean管理，并在类上添加 `@ValidParams` 注解启用：

定义一个bean对象
```java
@ValidParams// 在此bean中开启入参校验
@Component
public class MyBean {
    // 必须添加 @ValidObject 注解才会校验这个入参
    public Object myMethod(@ValidObject LoginParam param){
        return param.toString();
    }
}
```

调用
```java
    @Inject
    private MyBean myBean;

    @Mapping("/")
    public Object index() {
        LoginParam param = new LoginParam();
        param.setUsername("final-validator");
        Object object = myBean.myMethod(param);
        return object;
    }
```

访问：http://localhost:8080/?username=123
返回结果：
```java
password 不能为空
```

## 静态用法

```java
LoginParam param=new LoginParam();
param.setUsername("admin");
// 校验，失败时会通过 ValidatedException 异常抛出，
// 能够通过 ValidatedException 获取到 字段名称、对象名称、错误信息等。
FinalValidator.valid(param);
```
