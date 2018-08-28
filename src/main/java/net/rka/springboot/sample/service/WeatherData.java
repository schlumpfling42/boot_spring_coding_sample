package net.rka.springboot.sample.service;

/**
 * Weather data from OpenWeatherMap - only mapped the elements that we are interested in
 */
public class WeatherData {

    private WeatherDataMain main;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherDataMain getMain() {
        return main;
    }

    public void setMain(WeatherDataMain main) {
        this.main = main;
    }
}
