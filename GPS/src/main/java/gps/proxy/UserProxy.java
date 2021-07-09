package gps.proxy;

import gps.model.User;
import gpsUtil.location.VisitedLocation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This interface allows to send requests to the user api
 */
@Component
@FeignClient(name = "user-api", url = "http://localhost:8080")
public interface UserProxy {

    /**
     * Send the user getting request
     * @param userName : Name of the User to find
     * @return The User found
     */
    @GetMapping(value = "/getUser", produces = "application/json")
    public User getUser(@RequestParam String userName);

    /**
     * Send the user list getting request
     * @return The User list
     */
    @GetMapping(value = "/getAllUser", produces = "application/json")
    public List<User> getAllUser();

    /**
     * Send the user visited location adding request
     * @param userName : The name of the user whose you want to add a VisitedLocation
     * @param visitedLocation : Visited location to add to the user
     */
    @PutMapping(value = "/addToVisitedLocations", produces = "application/json")
    public void addToVisitedLocations(@RequestParam String userName, @RequestBody VisitedLocation visitedLocation);
}
