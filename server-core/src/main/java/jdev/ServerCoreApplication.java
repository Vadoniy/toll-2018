package jdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("jdev.controllers")
public class ServerCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerCoreApplication.class, args);
    }
}
