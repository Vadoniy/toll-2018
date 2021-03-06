package jdev.context;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import jdev.services.GPSservice;
import jdev.services.MessageStoreService;
import jdev.services.SendMessageService;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class TrackerCoreContext {

    @Bean
    public GPSservice gpSservice(){
        return new GPSservice();
    }

    @Bean
    public MessageStoreService messageStoreService(){
        return new MessageStoreService();
    }

    @Bean
    public SendMessageService sendMessageService(){
        return new SendMessageService();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setPoolSize(20);
        return scheduler;
    }
}
