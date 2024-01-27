package demo;

import java.math.BigDecimal;

/**
 * @author lingkang
 * Created by 2024/1/27
 */
public class Demo02 {
    public static void main(String[] args) {
        Object a=-8888;
        short c=2;
        a=c;
        Integer b=2;
        System.out.println(a instanceof Short);
        System.out.println(a.toString());
        System.out.println(a instanceof Number);
        Object aa=0;
        BigDecimal bb=new BigDecimal(-111);
        aa=bb;
        System.out.println(aa instanceof Number);

    }
}
