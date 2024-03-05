package test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import param.LoginParam;
import top.lingkang.finalvalidated.core.FinalValidator;

/**
 * @author lingkang
 * @create by 2024/3/5 10:42
 */
@Slf4j
public class Test01Base {
    @BeforeEach
    public void init() {
        FinalValidator.init();
    }

    @Test
    public void test01() {
        try {
            LoginParam param = new LoginParam();
            param.setUsername("123");
            FinalValidator.valid(param);
        } catch (Exception e) {
            log.info(e.getMessage());
            return;
        }
        throw new RuntimeException("检查失败");
    }

    @Test
    public void test02() {
        try {
            LoginParam param = new LoginParam();
            param.setUsername("123456");
            FinalValidator.valid(param);
        } catch (Exception e) {
            log.info(e.getMessage());
            return;
        }
        throw new RuntimeException("检查失败");
    }

    @Test
    public void test03() {
        try {
            LoginParam param = new LoginParam();
            param.setUsername("123456");
            param.setPassword("123");
            FinalValidator.valid(param);
        } catch (Exception e) {
            log.info(e.getMessage());
            return;
        }
        throw new RuntimeException("检查失败");
    }

    @Test
    public void test04() {
        try {
            LoginParam param = new LoginParam();
            param.setUsername("123456");
            param.setPassword("123555");
            FinalValidator.valid(param);
        } catch (Exception e) {
            throw new RuntimeException("检查失败");
        }
        log.info("检查通过");
    }
}
