package demo;

import custom.TestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lingkang
 * Created by 2024/3/5
 */
public class Demo05 {

    public static void main(String[] args) throws Exception {
        long end = 0L, start = System.currentTimeMillis();
        for (int i = 0; i < 12; i++) {
            List<Object> list = field();
            end = System.currentTimeMillis();
            System.out.println("耗时：" + (end - start));
            start = end;
        }
    }

    private static List<Object> field() throws Exception {
        TestParam param = new TestParam();
        param.setA("a");
        param.setB("b");
        param.setC("c");
        List<Object> list = new ArrayList<>(8000);
        // 当做从缓存中拿
        Field field = TestParam.class.getDeclaredField("a");
        field.setAccessible(true); // 做了访问操作，因为是缓存，所以提前执行
        for (int i = 0; i < 5000; i++) {
            list.add(field.get(param));
        }
        return list;
    }

    private static List<Object> method() throws Exception {
        TestParam param = new TestParam();
        param.setA("a");
        param.setB("b");
        param.setC("c");
        List<Object> list = new ArrayList<>(8000);
        // 当做从缓存中拿
        Method method = TestParam.class.getMethod("getA");
        for (int i = 0; i < 5000; i++) {
            list.add(method.invoke(param));
        }
        return list;
    }
}
