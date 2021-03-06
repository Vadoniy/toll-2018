import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PointDTO;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vadoniy on 18.07.2018.
 */
public class PointDTOTestSU {
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
        assertEquals(autoId, point.getAutoId());
    }

    @Test
    public void checkAutoId() throws IOException {
        PointDTO point = new ObjectMapper().readValue(expected, PointDTO.class);
        assertTrue(point.toJson().contains("autoId"));
    }
}
