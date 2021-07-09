package gps.service;

import gps.model.User;
import gps.model.UserNearestAttraction;
import gps.proxy.RewardProxy;
import gps.proxy.UserProxy;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class allows to interact with a GpsUtil
 */
@Service
public class GpsService implements GpsServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private ExecutorService executorService = Executors.newFixedThreadPool(1000);

    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

    private GpsUtil gpsUtil = new GpsUtil();

    @Autowired
    private UserProxy userProxy;
    @Autowired
    private RewardProxy rewardProxy;

    /**
     * Creates a new GpsService
     */
    public GpsService() {
        logger.info("GpsService()");
    }

    /**
     * Creates a new GpsService with the specified UserProxy and RewardProxy
     * @param userProxy : UserProxy that this service will use
     * @param rewardProxy : RewardProxy that this service will use
     */
    public GpsService(UserProxy userProxy, RewardProxy rewardProxy) {
        logger.info("GpsService(" + userProxy + "," + rewardProxy + ")");

        this.userProxy = userProxy;
        this.rewardProxy = rewardProxy;
    }

    @Override
    public VisitedLocation getUserLocation(String userName) {
        logger.info("getUserLocation(" + userName + ")");

        VisitedLocation visitedLocation = new VisitedLocation();

        User user = userProxy.getUser(userName);

        if (user != null) {

            if (user.getVisitedLocations().size() <= 0) {

                visitedLocation = gpsUtil.getUserLocation(user.getUserId());

                userProxy.addToVisitedLocations(userName, visitedLocation);
                rewardProxy.calculateRewards(userName);
            }

            else {

                visitedLocation = user.getVisitedLocations().get(user.getVisitedLocations().size()-1);
            }
        }

        return visitedLocation;
    }

    @Override
    public Map<UUID, VisitedLocation> getAllCurrentLocations() {
        logger.info("getAllCurrentLocations()");

        Map<UUID, VisitedLocation> visitedLocationMap = new HashMap<>();

        for (User u : userProxy.getAllUser()) {

            if (u.getVisitedLocations().size() > 0) {

                visitedLocationMap.put(u.getUserId(), u.getVisitedLocations().get(u.getVisitedLocations().size()-1));
            }
        }

        return visitedLocationMap;
    }

    @Override
    public List<UserNearestAttraction> getNearByAttractions(String userName) {
        logger.info("getNearByAttractions(" + userName + ")");

        List<UserNearestAttraction> userNearestAttractionList = new ArrayList<>();

        User user = userProxy.getUser(userName);

        if (user != null) {

            VisitedLocation visitedLocation = user.getVisitedLocations().get(user.getVisitedLocations().size()-1);

            Map<Double, Attraction> attractionMap = new TreeMap<>();

            for (Attraction attraction : gpsUtil.getAttractions()) {

                double lat1 = Math.toRadians(attraction.getLatitude());
                double lon1 = Math.toRadians(attraction.getLongitude());
                double lat2 = Math.toRadians(visitedLocation.getLocation().getLatitude());
                double lon2 = Math.toRadians(visitedLocation.getLocation().getLongitude());

                double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                        + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

                double nauticalMiles = user.getUserPreferences().getAttractionProximity() * Math.toDegrees(angle);
                double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;

                attractionMap.put(statuteMiles, attraction);
            }

            for (Map.Entry<Double, Attraction> entry : attractionMap.entrySet()) {

                String attractionName = entry.getValue().getAttractionName();
                Location attractionLocation = new Location(entry.getValue().getLatitude(), entry.getValue().getLongitude());
                Location userLocation = visitedLocation.getLocation();
                double attractionMilesDistance = entry.getKey();
                int rewardPoints = rewardProxy.getRewardPoints(entry.getValue().getAttractionId(), user.getUserId());

                userNearestAttractionList.add(new UserNearestAttraction(attractionName,
                        attractionLocation, userLocation, attractionMilesDistance, rewardPoints));

                if (userNearestAttractionList.size() >= 5) {

                    break;
                }
            }
        }

        return userNearestAttractionList;
    }

    @Override
    public Attraction getAttraction(String attractionName) {
        logger.info("getAttraction(" + attractionName + ")");

        Attraction attraction = null;

        for (Attraction a : gpsUtil.getAttractions()) {

            if (a.getAttractionName().equals(attractionName)) {

                attraction = a;
                break;
            }
        }

        return attraction;
    }

    @Override
    public List<Attraction> getAttractionList() {
        logger.info("getAttractionList()");

        return gpsUtil.getAttractions();
    }

    @Override
    public void calculateAllUSerLocation() {
        logger.info("calculateAllUSerLocation()");

        List<User> userList = userProxy.getAllUser();

        for (User u : userList) {

            CompletableFuture.supplyAsync(() -> {

                return gpsUtil.getUserLocation(u.getUserId());

            }, executorService).thenAccept(visitedLocation -> {

                userProxy.addToVisitedLocations(u.getUserName(), visitedLocation);

                System.out.println("Visited location added to the user : " + u.getUserName());

            });
        }

        executorService.shutdown();

        try {

            if (!executorService.awaitTermination(15, TimeUnit.MINUTES)) {

                executorService.shutdownNow();
            }

        } catch (InterruptedException ex) {

            executorService.shutdownNow();

            Thread.currentThread().interrupt();
        }
    }
}