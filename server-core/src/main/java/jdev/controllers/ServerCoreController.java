package jdev.controllers;

import dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class ServerCoreController {

    private static final Logger log = LoggerFactory.getLogger(ServerCoreController.class);
    private static String pointData = "PointDTO %s: autoId: %s; lon: %s; lat: %s; time: %s";
    private static String pointInfo;
    private static int pointCounter = 0;

    @RequestMapping(value = "/pointDTO", method = RequestMethod.POST)
    @ResponseBody
    public PointDTO createPoint(@RequestBody PointDTO p){

        pointInfo = String.format(pointData, pointCounter, p.getAutoId(), p.getLon(), p.getLat(), p.getTime());
        log.info(pointInfo);

        try (FileWriter writer = new FileWriter("trackOutput.txt", true)) {
            writer.write(pointInfo + '\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pointCounter++;

        return p;
    }
}
