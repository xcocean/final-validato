package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lingkang
 * created by 2024/1/26
 */
@SpringBootApplication
// @ComponentScan(value = {"top.lingkang.finalvalidated","app"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
