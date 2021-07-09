package user.service;

import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tripPricer.Provider;
import user.model.User;
import user.model.UserPreferences;
import user.model.UserReward;
import user.repository.UserRepository;
import user.repository.UserRepositoryInterface;

import java.util.List;

/**
 * This class allows to interact with an user repository
 */
@Service
public class UserService implements UserServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new UserService
     */
    public UserService() {
        logger.info("UserService()");

        userRepositoryInterface = userRepository;
    }

    /**
     * Creates a new UserService with the specified UserRepositoryInterface
     * @param userRepositoryInterface : repository that this service will use
     */
    public UserService(UserRepositoryInterface userRepositoryInterface) {
        logger.info("UserService(" + userRepositoryInterface + ")");

        this.userRepositoryInterface = userRepositoryInterface;
    }

    @Override
    public void addUser(User user) {
        logger.info("addUser(" + user + ")");

        userRepositoryInterface.addUser(user);
    }

    @Override
    public void addToVisitedLocations(String userName, VisitedLocation visitedLocation) {
        logger.info("addToVisitedLocations(" + userName + "," + visitedLocation + ")");

        User user = userRepositoryInterface.getUser(userName);

        if (user != null) {

            user.getVisitedLocations().add(visitedLocation);
        }
    }

    @Override
    public void addUserReward(String userName, UserReward userReward) {
        logger.info("addUserReward(" + userName + "," + userReward + ")");

        User user = userRepositoryInterface.getUser(userName);

        if (user != null) {

            boolean alreadyExist = false;

            for (UserReward r : user.getUserRewards()) {

                if (r.getAttraction().getAttractionName().equals(userReward.getAttraction().getAttractionName())) {

                    alreadyExist = true;
                }
            }

            if (alreadyExist == false) {

                user.getUserRewards().add(userReward);
            }
        }
    }

    @Override
    public void setUserPreferences(String userName, UserPreferences userPreferences) {
        logger.info("setUserPreferences(" + userName + "," + userPreferences + ")");

        User user = userRepositoryInterface.getUser(userName);

        if (user != null) {

            user.setUserPreferences(userPreferences);
        }
    }

    @Override
    public void setTripDeals(String userName, List<Provider> tripDeals) {
        logger.info("setTripDeals(" + userName + "," + tripDeals + ")");

        User user = userRepositoryInterface.getUser(userName);

        if (user != null) {

            user.setTripDeals(tripDeals);
        }
    }

    @Override
    public User getUser(String userName) {
        logger.info("getUser(" + userName + ")");

        return userRepositoryInterface.getUser(userName);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("getAllUser()");

        return userRepositoryInterface.getAllUser();
    }
}