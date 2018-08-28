package net.rka.springboot.sample.service;

import net.rka.springboot.sample.service.ServiceException;
import net.rka.springboot.sample.service.TimeZoneService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.TimeZone;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TimeZoneServiceTest {

    @Autowired
    private TimeZoneService timeZoneService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test fetching timezone for 97006
     * @throws ServiceException
     */
    @Test
    public void testGetTimeZone() throws ServiceException {
        TimeZone timeZone = timeZoneService.getTimeZone("97006");
        Assert.assertEquals("Timezone must be America/Los_Angeles", "America/Los_Angeles", timeZone.getID());
        System.out.println("Timezone for 97006=" + timeZone.getID());

    }

}