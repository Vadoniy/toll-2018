package jdev.services;

import dto.PointDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.lang.reflect.Field;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.*;

/**
 * Created by Vadoniy on 11.11.2018.
 */
public class MessageStoreServiceTest {

    private static final int capacity = 10;

    private BlockingDeque<PointDTO> queue;
    private PointDTO pointDTO;
    private MessageStoreService messageStoreService;

    @Before
    public void setUp() throws Exception {
        messageStoreService = new MessageStoreService();

        Field privateQueue = MessageStoreService.class.getDeclaredField("queue");
        privateQueue.setAccessible(true);
        queue = (BlockingDeque<PointDTO>) privateQueue.get(messageStoreService);
        pointDTO = new PointDTO(150.0, 180.2, "carId_0", 1200);

        for (int i = 0; i < capacity; i++){
            messageStoreService.addPoint(150.0 + i, 180.2+i, "carId_" + i, 1200 + i);
        }
    }

    @Test
    public void addPoint() throws Exception {
        queue.put(pointDTO);
        assertEquals("carId_0", queue.getFirst().getAutoId());
        assertEquals(150.0, queue.getFirst().getLat(), 0);
        assertEquals(180.2, queue.getFirst().getLon(), 0);
        assertEquals(1200, queue.getFirst().getTime());
    }

    @Test
    public void getQueue() throws Exception {
        assertEquals(capacity, messageStoreService.getQueue().toArray().length);
    }

    @Test
    public void takePoint() throws Exception {
        queue.put(pointDTO);
        assertEquals("carId_0", queue.getFirst().getAutoId());
        assertEquals(150.0, queue.getFirst().getLat(), 0);
        assertEquals(180.2, queue.getFirst().getLon(), 0);
        assertEquals(1200, queue.getFirst().getTime());
    }

    @Test
    public void removePoint() throws Exception {
        queue.put(pointDTO);
        messageStoreService.removePoint(pointDTO);
        assertFalse(queue.contains(pointDTO));
    }

}