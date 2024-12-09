package top.lingkang.finalvalidated.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.lingkang.finalvalidated.error.CheckException;
import top.lingkang.finalvalidated.error.ValidatedException;
import top.lingkang.finalvalidated.handle.CustomValidHandle;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author lingkang
 * Created by 2024/1/28<br/>
 * 可全局使用的校验，例如
 * <pre>{@code
 *     // spring 启动后
 *     FinalValidator.valid(loginParam);
 * }
 * </pre>
 * 或者<br/>
 * <pre>{@code
 *     @Autowired
 *     private FinalValidator finalValidator;
 *     // ....
 *
 *     finalValidator.valid(loginParam);
 * }
 * </pre>
 * 若你想在静态非spring系统中使用，需要手动初始化:<br/>
 * <pre>{@code
 *   // 只需初始化一次，必须初始化，否则报空指针错误
 *   FinalValidator.init();
 *
 *   LoginParam param=new LoginParam();
 *   param.setUsername("admin");
 *   // 校验参数
 *   FinalValidator.valid(param);
 * }
 * </pre>
 */
public class FinalValidator {
    private static final Logger log = LoggerFactory.getLogger(FinalValidator.class);

    private static FinalValidatorFactory finalValidatorFactory;
    private static boolean isSpring = false;

    /**
     * spring初始化时，使用构造函数传递参数
     */
    public FinalValidator(FinalValidatorFactory finalValidatorFactory) {
        if (FinalValidator.finalValidatorFactory != null && !isSpring) {
            throw new CheckException("在spring系统中，不能提前调用 FinalValidator.init() 初始化 FinalValidator ，" +
                    "在spring体系中已经交由bean托管，由spring自动初始化FinalValidator，请不要手动提前于spring初始化FinalValidator");
        }
        FinalValidator.finalValidatorFactory = finalValidatorFactory;
        isSpring = true;
    }

    /**
     * 非spring体系中手动初始化校验
     * spring 体系中不需要调用此方法，spring会自动初始化
     */
    public static void init() {
        if (finalValidatorFactory != null) {
            if (isSpring) {
                log.warn("FinalValidator 已经交由spring初始化完毕！无须手动初始化");
                return;
            }
            log.warn("FinalValidator 已经初始化完毕！");
            return;
        }

        try {
            FinalValidator.finalValidatorFactory = new FinalValidatorFactory();

            // 加载提示
            InputStream inputStream = FinalValidator.class.getClassLoader().getResourceAsStream("defaultValidated.properties");
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            FinalValidatorFactory.message.load(reader);
            FinalValidatorUtils.close(reader);

            // 加载自定义的
            inputStream = FinalValidator.class.getClassLoader().getResourceAsStream("finalValidated.properties");
            if (inputStream != null) {
                reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                Properties finalValidated = new Properties();
                finalValidated.load(reader);
                // 覆盖原有默认的
                FinalValidatorFactory.message.putAll(finalValidated);
                FinalValidatorUtils.close(reader);
            }
        } catch (Exception e) {
            FinalValidator.finalValidatorFactory = null;
            throw new CheckException("final-validator 初始化失败！", e);
        }
        log.info("final-validator Initialization completed");
    }

    /**
     * 手动校验:
     * <pre>{@code
     *         LoginParam param=new LoginParam();
     *         param.setUsername("admin");
     *         FinalValidator.valid(param);
     * }
     * </pre>
     * 非spring体系下应该先手动初始化:
     * <pre>{@code
     *         FinalValidator.init()
     *         LoginParam param=new LoginParam();
     *         param.setUsername("admin");
     *         FinalValidator.init();
     *         FinalValidator.valid(param);
     * }
     * </pre>
     * 父级属性也在检查范围（v2.2.0+），如果子类存在与父类相同的属性名，则只检查子类为主。除非子类属性没有添加检查注解。
     *
     * @param target 入参对象，其属性有final-validator校验注解时，会将其校验
     * @throws NullPointerException 未初始化化时，调用将会报空指针，请初始化: <pre>{@code FinalValidator.init()}</pre>
     */
    public static void valid(Object target) {
        if (target == null)
            throw new ValidatedException("校验入参不能为空");
        if (finalValidatorFactory.supports(target.getClass())) {
            finalValidatorFactory.validate(target);
        }
    }

    /**
     * 判断这个类是否需要校验，存在 final-validator 的条件注解属性才会进行校验
     *
     * @param clazz 对象类
     * @return true 需要校验。false 不需要校验
     * @throws NullPointerException 未初始化化时，调用将会报空指针，请初始化: <pre>{@code FinalValidator.init()}</pre>
     */
    public static boolean supports(Class<?> clazz) {
        return finalValidatorFactory.supports(clazz);
    }

    /**
     * 添加自定义注解
     *
     * @param annotation  自定义的注解
     * @param validHandle 自定义的校验处理
     */
    public static void addCustom(Class<? extends Annotation> annotation, Class<? extends CustomValidHandle> validHandle) {
        if (annotation == null)
            throw new CheckException("annotation 不能为空！");
        if (validHandle == null)
            throw new CheckException("ValidHandle 不能为空！");

        // 添加到自定义
        FinalValidatorUtils.addCustom(annotation, validHandle);
        // 添加自定义注解时，将缓存清理
        finalValidatorFactory.clearCache();
    }

    public static boolean isInitComplete() {
        return finalValidatorFactory != null;
    }

    public static void clearCache() {
        finalValidatorFactory.clearCache();
    }
}
