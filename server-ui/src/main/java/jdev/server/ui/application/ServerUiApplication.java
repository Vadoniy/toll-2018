package jdev.server.ui.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("jdev.server.ui.config")
public class ServerUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerUiApplication.class, args);
    }
}
