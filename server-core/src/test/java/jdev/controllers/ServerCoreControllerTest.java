package jdev.controllers;

import dto.PointDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ServerCoreControllerTest {

private static String pointDataJsonTest = "{\"lat\":20.5,\"lon\":12.5,\"autoId\":\"R934SK777\",\"time\":1234567891}";

    @InjectMocks
    ServerCoreController mockedController;

    private PointDTO pointDTO;

    @Before
    public void setPointDTO(){
        pointDTO = new PointDTO();
        pointDTO.setAutoId("R934SK777");
        pointDTO.setLon(12.5);
        pointDTO.setLat(20.5);
        pointDTO.setTime(1234567891);
    }

    @Test
    public void createPoint() throws Exception {
        PointDTO testPointDTO = mockedController.createPoint(pointDTO);
        assertEquals(testPointDTO.toJson(), pointDataJsonTest);
    }
}