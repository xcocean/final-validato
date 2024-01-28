package top.lingkang.finalvalidated.core;

import cn.hutool.core.io.IoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

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

    @Bean
    public FinalValidatorFactory finalValidatorFactory(@Qualifier("requestMappingHandlerAdapter") RequestMappingHandlerAdapter adapter) throws IOException {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) adapter.getWebBindingInitializer();
        FinalValidatorFactory finalValidatorFactory = new FinalValidatorFactory();
        initializer.setValidator(finalValidatorFactory);

        // 加载提示
        Resource resource = resourceLoader.getResource("classpath:defaultValidated.properties");
        InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        FinalValidatorFactory.message.load(reader);
        IoUtil.close(reader);

        // 加载自定义的
        resource = resourceLoader.getResource("classpath:finalValidated.properties");
        if (resource.exists()) {
            reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            Properties finalValidated = new Properties();
            finalValidated.load(reader);
            // 覆盖原有默认的
            FinalValidatorFactory.message.putAll(finalValidated);
            IoUtil.close(reader);
        }

        log.info("final-validator Initialization completed");
        return finalValidatorFactory;
    }

    @Bean
    public FinalValidator finalValidator(@Qualifier("finalValidatorFactory") FinalValidatorFactory finalValidatorFactory) {
        return new FinalValidator(finalValidatorFactory);
    }
}
