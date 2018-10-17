package jdev.application;

import jdev.context.TrackerCoreContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@EnableScheduling
@Configuration
public class TrackerCoreMain {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TrackerCoreContext.class);
    }
}
