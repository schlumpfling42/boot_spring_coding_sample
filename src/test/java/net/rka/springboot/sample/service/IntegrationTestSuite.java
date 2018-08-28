package net.rka.springboot.sample.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Integration test suite
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LocationServiceTest.class,
        TimeZoneServiceTest.class,
        WeatherServiceTest.class
})
public class IntegrationTestSuite {
}
