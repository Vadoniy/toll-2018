package jdev.controllers;

import dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerCoreController {

    private static final Logger log = LoggerFactory.getLogger(ServerCoreController.class);
    private static String pointData = "AutoId: %s lon: %s lat: %s time: %s";

    @RequestMapping(value = "/pointDTO", method = RequestMethod.POST)
    @ResponseBody
    public PointDTO createPoint(@RequestBody PointDTO p){
        log.info(String.format(pointData, p.getAutoId(), p.getLon(), p.getLat(), p.getTime()));
        return p;
    }
}
