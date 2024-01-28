# final-validator

## 介绍

final-validator 是一个JavaBean元数据校验模型和方法验证，能够自定义注解来扩展校验范围。属于符合我国习惯的参数校验java库，能够独立使用，或者结合springmvc体系使用。

## 起始点

开发的初衷是由于 `spring-boot-starter-validation` 用起来不习惯，无法直接自定义异常处理、字段多而数据格式不符合开发习惯。因而开发此库，让你脱离大量的`if`、`assert`的判断使用。

## 引入依赖

```xml
<dependency>
    <groupId>top.lingkang</groupId>
    <artifactId>final-validator</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## springmvc中

### 1、编写一个入参类
```java
@Data
public class LoginParam {
    @NotBlank
    private String username;
    @NotBlank
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
    /**
     * 捕获校验异常，返回rest结果
     */
    @ExceptionHandler(ValidatedException.class)
    public Object v(ValidatedException e) {
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg",e.getMessage());
        return map;
    }
}
```

### 4、调用
启动springboot，访问与结果返回：

`/login?username=&password=`
返回结果
```json
{"msg":"username 不能为空","code":1}
```

`/login?username=123&password=`
返回结果
```json
{"msg":"password 不能为空","code":1}
```

`/login?username=123&password=123`
返回结果
```json
{"username":"123","password":"123"}
```

若你想直接校验某个参数对象，可以直接调用：
```java
LoginParam param=new LoginParam();
param.setUsername("admin");
// 校验，失败时会通过 ValidatedException 异常抛出，能够通过 ValidatedException 获取到 字段名称、对象名称、错误信息等。
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

    // 校验，失败时会通过 ValidatedException 异常抛出，能够通过 ValidatedException 获取到 字段名称、对象名称、错误信息等。
    FinalValidator.valid(param);
}
```

## 添加自己的自定义校验

可以自定义注解，自己实现校验，请查看：[自定义注解](https://gitee.com/lingkang_top/final-validator/blob/master/doc/02.%E8%87%AA%E5%AE%9A%E4%B9%89%E6%B3%A8%E8%A7%A3%E5%A4%84%E7%90%86.md)







