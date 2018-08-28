package net.rka.springboot.sample.service;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service to manage the Google API context
 * TODO setting a private variable for right now, not sure what the lifecycle of the context ist
 */
@Service
public class GeoApiContextService {
    @Value("${googleAPIKey}")
    private String key;
    private GeoApiContext context;

    /**
     * Returns a current GeoApi context
     * @return
     */
    public synchronized GeoApiContext getContext() {
        if (context == null) {
            context = new GeoApiContext.Builder()
                    .apiKey(key)
                    .build();
        }
        return context;
    }
}
