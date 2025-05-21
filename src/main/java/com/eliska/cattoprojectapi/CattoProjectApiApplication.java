package com.eliska.cattoprojectapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CattoProjectApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CattoProjectApiApplication.class, args);
    }

    public static void MyLog(String message) {

        System.out.printf("||==//  %s   \\\\==||\n", message);
    }

}
