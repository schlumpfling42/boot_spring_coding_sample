# boot_spring_coding_sample
Simple boot spring application with rest endpoint retrieving
- current temperature and city name
- current timezone for a location
- elevation data for a location
for a zip code.

The project is set up as a Maven project, all dependencies are included.

Google API and OpenWeatherMap keys have to be provided in \src\main\resources\app.properties to successfully call the backend services.

To launch the application, execute SampleApplication.
Example url for calling the rest service: http://localhost:8080/weatherAndLocation/98526

An integration test suite is included to execute basic service tests.
