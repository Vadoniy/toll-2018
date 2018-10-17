package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class MessageStoreService {

    private static final Logger logger = LoggerFactory.getLogger(MessageStoreService.class);

    private BlockingDeque<PointDTO> queue = new LinkedBlockingDeque<>();

    public void addPoint(double lat, double lon, String autoId, long time) throws InterruptedException {
        logger.info("Message Store Server receives new point");
        queue.put(new PointDTO(lat, lon, autoId, time));
    }

    public Queue<PointDTO> getQueue(){
        return queue;
    }

    public List<String> takePoint() throws InterruptedException, JsonProcessingException {
        List<String> points = new ArrayList<>();
        while (!queue.isEmpty()){
            points.add(queue.take().toJson());
        }
        return points;
    }

    public void removePoint(PointDTO p){
        this.queue.remove(p);
    }
}