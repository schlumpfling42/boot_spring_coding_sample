package net.rka.springboot.sample.service;

import net.rka.springboot.sample.service.ServiceException;
import net.rka.springboot.sample.service.WeatherData;
import net.rka.springboot.sample.service.WeatherService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test get weather data for 97006
     * @throws ServiceException
     */
    @Test
    public void testGetWeatherData() throws ServiceException {
        WeatherData weatherData = weatherService.getWeatherData("97006");
        System.out.println("Temp in Fahrenheit for 97006=" + weatherData.getMain().getTemp());
        System.out.println("City for 97006=" + weatherData.getName());
        Assert.assertEquals("City name must be Portland", "Portland", weatherData.getName());
        Assert.assertTrue("Temperature must be small than 120", 120 > weatherData.getMain().getTemp());

    }

}