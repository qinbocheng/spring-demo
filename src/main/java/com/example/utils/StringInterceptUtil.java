package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: qinbocheng
 * Date: 2022/5/1 19:38
 * Description:
 * History:
 * <author>    <time>          <version> <desc>
 * qinbocheng  2022/5/1 19:38 版本号    描述
 */
@Slf4j
@Component
public class StringInterceptUtil {
    /**
     * 截取字符串内数字
     * @param string
     * @return
     */
    public  String get_StringNum(String string){
        String  regEx = "[^0-9]";
        Pattern p =Pattern.compile(regEx);
        Matcher m = p.matcher(string);
        return m.replaceAll("").trim();
    }


}
