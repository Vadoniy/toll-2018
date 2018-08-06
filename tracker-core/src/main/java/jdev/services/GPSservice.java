package jdev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GPSservice {


    private double lat = 52.0278200;
    private double lon = 47.8007000;
    private double speed = 60.0;
    private static String autoID = "r934sk";
    private double azimuth = 155.0;

    private static Logger logger = LoggerFactory.getLogger(GPSservice.class);

    @Autowired
    private MessageStoreService messageStoreService;

    @PostConstruct
    @Scheduled (fixedDelay = 1000)
    private void init() throws Exception{
        messageStoreService.addPoint(lat, lon, autoID, System.nanoTime(), azimuth, speed);
        logger.info("GPSService sent data");
    }
}
