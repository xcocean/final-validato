package demo;

import cn.hutool.core.lang.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lingkang
 * Created by 2024/1/28
 */
public class Demo04 {
    public static void main(String[] args) {
        String phoneNumber = "15977707550"; // 待检查的手机号码
        String regex = "^1[3-9]\\d{9}$"; // 匹配中国手机号码的正则表达式

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        if (matcher.matches()) {
            System.out.println("这是一个有效的手机号码");
        } else {
            System.out.println("这不是一个有效的手机号码");
        }
        Validator.isCitizenId("");
        Validator.isGeneral("");
    }
}
