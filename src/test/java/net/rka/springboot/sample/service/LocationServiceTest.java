package net.rka.springboot.sample.service;

import com.google.maps.model.LatLng;
import net.rka.springboot.sample.service.LocationService;
import net.rka.springboot.sample.service.ServiceException;
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
public class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test elevation for 97006
     * @throws ServiceException
     */
    @Test
    public void testGetElevation() throws ServiceException {
        double elevation97006 = locationService.getElevation("97006");
        System.out.println("Elevation for 97006=" + elevation97006);
        Assert.assertTrue("Elevation must be greater than 64 meters", 64 < elevation97006);

    }

    /**
     * Test location for 97006
     * @throws ServiceException
     */
    @Test
    public void testGetLocation() throws ServiceException {
        LatLng location = locationService.getLocation("97006");
        System.out.println("Location for 97006=" + location);
        Assert.assertTrue("Latitude must be greater than 45", 45 < location.lat);
        Assert.assertTrue("Longitude must be small than -122 ", -122 > location.lng);

    }
}