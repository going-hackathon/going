package com.hackathon.going;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GoingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoingApplication.class, args);
    }

}
