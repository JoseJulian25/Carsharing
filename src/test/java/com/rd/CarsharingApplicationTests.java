package com.rd;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarsharingApplicationTests {

    @Test
    void contextLoads() {
        String password = "123456789";
        String password2 = "123456e78";
        String password3 = "12345678E78";
        String password4 = "#Hola2525";
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9!@#$%^&*()\\-_=+{}\\[\\]|;:'\",.<>/?]{8,16}$";
        System.out.println(password.matches(regex));
        System.out.println(password2.matches(regex));
        System.out.println(password3.matches(regex));
        System.out.println(password4.matches(regex));

    }

}
