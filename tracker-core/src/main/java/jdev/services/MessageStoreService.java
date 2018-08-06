package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import dto.PointDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class MessageStoreService {

    private BlockingDeque<PointDTO> queue = new LinkedBlockingDeque<>();

    public void addPoint(double lat, double lon, String autoId, long time, double azimuth, double speed) throws InterruptedException {
        queue.put(new PointDTO(lat, lon, autoId, time, azimuth, speed));
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