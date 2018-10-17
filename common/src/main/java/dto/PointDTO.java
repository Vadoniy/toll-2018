package dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PointDTO {

    private double lat;
    private double lon;
    private String autoId;
    private long time;

    public PointDTO(){}

    public PointDTO(double lat, double lon, String autoId, long time) {
        this.lat = lat;
        this.lon = lon;
        this.autoId = autoId;
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAutoId() {
        return autoId;
    }

    public void setAutoId(String autoId) {
        this.autoId = autoId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    @Override
    public String toString() {
        return "PointDTO{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", autoId='" + autoId + '\'' +
                '}';
    }
}