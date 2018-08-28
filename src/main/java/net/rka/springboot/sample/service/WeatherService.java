package net.rka.springboot.sample.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

/**
 * Service retrieving weather data
 */
@Service
public class WeatherService {

    @Value("${weatherMapAPIKey}")
    private String key;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    /**
     * Fetch the weather data for a given postal code
     * @param zipCode
     * @return
     * @throws ServiceException
     */
    public WeatherData getWeatherData(String zipCode) throws ServiceException {
        try {
            return restTemplateBuilder.build().getForObject("http://api.openweathermap.org/data/2.5/weather?units=imperial&zip={0},us&APPID={1}", WeatherData.class, zipCode, key);
        } catch (Exception e) {
            throw new ServiceException("Unable to call Weather API", e);
        }

    }
}
