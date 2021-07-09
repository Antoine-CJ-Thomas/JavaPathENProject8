package user.service;


import gpsUtil.location.VisitedLocation;
import tripPricer.Provider;
import user.model.User;
import user.model.UserPreferences;
import user.model.UserReward;

import java.util.List;

/**
 * This interface allows to implement user service methods
 */
public interface UserServiceInterface {

    /**
     * Add an User
     * @param user : data of the User to add
     */
    public void addUser(User user);

    /**
     * Add a VisitedLocation to an user
     * @param userName : Name of the User to add the visitedLocation
     * @param visitedLocation : VisitedLocation to add to the User
     */
    public void addToVisitedLocations(String userName, VisitedLocation visitedLocation);

    /**
     * Add a UserReward to an User
     * @param userName : Name of the User to add the UserReward
     * @param userReward : UserReward to add to the User
     */
    public void addUserReward(String userName, UserReward userReward);

    /**
     * Set the UserPreferences of an User
     * @param userName : Name of the User to set the userPreferences
     * @param userPreferences : UserPreferences to set to the User
     */
    public void setUserPreferences(String userName, UserPreferences userPreferences);

    /**
     * Set the trip deals of an User
     * @param userName : Name of the User to set the trip deals
     * @param tripDeals : List of trip deals to set to the User
     */
    void setTripDeals(String userName, List<Provider> tripDeals);

    /**
     * Get an User
     * @param userName : Name of the User to find
     * @return The User that was found
     */
    public User getUser(String userName);

    /**
     * Get the User list
     * @return The User list
     */
    public List<User> getAllUser();
}