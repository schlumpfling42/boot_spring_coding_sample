package net.rka.springboot.sample.rest;

import com.google.maps.model.LatLng;
import net.rka.springboot.sample.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.Location;
import java.text.DecimalFormat;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Weather data controller
 * usage http://<hostname:port>/data/<zipCode></zipCode>
 */
@RestController
@RequestMapping(value = "/weatherAndLocation")
public class WeatherAndLocationDataController {

    private Pattern pattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private TimeZoneService timeZoneService;

    @RequestMapping(value = "/{zipCode}", method = RequestMethod.GET)
    public WeatherAndLocationDataDTO getWeatherData(@PathVariable String zipCode) {
        WeatherAndLocationDataDTO resultWeatherData = new WeatherAndLocationDataDTO();

        try {
            // check the zip code first
            checkZipCode(zipCode);

            // get the weather data
            try {
                WeatherData weatherData = weatherService.getWeatherData(zipCode);
                resultWeatherData.getStatus().add("Weather data successfully retrieved");
                resultWeatherData.setCity(weatherData.getName());
                resultWeatherData.setTemperature(new DecimalFormat("## F").format(weatherData.getMain().getTemp()));
            } catch (ServiceException se) {
                resultWeatherData.getStatus().add("Unable to fetch weather data because of: " + se.getMessage());
            }

            // get the location data
            try {
                LatLng location = locationService.getLocation(zipCode);
                double elevation = locationService.getElevation(zipCode);


                resultWeatherData.getStatus().add("Location data successfully retrieved");
                resultWeatherData.setElevation(new DecimalFormat("#### m").format(elevation));
                resultWeatherData.setLatitude(new DecimalFormat("###.##").format(location.lat));
                resultWeatherData.setLongitude(new DecimalFormat("###.##").format(location.lng));
            } catch (ServiceException se) {
                resultWeatherData.getStatus().add("Unable to fetch location data because of: " + se.getMessage());
            }

            //get the timezone data
            try {
                TimeZone timeZone = timeZoneService.getTimeZone(zipCode);

                resultWeatherData.getStatus().add("Time zone data successfully retrieved");
                resultWeatherData.setTimezone(timeZone.getDisplayName());
            } catch (ServiceException se) {
                resultWeatherData.getStatus().add("Unable to fetch timezone data because of: " + se.getMessage());
            }

            StringBuilder messageBuilder = new StringBuilder();
            messageBuilder.append("At the location ");
            if(resultWeatherData.getCity() != null) {
                messageBuilder.append(resultWeatherData.getCity());
            } else {
                messageBuilder.append("unknown");
            }
            messageBuilder.append(", the temperature is ");
            if(resultWeatherData.getTemperature() != null) {
                messageBuilder.append(resultWeatherData.getTemperature());
            } else {
                messageBuilder.append("unknown");
            }
            messageBuilder.append(", the time zone is ");
            if(resultWeatherData.getTimezone() != null) {
                messageBuilder.append(resultWeatherData.getTimezone());
            } else {
                messageBuilder.append("unknown");
            }
            messageBuilder.append(" and the elevation is ");
            if(resultWeatherData.getElevation() != null) {
                messageBuilder.append(resultWeatherData.getElevation());
            } else {
                messageBuilder.append("unknown");
            }
            resultWeatherData.setMessage(messageBuilder.toString());

        }catch (IllegalArgumentException iae) {
            resultWeatherData.getStatus().add("Invalid zip code");
        }

        return resultWeatherData;
    }

    private void checkZipCode(String zipCode) {
        if (!pattern.matcher(zipCode).matches()) {
            throw new IllegalArgumentException("Invalid zip code");
        }
    }
}

