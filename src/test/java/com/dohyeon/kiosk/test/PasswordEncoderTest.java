package com.dohyeon.kiosk.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passTest() {

        String rawPassword = "123456789";

        System.out.println(rawPassword);

        String encodePassword = passwordEncoder.encode(rawPassword);

        System.out.println(encodePassword);
    }
}
