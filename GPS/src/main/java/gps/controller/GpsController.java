package gps.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gps.service.GpsService;
import gps.service.GpsServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class allows to intercept gps requests
 */
@RestController
public class GpsController {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private GpsServiceInterface gpsServiceInterface;

    @Autowired
    private GpsService gpsService;

    /**
     * Creates a new GpsController
     */
    public GpsController() {
        logger.info("GpsController()");

        gpsServiceInterface = gpsService;
    }

    /**
     * Creates a new GpsController with the specified GpsServiceInterface
     * @param gpsServiceInterface : service that this controller will use
     */
    public GpsController(GpsServiceInterface gpsServiceInterface) {
        logger.info("GpsController(" + gpsServiceInterface + ")");

        this.gpsServiceInterface = gpsServiceInterface;
    }

    /**
     * Intercepts the user location getting request
     * @param userName : Name of the user you want to locate
     * @return The VisitedLocation of the user (JSon)
     */
    @GetMapping("/getUserLocation")
    public String getUserLocation(@RequestParam String userName) throws JsonProcessingException {
        logger.info("getUserLocation(" + userName + ")");

        return objectMapper.writeValueAsString(gpsServiceInterface.getUserLocation(userName));
    }

    /**
     * Intercepts the user location list getting request
     * @return The visited location of all users sorted by their ID (JSon)
     */
    @GetMapping("/getAllCurrentLocations")
    public String getAllCurrentLocations() throws JsonProcessingException {
        logger.info("getAllCurrentLocations()");

        return objectMapper.writeValueAsString(gpsServiceInterface.getAllCurrentLocations());
    }

    /**
     * Intercepts the nearest attraction getting request
     * @param userName : Name of the user for which you want to know the nearest attractions
     * @return The nearest attraction list (JSon)
     */
    @GetMapping("/getNearbyAttractions")
    public String getNearbyAttractions(@RequestParam String userName) throws JsonProcessingException {
        logger.info("getNearbyAttractions(" + userName + ")");

        gpsServiceInterface.getUserLocation(userName);

        return objectMapper.writeValueAsString(gpsServiceInterface.getNearByAttractions(userName));
    }

    /**
     * Intercepts the attraction getting request
     * @param attractionName : Name of the Attraction to find
     * @return The Attraction found (JSon)
     */
    @GetMapping("/getAttraction")
    public String getAttraction(@RequestParam String attractionName) throws JsonProcessingException {
        logger.info("getAttraction(" + attractionName + ")");

        return objectMapper.writeValueAsString(gpsServiceInterface.getAttraction(attractionName));
    }

    /**
     * Intercepts the attraction list getting request
     * @return The list of all Attraction (JSon)
     */
    @GetMapping("/getAttractionList")
    public String getAttractionList() throws JsonProcessingException {
        logger.info("getAttractionList()");

        return objectMapper.writeValueAsString(gpsServiceInterface.getAttractionList());
    }

    /**
     * Intercepts the users locations calculating request
     */
    @PutMapping("/calculateAllUSerLocation")
    public void calculateAllUSerLocation() {
        logger.info("calculateAllUSerLocation()");

        gpsServiceInterface.calculateAllUSerLocation();
    }
}