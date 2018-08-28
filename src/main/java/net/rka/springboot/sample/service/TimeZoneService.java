package net.rka.springboot.sample.service;

import com.google.maps.*;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TimeZone;

/**
 * Service retrieving location data
        */
@Service
public class TimeZoneService {

    @Autowired
    private LocationService locationService;

    @Autowired
    private GeoApiContextService contextService;

    /**
     * Get the elevation in meters for a given zip code
     * @param zipCode
     * @return elevation
     * @throws ServiceException
     */
    public TimeZone getTimeZone(String zipCode) throws ServiceException {
        try {
            // first get the
            GeoApiContext context = contextService.getContext();
            LatLng location = locationService.getLocation(zipCode);
            return TimeZoneApi.getTimeZone(context, location).await();

        } catch(Exception e) {
            throw new ServiceException("Unexpected error calling the Google Location API", e);
        }

    }

}
