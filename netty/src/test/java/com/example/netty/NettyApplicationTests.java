package com.example.netty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class NettyApplicationTests {

    static class Test1{
        public static String string = "hhh";
    }

    @Test
    void contextLoads() {
        System.out.println(Test1.string );
    }

}
