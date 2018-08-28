package net.rka.springboot.sample.service;

import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service retrieving location data
 */
@Service
public class LocationService {

    @Autowired
    private GeoApiContextService contextService;


    /**
     * Get the location as latitude and longitude
     * @param zipCode
     * @return LatLng
     * @throws ServiceException
     */
    public LatLng getLocation(String zipCode) throws ServiceException {
        try {
            GeoApiContext context = contextService.getContext();
            GeocodingResult[] geoCodingResults = GeocodingApi.geocode(context,
                    zipCode).await();
            // if the location can be found, fetch the elevation
            if(geoCodingResults != null && geoCodingResults.length == 1) {
                return geoCodingResults[0].geometry.location;

            } else {
                throw new ServiceException("The location could not be found");
            }
        } catch(Exception e) {
            throw new ServiceException("Unexpected error calling the Google Location API", e);
        }
    }

    /**
     * Get the elevation in meters for a given zip code
     * @param zipCode
     * @return elevation
     * @throws ServiceException
     */
    public double getElevation(String zipCode) throws ServiceException {
        try {
            // first get the
            GeoApiContext context = contextService.getContext();
                LatLng location = getLocation(zipCode);
                ElevationResult[] elevationResults = ElevationApi.getByPoints(context, location).await();
                if(elevationResults != null && elevationResults.length == 1) {
                    return elevationResults[0].elevation;
                } else {
                    throw new ServiceException("The location could not be found");
                }

        } catch(ServiceException se) {
            throw se;
        } catch(Exception e) {
            throw new ServiceException("Unexpected error calling the Google Location API", e);
        }

    }

}
