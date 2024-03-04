package demo;

import custom.TestParam;

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
        long start = System.currentTimeMillis();
        field();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }

    private static List<Object> field() throws Exception {
        TestParam param = new TestParam();
        param.setA("a");
        param.setB("b");
        param.setC("c");
        List<Object> list = new ArrayList<>(8000);
        // 当做从缓存中拿
        Field field = TestParam.class.getDeclaredField("a");
        for (int i = 0; i < 5000; i++) {
            field.setAccessible(true);
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
