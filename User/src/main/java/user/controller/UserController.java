package user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tripPricer.Provider;
import user.model.User;
import user.model.UserPreferences;
import user.model.UserReward;
import user.service.UserService;
import user.service.UserServiceInterface;

import java.util.List;
import java.util.UUID;

/**
 * This class allows to intercept user requests
 */
@RestController
public class UserController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private UserService userService;

    /**
     * Creates a new UserController
     */
    public UserController() {
        logger.info("UserController()");

        userServiceInterface = userService;
    }

    /**
     * Creates a new UserController with the specified UserServiceInterface
     * @param userServiceInterface : service that this controller will use
     */
    public UserController(UserServiceInterface userServiceInterface) {
        logger.info("UserController(" + userServiceInterface + ")");

        this.userServiceInterface = userServiceInterface;
    }

    /**
     * Intercepts the user adding request
     * @param user : User to add
     */
    @PostMapping(value = "/addUser")
    public void addUser(@RequestBody User user) {
        logger.info("addUser(" + user + ")");

        userServiceInterface.addUser(new User(UUID.randomUUID(), user.getUserName(), user.getPhoneNumber(), user.getEmailAddress()));
    }

    /**
     * Intercepts the user visited location adding request
     * @param userName : Name of the user to add the VisitedLocation
     * @param visitedLocation : Visited location to add to the user
     */
    @PutMapping(value = "/addToVisitedLocations")
    public void addToVisitedLocations(@RequestParam String userName, @RequestBody VisitedLocation visitedLocation) {
        logger.info("addToVisitedLocations(" + userName + "," + visitedLocation + ")");

        userServiceInterface.addToVisitedLocations(userName, visitedLocation);
    }

    /**
     * Intercepts the user reward adding request
     * @param userName : Name of the User to add UserReward
     * @param userReward : Reward to add to the user
     */
    @PutMapping(value = "/addUserReward")
    public void addUserReward(@RequestParam String userName, @RequestBody UserReward userReward) {
        logger.info("addUserReward(" + userName + "," + userReward + ")");

        userServiceInterface.addUserReward(userName, userReward);
    }

    /**
     * Intercepts the user preferences setting request
     * @param userName : Name of the User to set the UserPreferences
     * @param userPreferences : Preferences to set to the user
     */
    @PutMapping(value = "/setUserPreferences")
    public void setUserPreferences(@RequestParam String userName, @RequestBody UserPreferences userPreferences) {
        logger.info("setUserPreferences(" + userName + "," + userPreferences + ")");

        userServiceInterface.setUserPreferences(userName, userPreferences);
    }

    /**
     * Intercepts the user trip deals setting request
     * @param userName : Name of the user to set the trip deals
     * @param tripDeals : List of trip deals to set to the user
     */
    @PutMapping(value = "/setTripDeals")
    public void setTripDeals(@RequestParam String userName, @RequestBody List<Provider> tripDeals) {
        logger.info("setTripDeals(" + userName + "," + tripDeals + ")");

        userServiceInterface.setTripDeals(userName, tripDeals);
    }

    /**
     * Intercepts the user getting request
     * @param userName : Name of the User to find
     * @return The User found (JSon)
     */
    @GetMapping(value = "/getUser")
    public String getUser(@RequestParam String userName) throws JsonProcessingException {
        logger.info("getUser(" + userName + ")");

        return objectMapper.writeValueAsString(userServiceInterface.getUser(userName));
    }

    /**
     * Intercepts the user list getting request
     * @return The User list (JSon)
     */
    @GetMapping(value = "/getAllUser")
    public String getAllUser() throws JsonProcessingException {
        logger.info("getAllUser()");

        return objectMapper.writeValueAsString(userServiceInterface.getAllUser());
    }
}