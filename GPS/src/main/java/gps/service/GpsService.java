package gps.service;

import gps.model.User;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GpsService implements GpsServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
    private int attractionProximityRange = 200;

    private GpsUtil gpsUtil;

    public GpsService() {
        logger.info("GpsService()");

        gpsUtil = new GpsUtil();
    }

    public GpsService(GpsUtil gpsUtil) {
        logger.info("GpsService(" + gpsUtil + ")");

        this.gpsUtil = gpsUtil;
    }

    @Override
    public VisitedLocation getUserLocation(User user) {
        logger.info("getUserLocation(" + user + ")");

        VisitedLocation visitedLocation = null;

        if (user.getVisitedLocations().size() <= 0) {

            visitedLocation = gpsUtil.getUserLocation(user.getUserId());

            user.addToVisitedLocations(visitedLocation);

//            rewardsService.calculateRewards(user);
        }

        else {

            visitedLocation = user.getLastVisitedLocation();
        }

        return visitedLocation;
    }


    // TODO: Get a list of every user's most recent location as JSON
    //- Note: does not use gpsUtil to query for their current location,
    //        but rather gathers the user's current location from their stored location history.
    //
    // Return object should be the just a JSON mapping of userId to Locations similar to:
    //     {
    //        "019b04a9-067a-4c76-8817-ee75088c3822": {"longitude":-48.188821,"latitude":74.84371}
    //        ...
    //     }
    @Override
    public List<VisitedLocation> getAllCurrentLocations() {
        logger.info("getAllCurrentLocations()");

        return null;
    }

    //  TODO: Change this method to no longer return a List of Attractions.
    //  Instead: Get the closest five tourist attractions to the user - no matter how far away they are.
    //  Return a new JSON object that contains:
    // Name of Tourist attraction,
    // Tourist attractions lat/long,
    // The user's location lat/long,
    // The distance in miles between the user's location and each of the attractions.
    // The reward points for visiting each Attraction.
    //    Note: Attraction reward points can be gathered from RewardsCentral
    @Override
    public List<Attraction> getNearByAttractions(VisitedLocation visitedLocation) {
        logger.info("getNearByAttractions(" + visitedLocation + ")");

        List<Attraction> nearbyAttractions = new ArrayList<>();

        for(Attraction attraction : gpsUtil.getAttractions()) {

            double lat1 = Math.toRadians(attraction.latitude);
            double lon1 = Math.toRadians(attraction.longitude);
            double lat2 = Math.toRadians(visitedLocation.location.latitude);
            double lon2 = Math.toRadians(visitedLocation.location.longitude);

            double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                    + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

            double nauticalMiles = 60 * Math.toDegrees(angle);
            double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;

            if (statuteMiles > attractionProximityRange) {

                nearbyAttractions.add(attraction);
            }
        }

        return nearbyAttractions;
    }

    @Override
    public List<Attraction> getAttractionList() {
        return gpsUtil.getAttractions();
    }
}