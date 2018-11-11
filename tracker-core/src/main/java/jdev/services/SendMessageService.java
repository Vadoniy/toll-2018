package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendMessageService {

    private static final Logger log = LoggerFactory.getLogger(SendMessageService.class);
    private static String pointDataLog = "PointDTO %s: %s";
    private static int counterPoints = 0;

    @Autowired
    private MessageStoreService messageStoreService;

    @Autowired
    RestTemplate restTemplate;

    @Scheduled(fixedDelay = 3000)
    public List<PointDTO> sendMessageToServer() throws JsonProcessingException {
        log.info("Send Message Service sends points set to Server");

        List<PointDTO> pointDTOList = new ArrayList<>();

        for (PointDTO p : messageStoreService.getQueue()){
            log.info(String.format(pointDataLog, counterPoints, p.toJson()));
            pointDTOList.add(restTemplate.postForObject("http://localhost:8080/pointDTO", p, PointDTO.class));
            messageStoreService.getQueue().remove();
            counterPoints++;
        }
        return pointDTOList;
    }
}
