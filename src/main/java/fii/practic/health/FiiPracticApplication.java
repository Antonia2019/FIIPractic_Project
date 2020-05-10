package fii.practic.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class FiiPracticApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiiPracticApplication.class, args);
    }
}
