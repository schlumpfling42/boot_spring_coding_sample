package net.rka.springboot.sample.rest;

import java.util.ArrayList;
import java.util.List;

public class WeatherAndLocationDataDTO {

    private List<String> status = new ArrayList<>();
    private String city;
    private String temperature;
    private String latitude;
    private String longitude;
    private String timezone;
    private String elevation;

    public List<String> getStatus() {
        return status;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
