package jdev.services;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Service
public class GPSservice {

    private static String autoID = "r934sk";

    private static Logger logger = LoggerFactory.getLogger(GPSservice.class);

    final Kml kml = Kml.unmarshal(new File("src//main//resources//track.kml"));
    final Placemark placemark = (Placemark) kml.getFeature();
    LineString point = (LineString) placemark.getGeometry();
    List<Coordinate> coordinates = point.getCoordinates();
    int i = 0;

    @Autowired
    private MessageStoreService messageStoreService;

    @PostConstruct
    @Scheduled (fixedDelay = 1000)
    private void init() throws Exception{

        logger.info("GPSservice generated point");

        if (i < coordinates.toArray().length){
            messageStoreService.addPoint(coordinates.get(i).getLatitude(),
                    coordinates.get(i).getLongitude(), autoID, System.nanoTime());
            i++;
        } else {
            logger.info("Track is finished.");
        }
    }
}
