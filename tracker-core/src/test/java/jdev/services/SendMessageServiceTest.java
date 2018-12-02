package jdev.services;

import dto.PointDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SendMessageServiceTest {

    private PointDTO pointDTO;
    private BlockingDeque<PointDTO> queue;
    private static final int capacity = 3;

    @Mock
    MessageStoreService storage;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    SendMessageService sendMessageService;

    @Before
    public void setUp(){
        queue = new LinkedBlockingDeque<>(capacity);

        for (int i = 0; i < capacity; i++){
            pointDTO = new PointDTO(150.0 + i, 180.2 + i, "carId_" + i, 1200 + i);
            queue.add(pointDTO);
        }
    }

    @Test
    public void sendMessageToServer() throws Exception {
        when(storage.getQueue()).thenReturn(queue);
        when(restTemplate.postForObject("http://localhost:8080/pointDTO", pointDTO, PointDTO.class)).thenReturn(pointDTO);
        assertEquals(capacity, sendMessageService.sendMessageToServer().size());
    }

}