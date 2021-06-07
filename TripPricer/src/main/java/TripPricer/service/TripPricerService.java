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

    @Autowired
    private UserProxy userProxy;

    private TripPricer tripPricer = new TripPricer();

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
    public List<Provider> getTripDeals(String userName) {
        logger.info("getTripDeals(" + userName + ")");

        User user = userProxy.getUser(userName);

        int cumulativeRewardPoints = user.getUserRewards().stream().mapToInt(i -> i.getRewardPoints()).sum();

        List<Provider> providers = tripPricer.getPrice(tripPricerApiKey,
                user.getUserId(),
                user.getUserPreferences().getNumberOfAdults(),
                user.getUserPreferences().getNumberOfChildren(),
                user.getUserPreferences().getTripDuration(),
                cumulativeRewardPoints);

        return providers;
    }
}