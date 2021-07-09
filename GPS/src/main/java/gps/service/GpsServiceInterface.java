package gps.service;

import gps.model.UserNearestAttraction;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * This interface allows to implement gps service methods
 */
@Component
public interface GpsServiceInterface {

    /**
     * Get an user VisitedLocation
     * @param userName : Name of the user whose you want to get the VisitedLocation
     * @return The VisitedLocation of the user
     */
    public VisitedLocation getUserLocation(String userName);

    /**
     * Get all users VisitedLocation
     * @return The Map of all user UUID/VisitedLocation
     */
    public Map<UUID, VisitedLocation> getAllCurrentLocations();

    /**
     * Get an Attraction
     * @param attractionName : Name of the Attraction to find
     * @return The Attraction found
     */
    public Attraction getAttraction(String attractionName);

    /**
     * Get all Attractions
     * @return The List of all Attraction
     */
    public List<Attraction> getAttractionList();

    /**
     * Get the list of the nearest Attractions
     * @param userName : Name of the user whose you want to get the nearest Attraction
     * @return The List of UserNearestAttraction
     */
    public List<UserNearestAttraction> getNearByAttractions(String userName);

    /**
     * Calculate the VisitedLocation of all Users
     */
    public void calculateAllUSerLocation();
}