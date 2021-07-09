package reward.service;

import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reward.model.User;
import reward.model.UserReward;
import reward.proxy.GpsProxy;
import reward.proxy.UserProxy;
import rewardCentral.RewardCentral;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * This class allows to interact with a RewardCentral
 */
@Service
public class RewardService implements RewardServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private ExecutorService executorService = Executors.newFixedThreadPool(1000);

    private RewardCentral rewardCentral = new RewardCentral();

    @Autowired
    private UserProxy userProxy;
    @Autowired
    private GpsProxy gpsProxy;

    /**
     * Creates a new RewardService
     */
    public RewardService() {
        logger.info("RewardService()");
    }

    /**
     * Creates a new RewardService with the specified UserProxy and GpsProxy
     * @param userProxy : UserProxy that this service will use
     * @param gpsProxy : GpsProxy that this service will use
     */
    public RewardService(UserProxy userProxy, GpsProxy gpsProxy) {
        logger.info("RewardService(" + userProxy + "," + gpsProxy + ")");

        this.userProxy = userProxy;
        this.gpsProxy = gpsProxy;
    }

    @Override
    public int getRewardPoints(UUID attractionId, UUID userId) {
        logger.info("getRewardPoints(" + attractionId + "," + userId + ")");

        return rewardCentral.getAttractionRewardPoints(attractionId, userId);
    }

    @Override
    public void calculateRewards(String userName) {
        logger.info("calculateRewards(" + userName + ")");

        User user = userProxy.getUser(userName);

        List<Attraction> attractionList = gpsProxy.getAllAttraction();

        if (user != null) {

            for(VisitedLocation l : user.getVisitedLocations()) {
                for(Attraction a : attractionList) {

                    if (l.getLocation().getLatitude() == a.getLatitude() &&
                            l.getLocation().getLongitude() == a.getLongitude()) {

                        boolean rewardAlreadyExist = false;

                        for(UserReward r : user.getUserRewards()) {
                            
                            if (r.getAttraction().getAttractionName().equals(a.getAttractionName())) {

                                rewardAlreadyExist = true;
                                break;
                            }
                        }

                        if (!rewardAlreadyExist) {

                            int rewardPoints = rewardCentral.getAttractionRewardPoints(a.getAttractionId(), user.getUserId());

                            userProxy.addUserReward(user.getUserName(), new UserReward(l, a, rewardPoints));
                        }

                        break;
                    }
                }
            }
        }
    }

    @Override
    public void calculateRewardsOfAllUSer() {
        logger.info("calculateRewardOfAllUSer()");

        List<User> userList = userProxy.getAllUser();
        List<Attraction> attractionList = gpsProxy.getAllAttraction();

        for (User u : userList) {
            for(VisitedLocation l : u.getVisitedLocations()) {
                for(Attraction a : attractionList) {

                    if (l.getLocation().getLatitude() == a.getLatitude() &&
                            l.getLocation().getLongitude() == a.getLongitude()) {

                        boolean rewardAlreadyExist = false;

                        for(UserReward r : u.getUserRewards()) {

                            if (r.getAttraction().getAttractionName().equals(a.getAttractionName())) {

                                rewardAlreadyExist = true;
                                break;
                            }
                        }

                        if (!rewardAlreadyExist) {

                            CompletableFuture.supplyAsync(() -> {

                                return rewardCentral.getAttractionRewardPoints(a.getAttractionId(), u.getUserId());

                            }, executorService).thenAccept(rewardPoints -> {

                                userProxy.addUserReward(u.getUserName(), new UserReward(l, a, rewardPoints));

                                System.out.println("Reward added to the user : " + u.getUserName());

                            });
                        }

                        break;
                    }
                }
            }
        }

        executorService.shutdown();

        try {

            if (!executorService.awaitTermination(20, TimeUnit.MINUTES)) {

                executorService.shutdownNow();
            }

        } catch (InterruptedException ex) {

            executorService.shutdownNow();

            Thread.currentThread().interrupt();
        }
    }
}