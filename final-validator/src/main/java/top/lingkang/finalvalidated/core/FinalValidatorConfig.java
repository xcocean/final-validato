package top.lingkang.finalvalidated.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import top.lingkang.finalvalidated.utils.FinalValidatorUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author lingkang
 * Created by 2024/1/26
 * spring 初始化配置
 */
@Configuration
public class FinalValidatorConfig {
    private static final Logger log = LoggerFactory.getLogger(FinalValidatorConfig.class);
    @Autowired
    private ResourceLoader resourceLoader;
    private FinalValidatorFactory finalValidatorFactory;

    /**
     * FinalValidatorFactorySpring 内置 FinalValidatorFactory ，用于折中调用。
     * 主要目的是让FinalValidatorFactory能够脱离spring框架使用
     */
    @Bean
    public FinalValidatorFactorySpring finalValidatorFactorySpring(@Autowired RequestMappingHandlerAdapter adapter) throws IOException {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) adapter.getWebBindingInitializer();
        finalValidatorFactory = new FinalValidatorFactory();
        FinalValidatorFactorySpring validatorFactorySpring = new FinalValidatorFactorySpring(finalValidatorFactory);
        initializer.setValidator(validatorFactorySpring);

        // 加载提示
        Resource resource = resourceLoader.getResource("classpath:defaultValidated.properties");
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        FinalValidatorFactory.message.load(reader);
        FinalValidatorUtils.close(reader);

        // 加载自定义的
        resource = resourceLoader.getResource("classpath:finalValidated.properties");
        if (resource.exists()) {
            reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            Properties finalValidated = new Properties();
            finalValidated.load(reader);
            // 覆盖原有默认的
            FinalValidatorFactory.message.putAll(finalValidated);
            FinalValidatorUtils.close(reader);
        }

        log.info("final-validator Initialization completed");
        return validatorFactorySpring;
    }

    @Bean
    public FinalValidator finalValidator(@Autowired FinalValidatorFactorySpring spring) {
        return new FinalValidator(spring.finalValidatorFactory);
    }

    /**
     * 清理缓存，注意：清理后，您添加的自定义注解也被清除，需要重新添加。
     *
     * @since 2.3.0
     */
    public void clearCache() {
        finalValidatorFactory.clearCache();
    }
}
