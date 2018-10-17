import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PointDTO;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Vadoniy on 18.07.2018.
 */
public class PointDTOTestTC {

    private String expected = "{\"lat\":17.0,\"lon\":11.0,\"autoId\":\"P934CK777\",\"time\":65478392}";
    private String autoId = "P934CK777";

    @Test
    public void toJson() throws JsonProcessingException {
        PointDTO point = new PointDTO(17, 11, "Р934СК", System.currentTimeMillis());
        assertTrue(point.toJson().contains("\"autoId\":\"Р934СК\""));
    }

    @Test
    public void decodeDto() throws IOException {
        PointDTO point = new ObjectMapper().readValue(expected, PointDTO.class);
        assertEquals(point.getAutoId(), autoId);
    }

    @Test
    public void checkAutoId() throws IOException {
        PointDTO point = new ObjectMapper().readValue(expected, PointDTO.class);
        assertTrue(point.toJson().contains("autoId"));
        System.out.println(point.toJson());
    }
}
