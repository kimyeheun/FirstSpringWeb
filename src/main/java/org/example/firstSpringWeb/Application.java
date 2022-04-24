package org.example.firstSpringWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 프로젝트의 최상단에 위치
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
