package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SendMessageService {

    private static final Logger log = LoggerFactory.getLogger(SendMessageService.class);

    @Autowired
    private MessageStoreService messageStoreService;

    @PostConstruct
    @Scheduled(fixedDelay = 3000)
    public void sendMessageToServer() throws JsonProcessingException {
        log.info("Send Message Service sends points set to Server");

        //Далее нужно отправить на сервер, а не просто залоггировать
        for (PointDTO p : messageStoreService.getQueue()){
            log.info(p.toJson());
            messageStoreService.getQueue().remove();
        }
    }
}
