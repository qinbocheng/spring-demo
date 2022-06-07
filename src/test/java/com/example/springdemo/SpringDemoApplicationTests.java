package com.example.springdemo;
import com.example.service.TestService;
import com.example.utils.StringInterceptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
public class SpringDemoApplicationTests {
    @Resource
    private TestService testService;
    @Autowired
    private StringInterceptUtil stringInterceptUtil;

    @Test
    public void jk() {
        System.out.println(testService.checkPhone("1888969995531"));;
    }

    @Test
    public void test2(){
        System.out.println(stringInterceptUtil.get_StringNum("16520"));;
    }

}
