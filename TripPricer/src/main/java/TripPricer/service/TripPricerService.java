package TripPricer.service;

import TripPricer.model.User;
import TripPricer.proxy.UserProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tripPricer.Provider;
import tripPricer.TripPricer;

import java.util.List;

/**
 * This class allows to interact with a TripPricer
 */
@Service
public class TripPricerService implements TripPricerServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private static final String tripPricerApiKey = "test-server-api-key";

    private TripPricer tripPricer = new TripPricer();

    @Autowired
    private UserProxy userProxy;

    /**
     * Creates a new TripPricerService
     */
    public TripPricerService() {
        logger.info("TripPricerService()");
    }

    /**
     * Creates a new TripPricerService with the specified UserProxy
     * @param userProxy : UserProxy that this service will use
     */
    public TripPricerService(UserProxy userProxy) {
        logger.info("TripPricerService(" + userProxy + ")");

        this.userProxy = userProxy;
    }

    @Override
    public void calculateTripDeals(String userName) {
        logger.info("calculateTripDeals(" + userName + ")");

        List<Provider> providerList;

        User user = userProxy.getUser(userName);

        if (user != null) {

            int cumulativeRewardPoints = user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();

            providerList = tripPricer.getPrice(tripPricerApiKey,user.getUserId(),
                    user.getUserPreferences().getNumberOfAdults(), user.getUserPreferences().getNumberOfChildren(),
                    user.getUserPreferences().getTripDuration(), cumulativeRewardPoints);

            userProxy.setTripDeals(userName, providerList);
        }
    }
}