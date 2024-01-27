package top.lingkang.finalvalidated.core;

import cn.hutool.core.io.IoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
@Configuration
public class FinalValidatorConfig {
    private static final Logger log = LoggerFactory.getLogger(FinalValidatorConfig.class);
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ResourceLoader resourceLoader;

    @ConditionalOnMissingBean
    @Bean
    public ValidatorFactory validatorFactory() throws IOException {
        log.info("final-validator 开始初始化");
        RequestMappingHandlerAdapter adapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) adapter.getWebBindingInitializer();
        ValidatorFactory validatorFactory = new ValidatorFactory();
        initializer.setValidator(validatorFactory);

        // 加载提示
        Resource resource = resourceLoader.getResource("classpath:defaultValidated.properties");
        InputStreamReader reader=new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
        ValidatorFactory.message.load(reader);
        IoUtil.close(reader);

        // 加载自定义的
        resource = resourceLoader.getResource("classpath:finalValidated.properties");
        if (resource.exists()){
            reader=new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            Properties finalValidated=new Properties();
            finalValidated.load(reader);
            // 覆盖原有默认的
            ValidatorFactory.message.putAll(finalValidated);
            IoUtil.close(reader);
        }

        log.info("final-validator 初始化完成");
        return validatorFactory;
    }
}
