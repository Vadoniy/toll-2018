package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendMessageService {

    private static final Logger log = LoggerFactory.getLogger(SendMessageService.class);

    @Autowired
    private MessageStoreService messageStoreService;

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedDelay = 3000)
    public void sendMessageToServer() throws JsonProcessingException {
        log.info("Send Message Service sends points set to Server");

        for (PointDTO p : messageStoreService.getQueue()){
            log.info(p.toJson());
            restTemplate.postForObject("http://localhost:8080/pointDTO", p, PointDTO.class);
            messageStoreService.getQueue().remove();
        }
    }
}
